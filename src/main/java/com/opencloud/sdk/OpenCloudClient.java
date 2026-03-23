package com.opencloud.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencloud.sdk.graph.GraphApi;
import com.opencloud.sdk.ocs.OcsApi;
import com.opencloud.sdk.transport.RequestExecutor;
import com.opencloud.sdk.webdav.WebDavApi;
import okhttp3.OkHttpClient;

public final class OpenCloudClient implements AutoCloseable {
    private final OpenCloudClientConfig config;
    private final OkHttpClient okHttpClient;
    private final GraphApi graphApi;
    private final OcsApi ocsApi;
    private final WebDavApi webDavApi;

    private OpenCloudClient(OpenCloudClientConfig config) {
        this.config = config;
        this.okHttpClient = config.createHttpClient();
        ObjectMapper objectMapper = config.getObjectMapper();
        RequestExecutor requestExecutor = new RequestExecutor(config, okHttpClient, objectMapper);
        this.graphApi = new GraphApi(requestExecutor);
        this.ocsApi = new OcsApi(requestExecutor);
        this.webDavApi = new WebDavApi(requestExecutor, config.getBaseUrl(), config.getWebDavRootPath());
    }

    public static OpenCloudClient create(OpenCloudClientConfig config) {
        return new OpenCloudClient(config);
    }

    public OpenCloudClientConfig getConfig() {
        return config;
    }

    public GraphApi graph() {
        return graphApi;
    }

    public OcsApi ocs() {
        return ocsApi;
    }

    public WebDavApi webDav() {
        return webDavApi;
    }

    @Override
    public void close() {
        okHttpClient.dispatcher().executorService().shutdown();
        okHttpClient.connectionPool().evictAll();
        if (okHttpClient.cache() != null) {
            try {
                okHttpClient.cache().close();
            } catch (Exception ignored) {
            }
        }
    }
}