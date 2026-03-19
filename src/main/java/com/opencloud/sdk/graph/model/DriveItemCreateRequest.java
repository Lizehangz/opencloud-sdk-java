package com.opencloud.sdk.graph.model;

public class DriveItemCreateRequest {
    private String name;
    private RemoteItem remoteItem;

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RemoteItem getRemoteItem() {
        return remoteItem;
    }

    public void setRemoteItem(RemoteItem remoteItem) {
        this.remoteItem = remoteItem;
    }

    public static final class Builder {
        private final DriveItemCreateRequest request = new DriveItemCreateRequest();

        public Builder name(String value) {
            request.setName(value);
            return this;
        }

        public Builder remoteItem(RemoteItem value) {
            request.setRemoteItem(value);
            return this;
        }

        public DriveItemCreateRequest build() {
            return request;
        }
    }
}
