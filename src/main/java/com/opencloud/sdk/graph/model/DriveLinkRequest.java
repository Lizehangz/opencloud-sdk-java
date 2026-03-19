package com.opencloud.sdk.graph.model;

public class DriveLinkRequest {
    private String type;
    private String displayName;
    private String expirationDateTime;

    public static Builder builder() {
        return new Builder();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public static final class Builder {
        private final DriveLinkRequest request = new DriveLinkRequest();

        public Builder type(String value) {
            request.setType(value);
            return this;
        }

        public Builder displayName(String value) {
            request.setDisplayName(value);
            return this;
        }

        public Builder expirationDateTime(String value) {
            request.setExpirationDateTime(value);
            return this;
        }

        public DriveLinkRequest build() {
            return request;
        }
    }
}
