package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.graph.model.Application;
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.RoleDefinition;

import java.io.IOException;
import java.util.List;

public final class ApplicationsApi extends GraphResourceApi {
    ApplicationsApi(GraphApi graphApi) {
        super(graphApi);
    }

    public ApiResponse<JsonNode> listApplications() throws IOException {
        return json(GraphOperation.LISTAPPLICATIONS, ApiRequest.builder().build());
    }

    public ApiResponse<CollectionResponse<Application>> listApplicationsModel() throws IOException {
        return model(
            GraphOperation.LISTAPPLICATIONS,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<Application>>() { }
        );
    }

    public ApiResponse<JsonNode> getApplication(String applicationId) throws IOException {
        return json(
            GraphOperation.GETAPPLICATION,
            ApiRequest.builder().pathParam("application-id", applicationId).build()
        );
    }

    public ApiResponse<Application> getApplicationModel(String applicationId) throws IOException {
        return model(
            GraphOperation.GETAPPLICATION,
            ApiRequest.builder().pathParam("application-id", applicationId).build(),
            Application.class
        );
    }

    public ApiResponse<JsonNode> listRoleDefinitions() throws IOException {
        return json(GraphOperation.LISTPERMISSIONROLEDEFINITIONS, ApiRequest.builder().build());
    }

    public ApiResponse<List<RoleDefinition>> listRoleDefinitionsModel() throws IOException {
        return model(
            GraphOperation.LISTPERMISSIONROLEDEFINITIONS,
            ApiRequest.builder().build(),
            new TypeReference<List<RoleDefinition>>() { }
        );
    }

    public ApiResponse<JsonNode> getRoleDefinition(String roleId) throws IOException {
        return json(
            GraphOperation.GETPERMISSIONROLEDEFINITION,
            ApiRequest.builder().pathParam("role-id", roleId).build()
        );
    }

    public ApiResponse<RoleDefinition> getRoleDefinitionModel(String roleId) throws IOException {
        return model(
            GraphOperation.GETPERMISSIONROLEDEFINITION,
            ApiRequest.builder().pathParam("role-id", roleId).build(),
            RoleDefinition.class
        );
    }
}
