package com.opencloud.sdk.graph.model;

import java.util.List;
import java.util.Objects;

public class Permission {
    private String id;
    private SharingLink link;
    private List<String> roles;
    private IdentitySet grantedTo;

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
