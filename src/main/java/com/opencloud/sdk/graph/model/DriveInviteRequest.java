package com.opencloud.sdk.graph.model;

import java.util.List;

public class DriveInviteRequest {
    private List<InviteRecipient> recipients;
    private List<String> roles;
    private Boolean requireSignIn;
    private Boolean sendInvitation;
    private String message;
    private String expirationDateTime;

    public static Builder builder() {
        return new Builder();
    }

    public List<InviteRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<InviteRecipient> recipients) {
        this.recipients = recipients;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Boolean getRequireSignIn() {
        return requireSignIn;
    }

    public void setRequireSignIn(Boolean requireSignIn) {
        this.requireSignIn = requireSignIn;
    }

    public Boolean getSendInvitation() {
        return sendInvitation;
    }

    public void setSendInvitation(Boolean sendInvitation) {
        this.sendInvitation = sendInvitation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public static final class Builder {
        private final DriveInviteRequest value = new DriveInviteRequest();

        public Builder recipients(List<InviteRecipient> recipients) {
            value.setRecipients(recipients);
            return this;
        }

        public Builder roles(List<String> roles) {
            value.setRoles(roles);
            return this;
        }

        public Builder requireSignIn(Boolean requireSignIn) {
            value.setRequireSignIn(requireSignIn);
            return this;
        }

        public Builder sendInvitation(Boolean sendInvitation) {
            value.setSendInvitation(sendInvitation);
            return this;
        }

        public Builder message(String message) {
            value.setMessage(message);
            return this;
        }

        public Builder expirationDateTime(String expirationDateTime) {
            value.setExpirationDateTime(expirationDateTime);
            return this;
        }

        public DriveInviteRequest build() {
            return value;
        }
    }
}
