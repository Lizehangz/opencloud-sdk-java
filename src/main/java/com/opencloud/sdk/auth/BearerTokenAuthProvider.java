package com.opencloud.sdk.auth;

import okhttp3.Request;

public final class BearerTokenAuthProvider implements AuthProvider {
    private final String token;

    public BearerTokenAuthProvider(String token) {
        this.token = token;
    }

    @Override
    public void apply(Request.Builder requestBuilder) {
        requestBuilder.header("Authorization", "Bearer " + token);
    }
}
