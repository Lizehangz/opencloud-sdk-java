package com.opencloud.sdk.graph.model;

import java.util.Objects;

public class Invitation {
    private String id;
    private String invitedUserDisplayName;
    private String inviteRedeemUrl;
    private InviteRecipient invitedUserEmailAddress;

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

    public String getInviteRedeemUrl() {
        return inviteRedeemUrl;
    }

    public void setInviteRedeemUrl(String inviteRedeemUrl) {
        this.inviteRedeemUrl = inviteRedeemUrl;
    }

    public InviteRecipient getInvitedUserEmailAddress() {
        return invitedUserEmailAddress;
    }

    public void setInvitedUserEmailAddress(InviteRecipient invitedUserEmailAddress) {
        this.invitedUserEmailAddress = invitedUserEmailAddress;
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
            && Objects.equals(inviteRedeemUrl, that.inviteRedeemUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invitedUserDisplayName, inviteRedeemUrl);
    }

    @Override
    public String toString() {
        return "Invitation{id='" + id + "', invitedUserDisplayName='" + invitedUserDisplayName + "'}";
    }
}
