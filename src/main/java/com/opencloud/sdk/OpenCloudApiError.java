package com.opencloud.sdk;

import java.util.ArrayList;
import java.util.List;

public class OpenCloudApiError {
    private String code;
    private String message;
    private String target;
    private List<OpenCloudApiErrorDetail> details = new ArrayList<OpenCloudApiErrorDetail>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<OpenCloudApiErrorDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OpenCloudApiErrorDetail> details) {
        this.details = details;
    }
}
