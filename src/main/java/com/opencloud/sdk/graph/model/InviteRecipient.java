package com.opencloud.sdk.graph.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InviteRecipient {
    private String email;
    private String objectId;
    private String type;

    public static InviteRecipient of(String email) {
        InviteRecipient recipient = new InviteRecipient();
        recipient.setEmail(email);
        return recipient;
    }

    public static InviteRecipient user(String objectId) {
        InviteRecipient recipient = new InviteRecipient();
        recipient.setObjectId(objectId);
        recipient.setType("user");
        return recipient;
    }

    public static InviteRecipient group(String objectId) {
        InviteRecipient recipient = new InviteRecipient();
        recipient.setObjectId(objectId);
        recipient.setType("group");
        return recipient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @JsonProperty("@libre.graph.recipient.type")
    public String getType() {
        return type;
    }

    @JsonProperty("@libre.graph.recipient.type")
    public void setType(String type) {
        this.type = type;
    }
}
