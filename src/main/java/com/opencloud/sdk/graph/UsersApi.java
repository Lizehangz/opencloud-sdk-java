package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.graph.model.AppRoleAssignment;
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.User;
import com.opencloud.sdk.graph.model.UserCreateRequest;
import com.opencloud.sdk.graph.model.UserUpdateRequest;

import java.io.IOException;

public final class UsersApi extends GraphResourceApi {
    UsersApi(GraphApi graphApi) {
        super(graphApi);
    }

    public ApiResponse<JsonNode> list() throws IOException {
        return json(GraphOperation.LISTUSERS, ApiRequest.builder().build());
    }

    public ApiResponse<CollectionResponse<User>> listModel() throws IOException {
        return model(
            GraphOperation.LISTUSERS,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<User>>() { }
        );
    }

    public ApiResponse<JsonNode> list(String filter) throws IOException {
        ApiRequest.Builder builder = ApiRequest.builder();
        if (filter != null && !filter.trim().isEmpty()) {
            builder.queryParam("$filter", filter);
        }
        return json(GraphOperation.LISTUSERS, builder.build());
    }

    public ApiResponse<CollectionResponse<User>> listModel(String filter) throws IOException {
        ApiRequest.Builder builder = ApiRequest.builder();
        if (filter != null && !filter.trim().isEmpty()) {
            builder.queryParam("$filter", filter);
        }
        return model(
            GraphOperation.LISTUSERS,
            builder.build(),
            new TypeReference<CollectionResponse<User>>() { }
        );
    }

    public ApiResponse<JsonNode> get(String userId) throws IOException {
        return json(
            GraphOperation.GETUSER,
            ApiRequest.builder().pathParam("user-id", userId).build()
        );
    }

    public ApiResponse<User> getModel(String userId) throws IOException {
        return model(
            GraphOperation.GETUSER,
            ApiRequest.builder().pathParam("user-id", userId).build(),
            User.class
        );
    }

    public ApiResponse<JsonNode> create(Object payload) throws IOException {
        return json(GraphOperation.CREATEUSER, ApiRequest.builder().body(payload).build());
    }

    public ApiResponse<User> create(UserCreateRequest payload) throws IOException {
        return model(GraphOperation.CREATEUSER, ApiRequest.builder().body(payload).build(), User.class);
    }

    public ApiResponse<JsonNode> update(String userId, Object payload) throws IOException {
        return json(
            GraphOperation.UPDATEUSER,
            ApiRequest.builder()
                .pathParam("user-id", userId)
                .body(payload)
                .build()
        );
    }

    public ApiResponse<User> update(String userId, UserUpdateRequest payload) throws IOException {
        return model(
            GraphOperation.UPDATEUSER,
            ApiRequest.builder()
                .pathParam("user-id", userId)
                .body(payload)
                .build(),
            User.class
        );
    }

    public ApiResponse<Void> delete(String userId) throws IOException {
        return empty(
            GraphOperation.DELETEUSER,
            ApiRequest.builder().pathParam("user-id", userId).build()
        );
    }

    public ApiResponse<JsonNode> listAppRoleAssignments(String userId) throws IOException {
        return json(
            GraphOperation.USER_LISTAPPROLEASSIGNMENTS,
            ApiRequest.builder().pathParam("user-id", userId).build()
        );
    }

    public ApiResponse<CollectionResponse<AppRoleAssignment>> listAppRoleAssignmentsModel(String userId) throws IOException {
        return model(
            GraphOperation.USER_LISTAPPROLEASSIGNMENTS,
            ApiRequest.builder().pathParam("user-id", userId).build(),
            new TypeReference<CollectionResponse<AppRoleAssignment>>() { }
        );
    }

    public ApiResponse<JsonNode> createAppRoleAssignment(String userId, Object payload) throws IOException {
        return json(
            GraphOperation.USER_CREATEAPPROLEASSIGNMENTS,
            ApiRequest.builder()
                .pathParam("user-id", userId)
                .body(payload)
                .build()
        );
    }

    public ApiResponse<AppRoleAssignment> createAppRoleAssignment(String userId, AppRoleAssignment payload) throws IOException {
        return model(
            GraphOperation.USER_CREATEAPPROLEASSIGNMENTS,
            ApiRequest.builder()
                .pathParam("user-id", userId)
                .body(payload)
                .build(),
            AppRoleAssignment.class
        );
    }

    public ApiResponse<Void> deleteAppRoleAssignment(String userId, String appRoleAssignmentId) throws IOException {
        return empty(
            GraphOperation.USER_DELETEAPPROLEASSIGNMENTS,
            ApiRequest.builder()
                .pathParam("user-id", userId)
                .pathParam("appRoleAssignment-id", appRoleAssignmentId)
                .build()
        );
    }

    public ApiResponse<Void> exportPersonalData(String userId) throws IOException {
        return empty(
            GraphOperation.EXPORTPERSONALDATA,
            ApiRequest.builder()
                .pathParam("user-id", userId)
                .build()
        );
    }

    public ApiResponse<byte[]> getPhoto(String userId) throws IOException {
        return bytes(
            GraphOperation.GETUSERPHOTO,
            ApiRequest.builder()
                .pathParam("user-id", userId)
                .accept("*/*")
                .build()
        );
    }
}
