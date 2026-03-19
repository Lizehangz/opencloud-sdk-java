package com.opencloud.sdk.graph.model;

public class GroupCreateRequest {
    private String displayName;
    private String description;

    public static Builder builder() {
        return new Builder();
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

    public static final class Builder {
        private final GroupCreateRequest request = new GroupCreateRequest();

        public Builder displayName(String value) {
            request.setDisplayName(value);
            return this;
        }

        public Builder description(String value) {
            request.setDescription(value);
            return this;
        }

        public GroupCreateRequest build() {
            return request;
        }
    }
}
