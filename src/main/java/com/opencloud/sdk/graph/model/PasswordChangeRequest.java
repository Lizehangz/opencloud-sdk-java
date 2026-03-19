package com.opencloud.sdk.graph.model;

public class PasswordChangeRequest {
    private String currentPassword;
    private String newPassword;

    public static Builder builder() {
        return new Builder();
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public static final class Builder {
        private final PasswordChangeRequest request = new PasswordChangeRequest();

        public Builder currentPassword(String value) {
            request.setCurrentPassword(value);
            return this;
        }

        public Builder newPassword(String value) {
            request.setNewPassword(value);
            return this;
        }

        public PasswordChangeRequest build() {
            return request;
        }
    }
}
