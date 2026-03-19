package com.opencloud.sdk.graph.model;

import java.util.Objects;

public class DriveItem {
    private String id;
    private String name;
    private ParentReference parentReference;
    private FolderFacet folder;
    private FileFacet file;
    private RemoteItem remoteItem;
    private SpecialFolder specialFolder;

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
