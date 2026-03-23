package com.opencloud.sdk.graph.model;

import java.util.List;
import java.util.Objects;

public class User {
    private String id;
    private Boolean accountEnabled;
    private String displayName;
    private Drive drive;
    private List<ObjectIdentity> identities;
    private String mail;
    private String onPremisesSamAccountName;
    private PasswordProfile passwordProfile;
    private String surname;
    private String givenName;
    private String preferredLanguage;
    private SignInActivity signInActivity;
    private String userType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public List<ObjectIdentity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<ObjectIdentity> identities) {
        this.identities = identities;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOnPremisesSamAccountName() {
        return onPremisesSamAccountName;
    }

    public void setOnPremisesSamAccountName(String onPremisesSamAccountName) {
        this.onPremisesSamAccountName = onPremisesSamAccountName;
    }

    public PasswordProfile getPasswordProfile() {
        return passwordProfile;
    }

    public void setPasswordProfile(PasswordProfile passwordProfile) {
        this.passwordProfile = passwordProfile;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public SignInActivity getSignInActivity() {
        return signInActivity;
    }

    public void setSignInActivity(SignInActivity signInActivity) {
        this.signInActivity = signInActivity;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
            && Objects.equals(displayName, user.displayName)
            && Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, mail);
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', displayName='" + displayName + "', mail='" + mail + "'}";
    }
}