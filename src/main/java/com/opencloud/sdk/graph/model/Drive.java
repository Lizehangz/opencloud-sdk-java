package com.opencloud.sdk.graph.model;

import java.util.Objects;

public class Drive {
    private String id;
    private String name;
    private String description;
    private String driveType;
    private Quota quota;
    private DriveItem root;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

    public DriveItem getRoot() {
        return root;
    }

    public void setRoot(DriveItem root) {
        this.root = root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Drive)) {
            return false;
        }
        Drive drive = (Drive) o;
        return Objects.equals(id, drive.id)
            && Objects.equals(name, drive.name)
            && Objects.equals(driveType, drive.driveType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, driveType);
    }

    @Override
    public String toString() {
        return "Drive{id='" + id + "', name='" + name + "', driveType='" + driveType + "'}";
    }
}
