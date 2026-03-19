package com.opencloud.sdk.graph.model;

public class DriveCreateRequest {
    private String name;
    private String description;
    private Quota quota;

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

    public static final class Builder {
        private final DriveCreateRequest request = new DriveCreateRequest();

        public Builder name(String value) {
            request.setName(value);
            return this;
        }

        public Builder description(String value) {
            request.setDescription(value);
            return this;
        }

        public Builder quota(Quota value) {
            request.setQuota(value);
            return this;
        }

        public DriveCreateRequest build() {
            return request;
        }
    }
}
