package com.opencloud.sdk.graph.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RoleDefinition {
    private String id;
    private String displayName;
    private String description;
    private List<RolePermission> rolePermissions;
    private Integer weight;

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

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    @JsonProperty("@libre.graph.weight")
    public Integer getWeight() {
        return weight;
    }

    @JsonProperty("@libre.graph.weight")
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
