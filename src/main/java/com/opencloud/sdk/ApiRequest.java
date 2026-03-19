package com.opencloud.sdk;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ApiRequest {
    private final Map<String, String> pathParams;
    private final Map<String, String> queryParams;
    private final Map<String, String> headers;
    private final Object body;
    private final byte[] binaryBody;
    private final String contentType;
    private final String accept;

    private ApiRequest(Builder builder) {
        this.pathParams = Collections.unmodifiableMap(new LinkedHashMap<String, String>(builder.pathParams));
        this.queryParams = Collections.unmodifiableMap(new LinkedHashMap<String, String>(builder.queryParams));
        this.headers = Collections.unmodifiableMap(new LinkedHashMap<String, String>(builder.headers));
        this.body = builder.body;
        this.binaryBody = builder.binaryBody;
        this.contentType = builder.contentType;
        this.accept = builder.accept;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Object getBody() {
        return body;
    }

    public byte[] getBinaryBody() {
        return binaryBody;
    }

    public String getContentType() {
        return contentType;
    }

    public String getAccept() {
        return accept;
    }

    public static final class Builder {
        private final Map<String, String> pathParams = new LinkedHashMap<String, String>();
        private final Map<String, String> queryParams = new LinkedHashMap<String, String>();
        private final Map<String, String> headers = new LinkedHashMap<String, String>();
        private Object body;
        private byte[] binaryBody;
        private String contentType;
        private String accept = "application/json";

        public Builder pathParam(String key, String value) {
            this.pathParams.put(key, value);
            return this;
        }

        public Builder queryParam(String key, String value) {
            this.queryParams.put(key, value);
            return this;
        }

        public Builder header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder body(Object body) {
            this.body = body;
            this.binaryBody = null;
            return this;
        }

        public Builder binaryBody(byte[] binaryBody) {
            this.binaryBody = binaryBody;
            this.body = null;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder accept(String accept) {
            this.accept = accept;
            return this;
        }

        public ApiRequest build() {
            return new ApiRequest(this);
        }
    }
}
