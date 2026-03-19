package com.opencloud.sdk.graph.model;

import java.util.List;

public class AppRole {
    private String id;
    private String displayName;
    private String description;
    private List<String> allowedMemberTypes;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAllowedMemberTypes() {
        return allowedMemberTypes;
    }

    public void setAllowedMemberTypes(List<String> allowedMemberTypes) {
        this.allowedMemberTypes = allowedMemberTypes;
    }
}
