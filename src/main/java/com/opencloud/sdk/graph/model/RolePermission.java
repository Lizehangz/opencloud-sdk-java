package com.opencloud.sdk.graph.model;

import java.util.List;

public class RolePermission {
    private List<String> allowedResourceActions;
    private String condition;

    public List<String> getAllowedResourceActions() {
        return allowedResourceActions;
    }

    public void setAllowedResourceActions(List<String> allowedResourceActions) {
        this.allowedResourceActions = allowedResourceActions;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
