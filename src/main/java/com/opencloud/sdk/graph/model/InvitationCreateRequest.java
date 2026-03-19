package com.opencloud.sdk.graph.model;

public class InvitationCreateRequest {
    private InviteRecipient invitedUserEmailAddress;
    private String invitedUserDisplayName;
    private Boolean sendInvitationMessage;
    private String inviteRedirectUrl;

    public static Builder builder() {
        return new Builder();
    }

    public InviteRecipient getInvitedUserEmailAddress() {
        return invitedUserEmailAddress;
    }

    public void setInvitedUserEmailAddress(InviteRecipient invitedUserEmailAddress) {
        this.invitedUserEmailAddress = invitedUserEmailAddress;
    }

    public String getInvitedUserDisplayName() {
        return invitedUserDisplayName;
    }

    public void setInvitedUserDisplayName(String invitedUserDisplayName) {
        this.invitedUserDisplayName = invitedUserDisplayName;
    }

    public Boolean getSendInvitationMessage() {
        return sendInvitationMessage;
    }

    public void setSendInvitationMessage(Boolean sendInvitationMessage) {
        this.sendInvitationMessage = sendInvitationMessage;
    }

    public String getInviteRedirectUrl() {
        return inviteRedirectUrl;
    }

    public void setInviteRedirectUrl(String inviteRedirectUrl) {
        this.inviteRedirectUrl = inviteRedirectUrl;
    }

    public static final class Builder {
        private final InvitationCreateRequest request = new InvitationCreateRequest();

        public Builder invitedUserEmail(String email) {
            request.setInvitedUserEmailAddress(InviteRecipient.of(email));
            return this;
        }

        public Builder invitedUserDisplayName(String displayName) {
            request.setInvitedUserDisplayName(displayName);
            return this;
        }

        public Builder sendInvitationMessage(boolean sendInvitationMessage) {
            request.setSendInvitationMessage(sendInvitationMessage);
            return this;
        }

        public Builder inviteRedirectUrl(String inviteRedirectUrl) {
            request.setInviteRedirectUrl(inviteRedirectUrl);
            return this;
        }

        public InvitationCreateRequest build() {
            return request;
        }
    }
}
