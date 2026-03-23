package com.opencloud.sdk.transport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.OpenCloudApiError;
import com.opencloud.sdk.OpenCloudApiErrorDetail;
import com.opencloud.sdk.OpenCloudClientConfig;
import com.opencloud.sdk.OpenCloudException;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public final class RequestExecutor {
    private static final MediaType JSON = MediaType.parse("application/json");

    private final OpenCloudClientConfig config;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public RequestExecutor(OpenCloudClientConfig config, OkHttpClient client, ObjectMapper objectMapper) {
        this.config = config;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public <T> ApiResponse<T> executeGraph(String method, String path, ApiRequest request, Class<T> responseType) throws IOException {
        return execute(method, config.getGraphBasePath() + path, request, responseType);
    }

    public <T> ApiResponse<T> executeGraph(String method, String path, ApiRequest request, TypeReference<T> responseType) throws IOException {
        return execute(method, config.getGraphBasePath() + path, request, responseType);
    }

    public <T> ApiResponse<T> executeOcs(String method, String relativePath, ApiRequest request, Class<T> responseType) throws IOException {
        return execute(method, config.getOcsBasePath() + normalizeRelative(relativePath), request, responseType);
    }

    public <T> ApiResponse<T> executeOcs(String method, String relativePath, ApiRequest request, TypeReference<T> responseType) throws IOException {
        return execute(method, config.getOcsBasePath() + normalizeRelative(relativePath), request, responseType);
    }

    public <T> ApiResponse<T> executeWebDav(String method, String path, ApiRequest request, Class<T> responseType) throws IOException {
        return execute(method, path, request, responseType);
    }

    public <T> ApiResponse<T> executeWebDav(String method, String path, ApiRequest request, TypeReference<T> responseType) throws IOException {
        return execute(method, path, request, responseType);
    }

    public String getBaseGraphUrl() {
        return config.getBaseUrl() + config.getGraphBasePath();
    }

    public <T> ApiResponse<T> execute(String method, String path, ApiRequest request, Class<T> responseType) throws IOException {
        String resolvedPath = resolvePath(path, request);
        HttpUrl url = buildUrl(resolvedPath, request);
        RequestBody requestBody = createRequestBody(method, request);

        Request.Builder requestBuilder = new Request.Builder()
            .url(url)
            .method(method, requestBody);

        if (request.getAccept() != null && !request.getAccept().trim().isEmpty()) {
            requestBuilder.header("Accept", request.getAccept());
        }

        for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
            requestBuilder.header(header.getKey(), header.getValue());
        }

        if (config.getAuthProvider() != null) {
            config.getAuthProvider().apply(requestBuilder);
        }

        Response response = client.newCall(requestBuilder.build()).execute();
        try {
            ResponseBody responseBody = response.body();
            byte[] bytes = responseBody != null ? responseBody.bytes() : new byte[0];

            if (!response.isSuccessful()) {
                throw buildException(response.code(), response.message(), bytes);
            }

            try {
                T body = deserialize(bytes, responseType);
                return new ApiResponse<T>(response.code(), response.headers(), body);
            } catch (IOException e) {
                throw buildSuccessParseException(response.code(), response.message(), bytes, e);
            }
        } finally {
            response.close();
        }
    }

    public <T> ApiResponse<T> execute(String method, String path, ApiRequest request, TypeReference<T> responseType) throws IOException {
        String resolvedPath = resolvePath(path, request);
        HttpUrl url = buildUrl(resolvedPath, request);
        RequestBody requestBody = createRequestBody(method, request);

        Request.Builder requestBuilder = new Request.Builder()
            .url(url)
            .method(method, requestBody);

        if (request.getAccept() != null && !request.getAccept().trim().isEmpty()) {
            requestBuilder.header("Accept", request.getAccept());
        }

        for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
            requestBuilder.header(header.getKey(), header.getValue());
        }

        if (config.getAuthProvider() != null) {
            config.getAuthProvider().apply(requestBuilder);
        }

        Response response = client.newCall(requestBuilder.build()).execute();
        try {
            ResponseBody responseBody = response.body();
            byte[] bytes = responseBody != null ? responseBody.bytes() : new byte[0];

            if (!response.isSuccessful()) {
                throw buildException(response.code(), response.message(), bytes);
            }

            try {
                T body = deserialize(bytes, responseType);
                return new ApiResponse<T>(response.code(), response.headers(), body);
            } catch (IOException e) {
                throw buildSuccessParseException(response.code(), response.message(), bytes, e);
            }
        } finally {
            response.close();
        }
    }

    private HttpUrl buildUrl(String path, ApiRequest request) {
        HttpUrl parsed = HttpUrl.parse(config.getBaseUrl() + path);
        if (parsed == null) {
            throw new IllegalArgumentException("Unable to build request URL for path: " + path);
        }

        HttpUrl.Builder builder = parsed.newBuilder();
        for (Map.Entry<String, String> query : request.getQueryParams().entrySet()) {
            builder.addQueryParameter(query.getKey(), query.getValue());
        }
        return builder.build();
    }

    private RequestBody createRequestBody(String method, ApiRequest request) throws IOException {
        if ("DELETE".equals(method) && request.getBody() == null && request.getBinaryBody() == null) {
            return null;
        }

        if (!allowsRequestBody(method)) {
            return null;
        }

        if (request.getBinaryBody() != null) {
            String contentType = request.getContentType() != null ? request.getContentType() : "application/octet-stream";
            return RequestBody.create(MediaType.parse(contentType), request.getBinaryBody());
        }

        if (request.getBody() == null) {
            return RequestBody.create(null, new byte[0]);
        }

        String contentType = request.getContentType() != null ? request.getContentType() : "application/json";
        if (request.getBody() instanceof String && !"application/json".equalsIgnoreCase(contentType)) {
            return RequestBody.create(MediaType.parse(contentType), (String) request.getBody());
        }

        byte[] body = objectMapper.writeValueAsBytes(request.getBody());
        return RequestBody.create(JSON, body);
    }

    private boolean allowsRequestBody(String method) {
        return "POST".equals(method)
            || "PUT".equals(method)
            || "PATCH".equals(method)
            || "PROPFIND".equals(method)
            || "PROPPATCH".equals(method)
            || "MKCOL".equals(method)
            || "COPY".equals(method)
            || "MOVE".equals(method)
            || "DELETE".equals(method);
    }

    private String resolvePath(String path, ApiRequest request) {
        String resolved = path;
        for (Map.Entry<String, String> entry : request.getPathParams().entrySet()) {
            resolved = resolved.replace("{" + entry.getKey() + "}", urlEncode(entry.getValue()));
        }
        return resolved;
    }

    private String normalizeRelative(String path) {
        if (path == null || path.trim().isEmpty()) {
            return "";
        }
        return path.startsWith("/") ? path : "/" + path;
    }

    private String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not available", e);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T deserialize(byte[] body, Class<T> responseType) throws IOException {
        if (responseType == null || responseType == Void.class || body.length == 0) {
            return null;
        }
        if (responseType == byte[].class) {
            return (T) body;
        }
        return objectMapper.readValue(body, responseType);
    }

    private <T> T deserialize(byte[] body, TypeReference<T> responseType) throws IOException {
        if (responseType == null || body.length == 0) {
            return null;
        }
        return objectMapper.readValue(body, responseType);
    }

    private OpenCloudException buildSuccessParseException(int statusCode, String statusMessage, byte[] bytes, IOException cause) throws IOException {
        String responseBody = new String(bytes, "UTF-8");
        String preview = responseBody;
        if (preview.length() > 200) {
            preview = preview.substring(0, 200) + "...";
        }

        String message = "OpenCloud request returned " + statusCode + " " + statusMessage + " but the body could not be parsed";
        if (preview != null && !preview.trim().isEmpty()) {
            message = message + ": " + preview;
        }

        OpenCloudException exception = new OpenCloudException(message, statusCode, responseBody, null);
        exception.initCause(cause);
        return exception;
    }
    private OpenCloudException buildException(int statusCode, String statusMessage, byte[] bytes) throws IOException {
        String responseBody = new String(bytes, "UTF-8");
        OpenCloudApiError apiError = parseApiError(bytes);
        String message = "OpenCloud request failed: " + statusCode + " " + statusMessage;

        if (apiError != null && apiError.getMessage() != null && !apiError.getMessage().trim().isEmpty()) {
            message = message + " - " + apiError.getMessage();
        }

        return new OpenCloudException(message, statusCode, responseBody, apiError);
    }

    private OpenCloudApiError parseApiError(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        try {
            JsonNode root = objectMapper.readTree(bytes);
            if (root == null || root.isMissingNode()) {
                return null;
            }

            if (root.has("error")) {
                return parseODataError(root.get("error"));
            }

            if (root.has("ocs") && root.get("ocs").has("meta")) {
                return parseOcsError(root.get("ocs").get("meta"));
            }
        } catch (IOException ignored) {
            return null;
        }

        return null;
    }

    private OpenCloudApiError parseODataError(JsonNode errorNode) {
        if (errorNode == null || errorNode.isMissingNode()) {
            return null;
        }

        OpenCloudApiError error = new OpenCloudApiError();
        error.setCode(textValue(errorNode, "code"));
        error.setMessage(textValue(errorNode, "message"));
        error.setTarget(textValue(errorNode, "target"));

        JsonNode detailsNode = errorNode.get("details");
        if (detailsNode != null && detailsNode.isArray()) {
            for (JsonNode detailNode : detailsNode) {
                OpenCloudApiErrorDetail detail = new OpenCloudApiErrorDetail();
                detail.setCode(textValue(detailNode, "code"));
                detail.setMessage(textValue(detailNode, "message"));
                detail.setTarget(textValue(detailNode, "target"));
                error.getDetails().add(detail);
            }
        }

        return error;
    }

    private OpenCloudApiError parseOcsError(JsonNode metaNode) {
        if (metaNode == null || metaNode.isMissingNode()) {
            return null;
        }

        OpenCloudApiError error = new OpenCloudApiError();
        error.setCode(textValue(metaNode, "statuscode"));
        error.setMessage(textValue(metaNode, "message"));
        error.setTarget(textValue(metaNode, "status"));
        return error;
    }

    private String textValue(JsonNode node, String fieldName) {
        JsonNode value = node.get(fieldName);
        if (value == null || value.isNull()) {
            return null;
        }
        return value.asText();
    }
}


