package com.opencloud.sdk;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WebDavApiTest {
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
        server.shutdown();
    }

    @Test
    public void shouldUploadFileToWebDav() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(201));

        client.webDav().upload("/files/demo/readme.txt", "hello".getBytes("UTF-8"), "text/plain");

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("PUT", request.getMethod());
        Assert.assertEquals("/remote.php/dav/files/demo/readme.txt", request.getPath());
        Assert.assertEquals("text/plain", request.getHeader("Content-Type"));
        Assert.assertEquals("hello", request.getBody().readUtf8());
    }

    @Test
    public void shouldSetDestinationHeaderForCopy() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(201));

        client.webDav().copy("/files/demo/readme.txt", "/files/demo/readme-copy.txt", true);

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("COPY", request.getMethod());
        Assert.assertEquals(
            server.url("/").toString().substring(0, server.url("/").toString().length() - 1) + "/remote.php/dav/files/demo/readme-copy.txt",
            request.getHeader("Destination")
        );
        Assert.assertEquals("T", request.getHeader("Overwrite"));
    }

    @Test
    public void shouldSetDestinationHeaderForMove() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(201));

        client.webDav().move("/files/demo/readme.txt", "/files/demo/archive/readme.txt", false);

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("MOVE", request.getMethod());
        Assert.assertEquals("F", request.getHeader("Overwrite"));
        Assert.assertTrue(request.getHeader("Destination").endsWith("/remote.php/dav/files/demo/archive/readme.txt"));
    }
}
