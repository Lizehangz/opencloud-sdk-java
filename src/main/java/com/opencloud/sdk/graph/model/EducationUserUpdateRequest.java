package com.opencloud.sdk.graph.model;

import java.util.List;

public class EducationUserUpdateRequest {
    private String displayName;
    private String mail;
    private String givenName;
    private String surname;
    private String onPremisesSamAccountName;
    private String externalId;
    private String primaryRole;
    private List<ObjectIdentity> identities;

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

    public String getOnPremisesSamAccountName() {
        return onPremisesSamAccountName;
    }

    public void setOnPremisesSamAccountName(String onPremisesSamAccountName) {
        this.onPremisesSamAccountName = onPremisesSamAccountName;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }

    public List<ObjectIdentity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<ObjectIdentity> identities) {
        this.identities = identities;
    }

    public static final class Builder {
        private final EducationUserUpdateRequest request = new EducationUserUpdateRequest();

        public Builder displayName(String value) {
            request.setDisplayName(value);
            return this;
        }

        public Builder mail(String value) {
            request.setMail(value);
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

        public Builder onPremisesSamAccountName(String value) {
            request.setOnPremisesSamAccountName(value);
            return this;
        }

        public Builder externalId(String value) {
            request.setExternalId(value);
            return this;
        }

        public Builder primaryRole(String value) {
            request.setPrimaryRole(value);
            return this;
        }

        public Builder identities(List<ObjectIdentity> value) {
            request.setIdentities(value);
            return this;
        }

        public EducationUserUpdateRequest build() {
            return request;
        }
    }
}