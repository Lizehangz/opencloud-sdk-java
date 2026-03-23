package com.opencloud.sdk.graph.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Identity {
    private String id;
    private String displayName;
    private String userType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("@libre.graph.userType")
    public String getUserType() {
        return userType;
    }

    @JsonProperty("@libre.graph.userType")
    public void setUserType(String userType) {
        this.userType = userType;
    }
}
