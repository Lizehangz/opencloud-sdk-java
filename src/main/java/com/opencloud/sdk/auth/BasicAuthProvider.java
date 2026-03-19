package com.opencloud.sdk.auth;

import okhttp3.Credentials;
import okhttp3.Request;

public final class BasicAuthProvider implements AuthProvider {
    private final String username;
    private final String password;

    public BasicAuthProvider(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void apply(Request.Builder requestBuilder) {
        requestBuilder.header("Authorization", Credentials.basic(username, password));
    }
}
