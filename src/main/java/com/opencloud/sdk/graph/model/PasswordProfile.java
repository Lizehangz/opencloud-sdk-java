package com.opencloud.sdk.graph.model;

public class PasswordProfile {
    private Boolean forceChangePasswordNextSignIn;
    private String password;

    public static Builder builder() {
        return new Builder();
    }

    public Boolean getForceChangePasswordNextSignIn() {
        return forceChangePasswordNextSignIn;
    }

    public void setForceChangePasswordNextSignIn(Boolean forceChangePasswordNextSignIn) {
        this.forceChangePasswordNextSignIn = forceChangePasswordNextSignIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static final class Builder {
        private final PasswordProfile value = new PasswordProfile();

        public Builder forceChangePasswordNextSignIn(boolean forceChangePasswordNextSignIn) {
            value.setForceChangePasswordNextSignIn(forceChangePasswordNextSignIn);
            return this;
        }

        public Builder password(String password) {
            value.setPassword(password);
            return this;
        }

        public PasswordProfile build() {
            return value;
        }
    }
}