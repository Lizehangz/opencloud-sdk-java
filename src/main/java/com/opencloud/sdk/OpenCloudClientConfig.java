package com.opencloud.sdk;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencloud.sdk.auth.AuthProvider;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public final class OpenCloudClientConfig {
    private final String baseUrl;
    private final String graphBasePath;
    private final String ocsBasePath;
    private final String webDavRootPath;
    private final AuthProvider authProvider;
    private final ObjectMapper objectMapper;
    private final OkHttpClient okHttpClient;
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

        return new OkHttpClient.Builder()
            .connectTimeout(connectTimeoutMillis, TimeUnit.MILLISECONDS)
            .readTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS)
            .writeTimeout(writeTimeoutMillis, TimeUnit.MILLISECONDS)
            .build();
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

    public static final class Builder {
        private String baseUrl;
        private String graphBasePath = "/graph";
        private String ocsBasePath = "/ocs/v2.php";
        private String webDavRootPath = "/remote.php/dav";
        private AuthProvider authProvider;
        private ObjectMapper objectMapper;
        private OkHttpClient okHttpClient;
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
