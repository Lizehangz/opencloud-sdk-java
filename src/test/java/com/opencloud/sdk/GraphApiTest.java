package com.opencloud.sdk;

import com.opencloud.sdk.auth.BearerTokenAuthProvider;
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.User;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GraphApiTest {
    private MockWebServer server;
    private OpenCloudClient client;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        client = OpenCloudClient.create(
            OpenCloudClientConfig.builder()
                .baseUrl(server.url("/").toString())
                .authProvider(new BearerTokenAuthProvider("test-token"))
                .build()
        );
    }

    @After
    public void tearDown() throws Exception {
        client.close();
        server.shutdown();
    }

    @Test
    public void shouldDeserializeTypedUsersCollection() throws Exception {
        server.enqueue(new MockResponse()
            .setHeader("Content-Type", "application/json")
            .setBody("{\"value\":[{\"id\":\"u1\",\"displayName\":\"Albert Einstein\",\"mail\":\"albert@example.org\"}]}"));

        CollectionResponse<User> response = client.graph().users().listModel().getBody();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getValue());
        Assert.assertEquals(1, response.getValue().size());
        Assert.assertEquals("u1", response.getValue().get(0).getId());
        Assert.assertEquals("Albert Einstein", response.getValue().get(0).getDisplayName());

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("GET", request.getMethod());
        Assert.assertEquals("/graph/v1.0/users", request.getPath());
        Assert.assertEquals("Bearer test-token", request.getHeader("Authorization"));
    }

    @Test
    public void shouldDeserializeGroupMembersAsArray() throws Exception {
        server.enqueue(new MockResponse()
            .setHeader("Content-Type", "application/json")
            .setBody("[{\"id\":\"u1\",\"displayName\":\"Admin\",\"mail\":\"admin@example.org\"}]"));

        List<User> response = client.graph().groups().listMembersModel("group-1").getBody();

        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.size());
        Assert.assertEquals("u1", response.get(0).getId());
        Assert.assertEquals("Admin", response.get(0).getDisplayName());

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("GET", request.getMethod());
        Assert.assertEquals("/graph/v1.0/groups/group-1/members", request.getPath());
    }

    @Test
    public void shouldSendUserReferenceWhenAddingGroupMember() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(204));

        client.graph().groups().addMember("group-1", "user-1");

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("POST", request.getMethod());
        Assert.assertEquals("/graph/v1.0/groups/group-1/members/$ref", request.getPath());
        Assert.assertTrue(request.getBody().readUtf8().contains("/graph/v1.0/users/user-1"));
    }

    @Test
    public void shouldSendOcsApiHeaderAndJsonFormat() throws Exception {
        server.enqueue(new MockResponse()
            .setHeader("Content-Type", "application/json")
            .setBody("{\"ocs\":{\"meta\":{\"status\":\"ok\",\"statuscode\":200,\"message\":\"OK\"},\"data\":{}}}"));

        client.ocs().getCapabilities();

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("true", request.getHeader("OCS-APIRequest"));
        Assert.assertEquals("application/json", request.getHeader("Accept"));
        Assert.assertEquals("/ocs/v2.php/cloud/capabilities?format=json", request.getPath());
    }
}
