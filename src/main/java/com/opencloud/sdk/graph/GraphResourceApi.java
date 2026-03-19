package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;

import java.io.IOException;

abstract class GraphResourceApi {
    protected final GraphApi graphApi;

    GraphResourceApi(GraphApi graphApi) {
        this.graphApi = graphApi;
    }

    protected ApiResponse<JsonNode> json(GraphOperation operation, ApiRequest request) throws IOException {
        return graphApi.execute(operation, request);
    }

    protected <T> ApiResponse<T> model(GraphOperation operation, ApiRequest request, Class<T> responseType) throws IOException {
        return graphApi.execute(operation, request, responseType);
    }

    protected <T> ApiResponse<T> model(GraphOperation operation, ApiRequest request, TypeReference<T> responseType) throws IOException {
        return graphApi.execute(operation, request, responseType);
    }

    protected ApiResponse<Void> empty(GraphOperation operation, ApiRequest request) throws IOException {
        return graphApi.executeWithoutBody(operation, request);
    }

    protected ApiResponse<byte[]> bytes(GraphOperation operation, ApiRequest request) throws IOException {
        return graphApi.executeBytes(operation, request);
    }
}
