package com.opencloud.sdk.webdav;

import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.transport.RequestExecutor;

import java.io.IOException;

public final class WebDavApi {
    private final RequestExecutor requestExecutor;
    private final String baseUrl;
    private final String rootPath;

    public WebDavApi(RequestExecutor requestExecutor, String baseUrl, String rootPath) {
        this.requestExecutor = requestExecutor;
        this.baseUrl = baseUrl;
        this.rootPath = rootPath;
    }

    public ApiResponse<byte[]> download(String relativePath) throws IOException {
        return requestExecutor.executeWebDav("GET", absolutePath(relativePath), ApiRequest.builder().accept("*/*").build(), byte[].class);
    }

    public ApiResponse<Void> upload(String relativePath, byte[] body, String contentType) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .binaryBody(body)
            .contentType(contentType)
            .accept("*/*")
            .build();
        return requestExecutor.executeWebDav("PUT", absolutePath(relativePath), request, Void.class);
    }

    public ApiResponse<Void> delete(String relativePath) throws IOException {
        return requestExecutor.executeWebDav("DELETE", absolutePath(relativePath), ApiRequest.builder().accept("*/*").build(), Void.class);
    }

    public ApiResponse<Void> makeCollection(String relativePath) throws IOException {
        return requestExecutor.executeWebDav("MKCOL", absolutePath(relativePath), ApiRequest.builder().accept("*/*").build(), Void.class);
    }

    public ApiResponse<byte[]> propFind(String relativePath, String xmlBody, String depth) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .body(xmlBody)
            .contentType("application/xml")
            .accept("application/xml")
            .header("Depth", depth)
            .build();
        return requestExecutor.executeWebDav("PROPFIND", absolutePath(relativePath), request, byte[].class);
    }

    public ApiResponse<Void> copy(String sourceRelativePath, String destinationRelativePath, boolean overwrite) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .header("Destination", baseUrl + absolutePath(destinationRelativePath))
            .header("Overwrite", overwrite ? "T" : "F")
            .accept("*/*")
            .build();
        return requestExecutor.executeWebDav("COPY", absolutePath(sourceRelativePath), request, Void.class);
    }

    public ApiResponse<Void> move(String sourceRelativePath, String destinationRelativePath, boolean overwrite) throws IOException {
        ApiRequest request = ApiRequest.builder()
            .header("Destination", baseUrl + absolutePath(destinationRelativePath))
            .header("Overwrite", overwrite ? "T" : "F")
            .accept("*/*")
            .build();
        return requestExecutor.executeWebDav("MOVE", absolutePath(sourceRelativePath), request, Void.class);
    }

    public ApiResponse<byte[]> execute(String method, String relativePath, ApiRequest request) throws IOException {
        return requestExecutor.executeWebDav(method, absolutePath(relativePath), request, byte[].class);
    }

    public <T> ApiResponse<T> execute(String method, String relativePath, ApiRequest request, Class<T> responseType) throws IOException {
        return requestExecutor.executeWebDav(method, absolutePath(relativePath), request, responseType);
    }

    private String absolutePath(String relativePath) {
        if (relativePath == null || relativePath.trim().isEmpty()) {
            return rootPath;
        }
        if (relativePath.startsWith("/")) {
            return rootPath + relativePath;
        }
        return rootPath + "/" + relativePath;
    }
}
