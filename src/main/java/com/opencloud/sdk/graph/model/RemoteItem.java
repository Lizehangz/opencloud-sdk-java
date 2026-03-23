package com.opencloud.sdk.graph.model;

public class RemoteItem {
    private String id;
    private String name;
    private String createdDateTime;
    private String lastModifiedDateTime;
    private Long size;
    private FolderFacet folder;
    private FileFacet file;
    private ParentReference parentReference;
    private String webDavUrl;

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
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

    public ParentReference getParentReference() {
        return parentReference;
    }

    public void setParentReference(ParentReference parentReference) {
        this.parentReference = parentReference;
    }

    public String getWebDavUrl() {
        return webDavUrl;
    }

    public void setWebDavUrl(String webDavUrl) {
        this.webDavUrl = webDavUrl;
    }
}