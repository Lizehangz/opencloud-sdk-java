package com.opencloud.sdk.graph.model;

import java.util.List;

public class TagAssignmentRequest {
    private String resourceId;
    private List<String> tags;

    public static Builder builder() {
        return new Builder();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static final class Builder {
        private final TagAssignmentRequest value = new TagAssignmentRequest();

        public Builder resourceId(String resourceId) {
            value.setResourceId(resourceId);
            return this;
        }

        public Builder tags(List<String> tags) {
            value.setTags(tags);
            return this;
        }

        public TagAssignmentRequest build() {
            return value;
        }
    }
}
