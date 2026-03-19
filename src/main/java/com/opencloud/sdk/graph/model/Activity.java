package com.opencloud.sdk.graph.model;

public class Activity {
    private String id;
    private ActivityTimes times;
    private ActivityTemplate template;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActivityTimes getTimes() {
        return times;
    }

    public void setTimes(ActivityTimes times) {
        this.times = times;
    }

    public ActivityTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ActivityTemplate template) {
        this.template = template;
    }
}
