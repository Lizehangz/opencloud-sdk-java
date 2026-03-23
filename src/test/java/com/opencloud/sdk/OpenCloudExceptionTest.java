package com.opencloud.sdk;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OpenCloudExceptionTest {
    private MockWebServer server;
    private OpenCloudClient client;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        client = OpenCloudClient.create(
            OpenCloudClientConfig.builder()
                .baseUrl(server.url("/").toString())
                .build()
        );
    }

    @After
    public void tearDown() throws Exception {
        client.close();
        server.shutdown();
    }

    @Test
    public void shouldParseODataErrorResponse() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(404)
            .setHeader("Content-Type", "application/json")
            .setBody("{\"error\":{\"code\":\"itemNotFound\",\"message\":\"Item missing\",\"target\":\"item-id\",\"details\":[{\"code\":\"missing\",\"message\":\"No such item\"}]}}"));

        try {
            client.graph().users().getModel("missing-id");
            Assert.fail("Expected OpenCloudException");
        } catch (OpenCloudException ex) {
            Assert.assertEquals(404, ex.getStatusCode());
            Assert.assertEquals("itemNotFound", ex.getErrorCode());
            Assert.assertEquals("Item missing", ex.getErrorMessage());
            Assert.assertEquals("item-id", ex.getTarget());
            Assert.assertNotNull(ex.getApiError());
            Assert.assertEquals(1, ex.getApiError().getDetails().size());
        }
    }

    @Test
    public void shouldParseOcsErrorResponse() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(500)
            .setHeader("Content-Type", "application/json")
            .setBody("{\"ocs\":{\"meta\":{\"status\":\"failure\",\"statuscode\":500,\"message\":\"Server exploded\"},\"data\":[]}}"));

        try {
            client.ocs().getCapabilities();
            Assert.fail("Expected OpenCloudException");
        } catch (OpenCloudException ex) {
            Assert.assertEquals(500, ex.getStatusCode());
            Assert.assertEquals("500", ex.getErrorCode());
            Assert.assertEquals("Server exploded", ex.getErrorMessage());
            Assert.assertEquals("failure", ex.getTarget());
        }
    }

    @Test
    public void shouldExposeBodyWhenSuccessfulResponseCannotBeParsed() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "text/plain")
            .setBody("can't split empty storage space ID: invalid storage space id"));

        try {
            client.graph().activities().listModel("resourceid:demo depth:2");
            Assert.fail("Expected OpenCloudException");
        } catch (OpenCloudException ex) {
            Assert.assertEquals(200, ex.getStatusCode());
            Assert.assertNull(ex.getApiError());
            Assert.assertTrue(ex.getMessage().contains("could not be parsed"));
            Assert.assertTrue(ex.getResponseBody().contains("invalid storage space id"));
            Assert.assertNotNull(ex.getCause());
        }
    }
}
