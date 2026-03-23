package com.opencloud.sdk.graph.model;

import java.util.List;
import java.util.Objects;

public class EducationUser extends User {
    private String externalId;
    private String primaryRole;
    private List<ObjectIdentity> identities;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EducationUser)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        EducationUser that = (EducationUser) o;
        return Objects.equals(externalId, that.externalId)
            && Objects.equals(primaryRole, that.primaryRole)
            && Objects.equals(identities, that.identities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), externalId, primaryRole, identities);
    }

    @Override
    public String toString() {
        return "EducationUser{id='" + getId() + "', displayName='" + getDisplayName() + "', mail='" + getMail() + "', primaryRole='" + primaryRole + "'}";
    }
}