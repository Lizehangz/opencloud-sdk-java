package com.opencloud.sdk.graph.model;

public class ObjectIdentity {
    private String issuer;
    private String issuerAssignedId;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuerAssignedId() {
        return issuerAssignedId;
    }

    public void setIssuerAssignedId(String issuerAssignedId) {
        this.issuerAssignedId = issuerAssignedId;
    }
}
