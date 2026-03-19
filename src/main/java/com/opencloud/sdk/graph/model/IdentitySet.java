package com.opencloud.sdk.graph.model;

public class IdentitySet {
    private Identity user;
    private Identity application;
    private Identity group;

    public Identity getUser() {
        return user;
    }

    public void setUser(Identity user) {
        this.user = user;
    }

    public Identity getApplication() {
        return application;
    }

    public void setApplication(Identity application) {
        this.application = application;
    }

    public Identity getGroup() {
        return group;
    }

    public void setGroup(Identity group) {
        this.group = group;
    }
}
