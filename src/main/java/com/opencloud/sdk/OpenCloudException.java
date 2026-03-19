package com.opencloud.sdk;

public final class OpenCloudException extends RuntimeException {
    private final int statusCode;
    private final String responseBody;
    private final OpenCloudApiError apiError;

    public OpenCloudException(String message, int statusCode, String responseBody, OpenCloudApiError apiError) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.apiError = apiError;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public OpenCloudApiError getApiError() {
        return apiError;
    }

    public String getErrorCode() {
        return apiError != null ? apiError.getCode() : null;
    }

    public String getErrorMessage() {
        return apiError != null ? apiError.getMessage() : getMessage();
    }

    public String getTarget() {
        return apiError != null ? apiError.getTarget() : null;
    }
}
