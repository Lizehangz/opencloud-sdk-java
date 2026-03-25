package com.opencloud.sdk;

import com.opencloud.sdk.webdav.ThumbnailOptions;
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
        client.close();
        server.shutdown();
    }

    @Test
    public void shouldUploadFileToResourceScopedWebDavRootPath() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(201));

        client.webDav().upload("space-1", "readme.txt", "hello".getBytes("UTF-8"), "text/plain");

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("PUT", request.getMethod());
        Assert.assertEquals("/dav/spaces/space-1/readme.txt", request.getPath());
        Assert.assertEquals("text/plain", request.getHeader("Content-Type"));
        Assert.assertEquals("hello", request.getBody().readUtf8());
    }

    @Test
    public void shouldGetThumbnailFromResourceScopedWebDavPath() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("thumb"));

        byte[] thumbnail = client.webDav().getThumbnail("space-1", "photos/cat.jpg", 128, 96).getBody();

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("GET", request.getMethod());
        Assert.assertEquals("/dav/spaces/space-1/photos/cat.jpg?preview=1&x=128&y=96", request.getPath());
        Assert.assertEquals("*/*", request.getHeader("Accept"));
        Assert.assertEquals("thumb", new String(thumbnail, "UTF-8"));
    }

    @Test
    public void shouldGetThumbnailWithAdvancedOptions() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("thumb"));

        ThumbnailOptions options = ThumbnailOptions.builder()
            .processor("fit")
            .scalingUp(false)
            .aspectRatio(true)
            .cacheKey("etag-123")
            .build();

        client.webDav().getThumbnail("space-1", "photos/cat.jpg", 200, 200, options);

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals(
            "/dav/spaces/space-1/photos/cat.jpg?preview=1&x=200&y=200&processor=fit&scalingup=0&a=1&c=etag-123",
            request.getPath()
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidThumbnailWidth() throws Exception {
        client.webDav().getThumbnail("space-1", "photos/cat.jpg", 0, 96);
    }

    @Test
    public void shouldSetDestinationHeaderForResourceScopedCopy() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(201));

        client.webDav().copy("space-1", "docs/readme.txt", "archive/readme-copy.txt", true);

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("COPY", request.getMethod());
        Assert.assertEquals(
            server.url("/").toString().substring(0, server.url("/").toString().length() - 1) + "/dav/spaces/space-1/archive/readme-copy.txt",
            request.getHeader("Destination")
        );
        Assert.assertEquals("T", request.getHeader("Overwrite"));
    }

    @Test
    public void shouldSetDestinationHeaderForResourceScopedMove() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(201));

        client.webDav().move("space-1", "docs/readme.txt", "archive/readme.txt", false);

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("MOVE", request.getMethod());
        Assert.assertEquals("F", request.getHeader("Overwrite"));
        Assert.assertTrue(request.getHeader("Destination").endsWith("/dav/spaces/space-1/archive/readme.txt"));
    }

    @Test
    public void shouldKeepLegacyWebDavMethodsForCompatibility() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(201));

        client.webDav().upload("/files/demo/readme.txt", "hello".getBytes("UTF-8"), "text/plain");

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals("/remote.php/dav/files/demo/readme.txt", request.getPath());
    }
}
