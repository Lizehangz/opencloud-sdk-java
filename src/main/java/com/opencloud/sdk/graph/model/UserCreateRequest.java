package com.opencloud.sdk.graph.model;

import java.util.List;

public class UserCreateRequest {
    private Boolean accountEnabled;
    private String displayName;
    private List<ObjectIdentity> identities;
    private String mail;
    private String onPremisesSamAccountName;
    private PasswordProfile passwordProfile;
    private String surname;
    private String givenName;
    private String preferredLanguage;

    public static Builder builder() {
        return new Builder();
    }

    public Boolean getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<ObjectIdentity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<ObjectIdentity> identities) {
        this.identities = identities;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOnPremisesSamAccountName() {
        return onPremisesSamAccountName;
    }

    public void setOnPremisesSamAccountName(String onPremisesSamAccountName) {
        this.onPremisesSamAccountName = onPremisesSamAccountName;
    }

    public PasswordProfile getPasswordProfile() {
        return passwordProfile;
    }

    public void setPasswordProfile(PasswordProfile passwordProfile) {
        this.passwordProfile = passwordProfile;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public static final class Builder {
        private final UserCreateRequest request = new UserCreateRequest();

        public Builder accountEnabled(boolean value) {
            request.setAccountEnabled(value);
            return this;
        }

        public Builder displayName(String value) {
            request.setDisplayName(value);
            return this;
        }

        public Builder identities(List<ObjectIdentity> value) {
            request.setIdentities(value);
            return this;
        }

        public Builder mail(String value) {
            request.setMail(value);
            return this;
        }

        public Builder onPremisesSamAccountName(String value) {
            request.setOnPremisesSamAccountName(value);
            return this;
        }

        public Builder passwordProfile(PasswordProfile value) {
            request.setPasswordProfile(value);
            return this;
        }

        public Builder surname(String value) {
            request.setSurname(value);
            return this;
        }

        public Builder givenName(String value) {
            request.setGivenName(value);
            return this;
        }

        public Builder preferredLanguage(String value) {
            request.setPreferredLanguage(value);
            return this;
        }

        public UserCreateRequest build() {
            return request;
        }
    }
}