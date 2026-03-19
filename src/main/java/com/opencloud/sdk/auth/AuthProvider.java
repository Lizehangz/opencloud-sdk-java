package com.opencloud.sdk.auth;

import okhttp3.Request;

public interface AuthProvider {
    void apply(Request.Builder requestBuilder);
}
