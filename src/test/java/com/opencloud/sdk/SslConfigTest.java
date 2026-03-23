package com.opencloud.sdk;

import okhttp3.tls.HandshakeCertificates;
import okhttp3.tls.HeldCertificate;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SslConfigTest {
    private MockWebServer server;
    private HeldCertificate heldCertificate;

    @Before
    public void setUp() throws Exception {
        heldCertificate = new HeldCertificate.Builder()
            .addSubjectAlternativeName("localhost")
            .commonName("localhost")
            .build();

        HandshakeCertificates serverCertificates = new HandshakeCertificates.Builder()
            .heldCertificate(heldCertificate)
            .build();

        server = new MockWebServer();
        server.useHttps(serverCertificates.sslSocketFactory(), false);
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void shouldAllowInsecureTlsForLocalDevelopment() throws Exception {
        server.enqueue(new MockResponse()
            .setHeader("Content-Type", "application/json")
            .setBody("{\"id\":\"u1\",\"displayName\":\"Local User\"}"));

        OpenCloudClient client = OpenCloudClient.create(
            OpenCloudClientConfig.builder()
                .baseUrl(server.url("/").toString())
                .insecureSkipTlsVerification(true)
                .build()
        );

        Assert.assertEquals("Local User", client.graph().me().getProfileModel().getBody().getDisplayName());
    }

    @Test
    public void shouldAllowCustomTrustStoreObjects() throws Exception {
        server.enqueue(new MockResponse()
            .setHeader("Content-Type", "application/json")
            .setBody("{\"id\":\"u2\",\"displayName\":\"Trusted User\"}"));

        HandshakeCertificates clientCertificates = new HandshakeCertificates.Builder()
            .addTrustedCertificate(heldCertificate.certificate())
            .build();

        OpenCloudClient client = OpenCloudClient.create(
            OpenCloudClientConfig.builder()
                .baseUrl(server.url("/").toString())
                .sslSocketFactory(clientCertificates.sslSocketFactory(), clientCertificates.trustManager())
                .build()
        );

        Assert.assertEquals("Trusted User", client.graph().me().getProfileModel().getBody().getDisplayName());
    }
}
