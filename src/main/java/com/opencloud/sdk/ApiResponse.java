package com.opencloud.sdk;

import okhttp3.Headers;

public final class ApiResponse<T> {
    private final int statusCode;
    private final Headers headers;
    private final T body;

    public ApiResponse(int statusCode, Headers headers, T body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Headers getHeaders() {
        return headers;
    }

    public T getBody() {
        return body;
    }
}
