package com.opencloud.sdk.graph.model;

import java.util.Map;

public class ActivityTemplate {
    private String message;
    private Map<String, Object> variables;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
