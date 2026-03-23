package com.opencloud.sdk.graph.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class DriveItem {
    private String id;
    private String name;
    private String createdDateTime;
    private String lastModifiedDateTime;
    private String description;
    private String eTag;
    private Long size;
    private String webDavUrl;
    private ParentReference parentReference;
    private FolderFacet folder;
    private FileFacet file;
    private RemoteItem remoteItem;
    private SpecialFolder specialFolder;
    private List<Permission> permissions;

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

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("eTag")
    public String getETag() {
        return eTag;
    }

    @JsonProperty("eTag")
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getWebDavUrl() {
        return webDavUrl;
    }

    public void setWebDavUrl(String webDavUrl) {
        this.webDavUrl = webDavUrl;
    }

    public ParentReference getParentReference() {
        return parentReference;
    }

    public void setParentReference(ParentReference parentReference) {
        this.parentReference = parentReference;
    }

    public FolderFacet getFolder() {
        return folder;
    }

    public void setFolder(FolderFacet folder) {
        this.folder = folder;
    }

    public FileFacet getFile() {
        return file;
    }

    public void setFile(FileFacet file) {
        this.file = file;
    }

    public RemoteItem getRemoteItem() {
        return remoteItem;
    }

    public void setRemoteItem(RemoteItem remoteItem) {
        this.remoteItem = remoteItem;
    }

    public SpecialFolder getSpecialFolder() {
        return specialFolder;
    }

    public void setSpecialFolder(SpecialFolder specialFolder) {
        this.specialFolder = specialFolder;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DriveItem)) {
            return false;
        }
        DriveItem driveItem = (DriveItem) o;
        return Objects.equals(id, driveItem.id)
            && Objects.equals(name, driveItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "DriveItem{id='" + id + "', name='" + name + "'}";
    }
}
