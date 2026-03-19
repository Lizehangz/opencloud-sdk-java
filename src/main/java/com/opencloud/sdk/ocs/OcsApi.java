package com.opencloud.sdk.ocs;

import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.transport.RequestExecutor;

import java.io.IOException;
import java.util.Map;

public final class OcsApi {
    private final RequestExecutor requestExecutor;

    public OcsApi(RequestExecutor requestExecutor) {
        this.requestExecutor = requestExecutor;
    }

    public ApiResponse<JsonNode> execute(String method, String relativePath, ApiRequest request) throws IOException {
        return requestExecutor.executeOcs(method, relativePath, withOcsHeaders(request), JsonNode.class);
    }

    public <T> ApiResponse<T> execute(String method, String relativePath, ApiRequest request, Class<T> responseType) throws IOException {
        return requestExecutor.executeOcs(method, relativePath, withOcsHeaders(request), responseType);
    }

    public ApiResponse<JsonNode> getCapabilities() throws IOException {
        return execute("GET", "/cloud/capabilities", ApiRequest.builder().build());
    }

    public ApiResponse<JsonNode> getUserSigningKey() throws IOException {
        return execute("GET", "/cloud/user/signing-key", ApiRequest.builder().build());
    }

    private ApiRequest withOcsHeaders(ApiRequest request) {
        ApiRequest.Builder builder = ApiRequest.builder()
            .accept(request.getAccept())
            .contentType(request.getContentType());

        if (request.getBinaryBody() != null) {
            builder.binaryBody(request.getBinaryBody());
        } else if (request.getBody() != null) {
            builder.body(request.getBody());
        }

        for (Map.Entry<String, String> entry : request.getPathParams().entrySet()) {
            builder.pathParam(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : request.getQueryParams().entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }
        builder.header("OCS-APIRequest", "true");
        return builder.build();
    }
}
