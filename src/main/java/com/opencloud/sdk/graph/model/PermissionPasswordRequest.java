package com.opencloud.sdk.graph.model;

public class PermissionPasswordRequest {
    private String password;

    public static Builder builder() {
        return new Builder();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static final class Builder {
        private final PermissionPasswordRequest value = new PermissionPasswordRequest();

        public Builder password(String password) {
            value.setPassword(password);
            return this;
        }

        public PermissionPasswordRequest build() {
            return value;
        }
    }
}
