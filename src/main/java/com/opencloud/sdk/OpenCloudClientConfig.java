package com.opencloud.sdk;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencloud.sdk.auth.AuthProvider;
import okhttp3.OkHttpClient;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public final class OpenCloudClientConfig {
    private final String baseUrl;
    private final String graphBasePath;
    private final String ocsBasePath;
    private final String webDavRootPath;
    private final AuthProvider authProvider;
    private final ObjectMapper objectMapper;
    private final OkHttpClient okHttpClient;
    private final SSLSocketFactory sslSocketFactory;
    private final X509TrustManager trustManager;
    private final HostnameVerifier hostnameVerifier;
    private final boolean insecureSkipTlsVerification;
    private final String trustStorePath;
    private final String trustStoreResource;
    private final char[] trustStorePassword;
    private final String trustStoreType;
    private final long connectTimeoutMillis;
    private final long readTimeoutMillis;
    private final long writeTimeoutMillis;

    private OpenCloudClientConfig(Builder builder) {
        this.baseUrl = trimTrailingSlash(builder.baseUrl);
        this.graphBasePath = normalizePath(builder.graphBasePath);
        this.ocsBasePath = normalizePath(builder.ocsBasePath);
        this.webDavRootPath = normalizePath(builder.webDavRootPath);
        this.authProvider = builder.authProvider;
        this.objectMapper = builder.objectMapper != null ? builder.objectMapper : defaultObjectMapper();
        this.okHttpClient = builder.okHttpClient;
        this.sslSocketFactory = builder.sslSocketFactory;
        this.trustManager = builder.trustManager;
        this.hostnameVerifier = builder.hostnameVerifier;
        this.insecureSkipTlsVerification = builder.insecureSkipTlsVerification;
        this.trustStorePath = builder.trustStorePath;
        this.trustStoreResource = builder.trustStoreResource;
        this.trustStorePassword = builder.trustStorePassword != null ? builder.trustStorePassword.clone() : null;
        this.trustStoreType = builder.trustStoreType;
        this.connectTimeoutMillis = builder.connectTimeoutMillis;
        this.readTimeoutMillis = builder.readTimeoutMillis;
        this.writeTimeoutMillis = builder.writeTimeoutMillis;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getGraphBasePath() {
        return graphBasePath;
    }

    public String getOcsBasePath() {
        return ocsBasePath;
    }

    public String getWebDavRootPath() {
        return webDavRootPath;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public OkHttpClient createHttpClient() {
        if (okHttpClient != null) {
            return okHttpClient;
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(connectTimeoutMillis, TimeUnit.MILLISECONDS)
            .readTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS)
            .writeTimeout(writeTimeoutMillis, TimeUnit.MILLISECONDS);

        return applySslSettings(builder).build();
    }

    private static String trimTrailingSlash(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("baseUrl is required");
        }

        String normalized = value.trim();
        while (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

    private static String normalizePath(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "";
        }

        String normalized = value.trim();
        if (!normalized.startsWith("/")) {
            normalized = "/" + normalized;
        }
        while (normalized.endsWith("/") && normalized.length() > 1) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

    private static ObjectMapper defaultObjectMapper() {
        return new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private OkHttpClient.Builder applySslSettings(OkHttpClient.Builder builder) {
        try {
            if (insecureSkipTlsVerification) {
                X509TrustManager insecureTrustManager = insecureTrustManager();
                builder.sslSocketFactory(newSslSocketFactory(insecureTrustManager), insecureTrustManager);
                builder.hostnameVerifier(insecureHostnameVerifier());
                return builder;
            }

            X509TrustManager resolvedTrustManager = trustManager;
            SSLSocketFactory resolvedSocketFactory = sslSocketFactory;

            if (resolvedSocketFactory == null && trustStorePath != null && !trustStorePath.trim().isEmpty()) {
                resolvedTrustManager = trustManagerFromTrustStore(openFileInputStream(trustStorePath), trustStorePassword, trustStoreType);
                resolvedSocketFactory = newSslSocketFactory(resolvedTrustManager);
            }

            if (resolvedSocketFactory == null && trustStoreResource != null && !trustStoreResource.trim().isEmpty()) {
                resolvedTrustManager = trustManagerFromTrustStore(openResourceInputStream(trustStoreResource), trustStorePassword, trustStoreType);
                resolvedSocketFactory = newSslSocketFactory(resolvedTrustManager);
            }

            if (resolvedSocketFactory != null && resolvedTrustManager != null) {
                builder.sslSocketFactory(resolvedSocketFactory, resolvedTrustManager);
            }

            if (hostnameVerifier != null) {
                builder.hostnameVerifier(hostnameVerifier);
            }

            return builder;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Failed to configure SSL for OpenCloud client", e);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load trust store for OpenCloud client", e);
        }
    }

    private static X509TrustManager trustManagerFromTrustStore(InputStream inputStream, char[] trustStorePassword, String trustStoreType)
        throws GeneralSecurityException, IOException {
        KeyStore keyStore = KeyStore.getInstance(trustStoreType != null ? trustStoreType : KeyStore.getDefaultType());
        try {
            keyStore.load(inputStream, trustStorePassword);
        } finally {
            inputStream.close();
        }

        TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        factory.init(keyStore);
        return findX509TrustManager(factory.getTrustManagers());
    }

    private static InputStream openFileInputStream(String trustStorePath) throws IOException {
        return new FileInputStream(trustStorePath);
    }

    private static InputStream openResourceInputStream(String trustStoreResource) throws IOException {
        String normalized = trustStoreResource.startsWith("/") ? trustStoreResource.substring(1) : trustStoreResource;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = contextClassLoader != null ? contextClassLoader.getResourceAsStream(normalized) : null;
        if (inputStream == null) {
            inputStream = OpenCloudClientConfig.class.getClassLoader().getResourceAsStream(normalized);
        }
        if (inputStream == null) {
            throw new IOException("Trust store resource not found: " + trustStoreResource);
        }
        return inputStream;
    }

    private static SSLSocketFactory newSslSocketFactory(X509TrustManager trustManager) throws GeneralSecurityException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        return sslContext.getSocketFactory();
    }

    private static X509TrustManager insecureTrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    private static HostnameVerifier insecureHostnameVerifier() {
        return (hostname, session) -> true;
    }

    private static X509TrustManager findX509TrustManager(TrustManager[] trustManagers) {
        for (TrustManager trustManager : trustManagers) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        throw new IllegalStateException("No X509TrustManager found");
    }

    public static final class Builder {
        private String baseUrl;
        private String graphBasePath = "/graph";
        private String ocsBasePath = "/ocs/v2.php";
        private String webDavRootPath = "/dav/spaces";
        private AuthProvider authProvider;
        private ObjectMapper objectMapper;
        private OkHttpClient okHttpClient;
        private SSLSocketFactory sslSocketFactory;
        private X509TrustManager trustManager;
        private HostnameVerifier hostnameVerifier;
        private boolean insecureSkipTlsVerification;
        private String trustStorePath;
        private String trustStoreResource;
        private char[] trustStorePassword;
        private String trustStoreType = KeyStore.getDefaultType();
        private long connectTimeoutMillis = 10_000L;
        private long readTimeoutMillis = 60_000L;
        private long writeTimeoutMillis = 60_000L;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder graphBasePath(String graphBasePath) {
            this.graphBasePath = graphBasePath;
            return this;
        }

        public Builder ocsBasePath(String ocsBasePath) {
            this.ocsBasePath = ocsBasePath;
            return this;
        }

        public Builder webDavRootPath(String webDavRootPath) {
            this.webDavRootPath = webDavRootPath;
            return this;
        }

        public Builder authProvider(AuthProvider authProvider) {
            this.authProvider = authProvider;
            return this;
        }

        public Builder objectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public Builder okHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sslSocketFactory, X509TrustManager trustManager) {
            this.sslSocketFactory = sslSocketFactory;
            this.trustManager = trustManager;
            return this;
        }

        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public Builder insecureSkipTlsVerification(boolean insecureSkipTlsVerification) {
            this.insecureSkipTlsVerification = insecureSkipTlsVerification;
            return this;
        }

        public Builder trustStore(String trustStorePath, String trustStorePassword) {
            this.trustStorePath = trustStorePath;
            this.trustStoreResource = null;
            this.trustStorePassword = trustStorePassword != null ? trustStorePassword.toCharArray() : null;
            return this;
        }

        public Builder trustStore(String trustStorePath, String trustStorePassword, String trustStoreType) {
            this.trustStorePath = trustStorePath;
            this.trustStoreResource = null;
            this.trustStorePassword = trustStorePassword != null ? trustStorePassword.toCharArray() : null;
            this.trustStoreType = trustStoreType;
            return this;
        }

        public Builder trustStoreResource(String trustStoreResource, String trustStorePassword) {
            this.trustStorePath = null;
            this.trustStoreResource = trustStoreResource;
            this.trustStorePassword = trustStorePassword != null ? trustStorePassword.toCharArray() : null;
            return this;
        }

        public Builder trustStoreResource(String trustStoreResource, String trustStorePassword, String trustStoreType) {
            this.trustStorePath = null;
            this.trustStoreResource = trustStoreResource;
            this.trustStorePassword = trustStorePassword != null ? trustStorePassword.toCharArray() : null;
            this.trustStoreType = trustStoreType;
            return this;
        }

        public Builder connectTimeoutMillis(long connectTimeoutMillis) {
            this.connectTimeoutMillis = connectTimeoutMillis;
            return this;
        }

        public Builder readTimeoutMillis(long readTimeoutMillis) {
            this.readTimeoutMillis = readTimeoutMillis;
            return this;
        }

        public Builder writeTimeoutMillis(long writeTimeoutMillis) {
            this.writeTimeoutMillis = writeTimeoutMillis;
            return this;
        }

        public OpenCloudClientConfig build() {
            return new OpenCloudClientConfig(this);
        }
    }
}

