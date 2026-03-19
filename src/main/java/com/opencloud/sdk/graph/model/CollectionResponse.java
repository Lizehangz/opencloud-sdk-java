package com.opencloud.sdk.graph.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CollectionResponse<T> {
    private List<T> value;

    @JsonProperty("@odata.nextLink")
    private String nextLink;

    public List<T> getValue() {
        return value;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }
}
