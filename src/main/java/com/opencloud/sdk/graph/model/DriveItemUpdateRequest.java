package com.opencloud.sdk.graph.model;

public class DriveItemUpdateRequest {
    private String name;

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {
        private final DriveItemUpdateRequest value = new DriveItemUpdateRequest();

        public Builder name(String name) {
            value.setName(name);
            return this;
        }

        public DriveItemUpdateRequest build() {
            return value;
        }
    }
}
