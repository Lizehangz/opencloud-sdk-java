package com.opencloud.sdk.graph.model;

public class UserUpdateRequest {
    private String displayName;
    private String mail;
    private String preferredLanguage;
    private String givenName;
    private String surname;

    public static Builder builder() {
        return new Builder();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static final class Builder {
        private final UserUpdateRequest request = new UserUpdateRequest();

        public Builder displayName(String value) {
            request.setDisplayName(value);
            return this;
        }

        public Builder mail(String value) {
            request.setMail(value);
            return this;
        }

        public Builder preferredLanguage(String value) {
            request.setPreferredLanguage(value);
            return this;
        }

        public Builder givenName(String value) {
            request.setGivenName(value);
            return this;
        }

        public Builder surname(String value) {
            request.setSurname(value);
            return this;
        }

        public UserUpdateRequest build() {
            return request;
        }
    }
}
