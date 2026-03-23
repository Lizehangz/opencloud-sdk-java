package com.opencloud.sdk.graph.model;

import java.util.Objects;

public class Invitation {
    private String id;
    private String invitedUserDisplayName;
    private String invitedUserEmailAddress;
    private String inviteRedirectUrl;
    private String inviteRedeemUrl;
    private Boolean sendInvitationMessage;
    private String status;
    private User invitedUser;
    private String invitedUserType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvitedUserDisplayName() {
        return invitedUserDisplayName;
    }

    public void setInvitedUserDisplayName(String invitedUserDisplayName) {
        this.invitedUserDisplayName = invitedUserDisplayName;
    }

    public String getInvitedUserEmailAddress() {
        return invitedUserEmailAddress;
    }

    public void setInvitedUserEmailAddress(String invitedUserEmailAddress) {
        this.invitedUserEmailAddress = invitedUserEmailAddress;
    }

    public String getInviteRedirectUrl() {
        return inviteRedirectUrl;
    }

    public void setInviteRedirectUrl(String inviteRedirectUrl) {
        this.inviteRedirectUrl = inviteRedirectUrl;
    }

    public String getInviteRedeemUrl() {
        return inviteRedeemUrl;
    }

    public void setInviteRedeemUrl(String inviteRedeemUrl) {
        this.inviteRedeemUrl = inviteRedeemUrl;
    }

    public Boolean getSendInvitationMessage() {
        return sendInvitationMessage;
    }

    public void setSendInvitationMessage(Boolean sendInvitationMessage) {
        this.sendInvitationMessage = sendInvitationMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getInvitedUser() {
        return invitedUser;
    }

    public void setInvitedUser(User invitedUser) {
        this.invitedUser = invitedUser;
    }

    public String getInvitedUserType() {
        return invitedUserType;
    }

    public void setInvitedUserType(String invitedUserType) {
        this.invitedUserType = invitedUserType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invitation)) {
            return false;
        }
        Invitation that = (Invitation) o;
        return Objects.equals(id, that.id)
            && Objects.equals(invitedUserDisplayName, that.invitedUserDisplayName)
            && Objects.equals(invitedUserEmailAddress, that.invitedUserEmailAddress)
            && Objects.equals(inviteRedeemUrl, that.inviteRedeemUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invitedUserDisplayName, invitedUserEmailAddress, inviteRedeemUrl);
    }

    @Override
    public String toString() {
        return "Invitation{id='" + id + "', invitedUserDisplayName='" + invitedUserDisplayName + "'}";
    }
}
