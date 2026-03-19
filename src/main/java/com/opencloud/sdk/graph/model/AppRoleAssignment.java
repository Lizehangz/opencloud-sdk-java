package com.opencloud.sdk.graph.model;

public class AppRoleAssignment {
    private String id;
    private String appRoleId;
    private String principalId;
    private String principalDisplayName;
    private String principalType;
    private String resourceId;
    private String resourceDisplayName;

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppRoleId() {
        return appRoleId;
    }

    public void setAppRoleId(String appRoleId) {
        this.appRoleId = appRoleId;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getPrincipalDisplayName() {
        return principalDisplayName;
    }

    public void setPrincipalDisplayName(String principalDisplayName) {
        this.principalDisplayName = principalDisplayName;
    }

    public String getPrincipalType() {
        return principalType;
    }

    public void setPrincipalType(String principalType) {
        this.principalType = principalType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceDisplayName() {
        return resourceDisplayName;
    }

    public void setResourceDisplayName(String resourceDisplayName) {
        this.resourceDisplayName = resourceDisplayName;
    }

    public static final class Builder {
        private final AppRoleAssignment value = new AppRoleAssignment();

        public Builder appRoleId(String appRoleId) {
            value.setAppRoleId(appRoleId);
            return this;
        }

        public Builder principalId(String principalId) {
            value.setPrincipalId(principalId);
            return this;
        }

        public Builder resourceId(String resourceId) {
            value.setResourceId(resourceId);
            return this;
        }

        public AppRoleAssignment build() {
            return value;
        }
    }
}
