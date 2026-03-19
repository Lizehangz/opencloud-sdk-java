package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.transport.RequestExecutor;

import java.io.IOException;

public final class GraphApi {
    private final RequestExecutor requestExecutor;
    private final MeApi meApi;
    private final UsersApi usersApi;
    private final DrivesApi drivesApi;
    private final GroupsApi groupsApi;
    private final InvitationsApi invitationsApi;
    private final ApplicationsApi applicationsApi;
    private final EducationApi educationApi;
    private final TagsApi tagsApi;
    private final ActivitiesApi activitiesApi;

    public GraphApi(RequestExecutor requestExecutor) {
        this.requestExecutor = requestExecutor;
        this.meApi = new MeApi(this);
        this.usersApi = new UsersApi(this);
        this.drivesApi = new DrivesApi(this);
        this.groupsApi = new GroupsApi(this);
        this.invitationsApi = new InvitationsApi(this);
        this.applicationsApi = new ApplicationsApi(this);
        this.educationApi = new EducationApi(this);
        this.tagsApi = new TagsApi(this);
        this.activitiesApi = new ActivitiesApi(this);
    }

    public ApiResponse<JsonNode> execute(GraphOperation operation, ApiRequest request) throws IOException {
        return requestExecutor.executeGraph(operation.getMethod(), operation.getPath(), request, JsonNode.class);
    }

    public <T> ApiResponse<T> execute(GraphOperation operation, ApiRequest request, Class<T> responseType) throws IOException {
        return requestExecutor.executeGraph(operation.getMethod(), operation.getPath(), request, responseType);
    }

    public <T> ApiResponse<T> execute(GraphOperation operation, ApiRequest request, TypeReference<T> responseType) throws IOException {
        return requestExecutor.executeGraph(operation.getMethod(), operation.getPath(), request, responseType);
    }

    public ApiResponse<byte[]> executeBytes(GraphOperation operation, ApiRequest request) throws IOException {
        return requestExecutor.executeGraph(operation.getMethod(), operation.getPath(), request, byte[].class);
    }

    public ApiResponse<Void> executeWithoutBody(GraphOperation operation, ApiRequest request) throws IOException {
        return requestExecutor.executeGraph(operation.getMethod(), operation.getPath(), request, Void.class);
    }

    public MeApi me() {
        return meApi;
    }

    public UsersApi users() {
        return usersApi;
    }

    public DrivesApi drives() {
        return drivesApi;
    }

    public GroupsApi groups() {
        return groupsApi;
    }

    public InvitationsApi invitations() {
        return invitationsApi;
    }

    public ApplicationsApi applications() {
        return applicationsApi;
    }

    public EducationApi education() {
        return educationApi;
    }

    public TagsApi tags() {
        return tagsApi;
    }

    public ActivitiesApi activities() {
        return activitiesApi;
    }

    String getBaseGraphUrl() {
        return requestExecutor.getBaseGraphUrl();
    }
}
