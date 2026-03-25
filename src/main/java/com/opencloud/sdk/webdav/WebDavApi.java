package com.opencloud.sdk.webdav;

import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.transport.RequestExecutor;

import java.io.IOException;

public final class WebDavApi {
    private static final String LEGACY_ROOT_PATH = "/remote.php/dav";

    private final RequestExecutor requestExecutor;
    private final String baseUrl;
    private final String rootPath;

    public WebDavApi(RequestExecutor requestExecutor, String baseUrl, String rootPath) {
        this.requestExecutor = requestExecutor;
        this.baseUrl = baseUrl;
        this.rootPath = rootPath;
    }

    public ApiResponse<byte[]> download(String resourceId, String relativePath) throws IOException {
        return requestExecutor.executeWebDav("GET", absoluteResourcePath(resourceId, relativePath), ApiRequest.builder().accept("*/*").build(), byte[].class);
    }

    public ApiResponse<byte[]> getThumbnail(String resourceId, String relativePath, int width, int height) throws IOException {
        return getThumbnail(resourceId, relativePath, width, height, null);
    }

    public ApiResponse<byte[]> getThumbnail(String resourceId, String relativePath, int width, int height, ThumbnailOptions options) throws IOException {
        validateThumbnailDimension(width, "width");
        validateThumbnailDimension(height, "height");

        ApiRequest.Builder request = ApiRequest.builder()
            .accept("*/*")
            .queryParam("preview", "1")
            .queryParam("x", String.valueOf(width))
            .queryParam("y", String.valueOf(height));

        if (options != null) {
            if (options.getProcessor() != null && !options.getProcessor().trim().isEmpty()) {
                request.queryParam("processor", options.getProcessor().trim());
            }
            if (options.getScalingUp() != null) {
                request.queryParam("scalingup", booleanToFlag(options.getScalingUp()));
            }
            if (options.getAspectRatio() != null) {
                request.queryParam("a", booleanToFlag(options.getAspectRatio()));
            }
            if (options.getCacheKey() != null && !options.getCacheKey().trim().isEmpty()) {
                request.queryParam("c", options.getCacheKey().trim());
            }
        }

        return requestExecutor.executeWebDav("GET", absoluteResourcePath(resourceId, relativePath), request.build(), byte[].class);
    }

    public ApiResponse<Void> upload(String resourceId, String relativePath, byte[] body, String contentType) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .binaryBody(body)
            .contentType(contentType)
            .accept("*/*")
            .build();
        return requestExecutor.executeWebDav("PUT", absoluteResourcePath(resourceId, relativePath), request, Void.class);
    }

    public ApiResponse<Void> delete(String resourceId, String relativePath) throws IOException {
        return requestExecutor.executeWebDav("DELETE", absoluteResourcePath(resourceId, relativePath), ApiRequest.builder().accept("*/*").build(), Void.class);
    }

    public ApiResponse<Void> makeCollection(String resourceId, String relativePath) throws IOException {
        return requestExecutor.executeWebDav("MKCOL", absoluteResourcePath(resourceId, relativePath), ApiRequest.builder().accept("*/*").build(), Void.class);
    }

    public ApiResponse<byte[]> propFind(String resourceId, String relativePath, String xmlBody, String depth) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .body(xmlBody)
            .contentType("application/xml")
            .accept("application/xml")
            .header("Depth", depth)
            .build();
        return requestExecutor.executeWebDav("PROPFIND", absoluteResourcePath(resourceId, relativePath), request, byte[].class);
    }

    public ApiResponse<Void> copy(String resourceId, String sourceRelativePath, String destinationRelativePath, boolean overwrite) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .header("Destination", baseUrl + absoluteResourcePath(resourceId, destinationRelativePath))
            .header("Overwrite", overwrite ? "T" : "F")
            .accept("*/*")
            .build();
        return requestExecutor.executeWebDav("COPY", absoluteResourcePath(resourceId, sourceRelativePath), request, Void.class);
    }

    public ApiResponse<Void> move(String resourceId, String sourceRelativePath, String destinationRelativePath, boolean overwrite) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .header("Destination", baseUrl + absoluteResourcePath(resourceId, destinationRelativePath))
            .header("Overwrite", overwrite ? "T" : "F")
            .accept("*/*")
            .build();
        return requestExecutor.executeWebDav("MOVE", absoluteResourcePath(resourceId, sourceRelativePath), request, Void.class);
    }

    public ApiResponse<byte[]> execute(String resourceId, String method, String relativePath, ApiRequest request) throws IOException {
        return requestExecutor.executeWebDav(method, absoluteResourcePath(resourceId, relativePath), request, byte[].class);
    }

    public <T> ApiResponse<T> execute(String resourceId, String method, String relativePath, ApiRequest request, Class<T> responseType) throws IOException {
        return requestExecutor.executeWebDav(method, absoluteResourcePath(resourceId, relativePath), request, responseType);
    }

    @Deprecated
    public ApiResponse<byte[]> download(String relativePath) throws IOException {
        return requestExecutor.executeWebDav("GET", absoluteLegacyPath(relativePath), ApiRequest.builder().accept("*/*").build(), byte[].class);
    }

    @Deprecated
    public ApiResponse<Void> upload(String relativePath, byte[] body, String contentType) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .binaryBody(body)
            .contentType(contentType)
            .accept("*/*")
            .build();
        return requestExecutor.executeWebDav("PUT", absoluteLegacyPath(relativePath), request, Void.class);
    }

    @Deprecated
    public ApiResponse<Void> delete(String relativePath) throws IOException {
        return requestExecutor.executeWebDav("DELETE", absoluteLegacyPath(relativePath), ApiRequest.builder().accept("*/*").build(), Void.class);
    }

    @Deprecated
    public ApiResponse<Void> makeCollection(String relativePath) throws IOException {
        return requestExecutor.executeWebDav("MKCOL", absoluteLegacyPath(relativePath), ApiRequest.builder().accept("*/*").build(), Void.class);
    }

    @Deprecated
    public ApiResponse<byte[]> propFind(String relativePath, String xmlBody, String depth) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .body(xmlBody)
            .contentType("application/xml")
            .accept("application/xml")
            .header("Depth", depth)
            .build();
        return requestExecutor.executeWebDav("PROPFIND", absoluteLegacyPath(relativePath), request, byte[].class);
    }

    @Deprecated
    public ApiResponse<Void> copy(String sourceRelativePath, String destinationRelativePath, boolean overwrite) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .header("Destination", baseUrl + absoluteLegacyPath(destinationRelativePath))
            .header("Overwrite", overwrite ? "T" : "F")
            .accept("*/*")
            .build();
        return requestExecutor.executeWebDav("COPY", absoluteLegacyPath(sourceRelativePath), request, Void.class);
    }

    @Deprecated
    public ApiResponse<Void> move(String sourceRelativePath, String destinationRelativePath, boolean overwrite) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .header("Destination", baseUrl + absoluteLegacyPath(destinationRelativePath))
            .header("Overwrite", overwrite ? "T" : "F")
            .accept("*/*")
            .build();
        return requestExecutor.executeWebDav("MOVE", absoluteLegacyPath(sourceRelativePath), request, Void.class);
    }

    @Deprecated
    public ApiResponse<byte[]> execute(String method, String relativePath, ApiRequest request) throws IOException {
        return requestExecutor.executeWebDav(method, absoluteLegacyPath(relativePath), request, byte[].class);
    }

    @Deprecated
    public <T> ApiResponse<T> execute(String method, String relativePath, ApiRequest request, Class<T> responseType) throws IOException {
        return requestExecutor.executeWebDav(method, absoluteLegacyPath(relativePath), request, responseType);
    }

    private String absoluteResourcePath(String resourceId, String relativePath) {
        if (resourceId == null || resourceId.trim().isEmpty()) {
            throw new IllegalArgumentException("resourceId is required for WebDAV space operations");
        }

        String normalizedResourceId = trimSlashes(resourceId);
        if (relativePath == null || relativePath.trim().isEmpty()) {
            return rootPath + "/" + normalizedResourceId;
        }

        String normalizedRelativePath = relativePath.startsWith("/") ? relativePath : "/" + relativePath;
        return rootPath + "/" + normalizedResourceId + normalizedRelativePath;
    }

    private String absoluteLegacyPath(String relativePath) {
        if (relativePath == null || relativePath.trim().isEmpty()) {
            return LEGACY_ROOT_PATH;
        }
        if (relativePath.startsWith("/")) {
            return LEGACY_ROOT_PATH + relativePath;
        }
        return LEGACY_ROOT_PATH + "/" + relativePath;
    }

    private String trimSlashes(String value) {
        String normalized = value.trim();
        while (normalized.startsWith("/")) {
            normalized = normalized.substring(1);
        }
        while (normalized.endsWith("/") && normalized.length() > 1) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

    private void validateThumbnailDimension(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than 0");
        }
    }

    private String booleanToFlag(boolean value) {
        return value ? "1" : "0";
    }
}
