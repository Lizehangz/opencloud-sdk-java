package com.opencloud.sdk.graph.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReferenceRequest {
    @JsonProperty("@odata.id")
    private String odataId;

    public static ReferenceRequest of(String odataId) {
        ReferenceRequest request = new ReferenceRequest();
        request.setOdataId(odataId);
        return request;
    }

    public String getOdataId() {
        return odataId;
    }

    public void setOdataId(String odataId) {
        this.odataId = odataId;
    }
}
