package com.opencloud.sdk.graph.model;

public class InviteRecipient {
    private String email;

    public static InviteRecipient of(String email) {
        InviteRecipient recipient = new InviteRecipient();
        recipient.setEmail(email);
        return recipient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
