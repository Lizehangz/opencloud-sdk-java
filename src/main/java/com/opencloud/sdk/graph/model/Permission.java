package com.opencloud.sdk.graph.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Permission {
    private String id;
    private SharingLink link;
    private List<String> roles;
    private IdentitySet grantedTo;
    private IdentitySet grantedToV2;
    private Boolean hasPassword;
    private String expirationDateTime;
    private String createdDateTime;
    private List<String> actions;
    private SharingInvitation invitation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SharingLink getLink() {
        return link;
    }

    public void setLink(SharingLink link) {
        this.link = link;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public IdentitySet getGrantedTo() {
        return grantedTo;
    }

    public void setGrantedTo(IdentitySet grantedTo) {
        this.grantedTo = grantedTo;
    }

    public IdentitySet getGrantedToV2() {
        return grantedToV2;
    }

    public void setGrantedToV2(IdentitySet grantedToV2) {
        this.grantedToV2 = grantedToV2;
    }

    public Boolean getHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(Boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @JsonProperty("@libre.graph.permissions.actions")
    public List<String> getActions() {
        return actions;
    }

    @JsonProperty("@libre.graph.permissions.actions")
    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public SharingInvitation getInvitation() {
        return invitation;
    }

    public void setInvitation(SharingInvitation invitation) {
        this.invitation = invitation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Permission)) {
            return false;
        }
        Permission that = (Permission) o;
        return Objects.equals(id, that.id)
            && Objects.equals(link, that.link)
            && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, roles);
    }

    @Override
    public String toString() {
        return "Permission{id='" + id + "', roles=" + roles + "}";
    }
}
