package com.opencloud.sdk.graph.model;

public class PermissionUpdateRequest {
    private String expirationDateTime;
    private String roles;

    public static Builder builder() {
        return new Builder();
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public static final class Builder {
        private final PermissionUpdateRequest value = new PermissionUpdateRequest();

        public Builder expirationDateTime(String expirationDateTime) {
            value.setExpirationDateTime(expirationDateTime);
            return this;
        }

        public Builder roles(String roles) {
            value.setRoles(roles);
            return this;
        }

        public PermissionUpdateRequest build() {
            return value;
        }
    }
}
