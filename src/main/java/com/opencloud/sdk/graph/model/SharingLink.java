package com.opencloud.sdk.graph.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SharingLink {
    private String type;
    private String displayName;
    private String webUrl;
    private Boolean quicklink;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("@libre.graph.displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("@libre.graph.displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("@libre.graph.quickLink")
    public Boolean getQuicklink() {
        return quicklink;
    }

    @JsonProperty("@libre.graph.quickLink")
    public void setQuicklink(Boolean quicklink) {
        this.quicklink = quicklink;
    }
}
