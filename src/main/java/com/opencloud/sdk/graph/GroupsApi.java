package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.Group;
import com.opencloud.sdk.graph.model.GroupCreateRequest;
import com.opencloud.sdk.graph.model.GroupUpdateRequest;
import com.opencloud.sdk.graph.model.Identity;

import java.io.IOException;

public final class GroupsApi extends GraphResourceApi {
    GroupsApi(GraphApi graphApi) {
        super(graphApi);
    }

    public ApiResponse<JsonNode> list() throws IOException {
        return json(GraphOperation.LISTGROUPS, ApiRequest.builder().build());
    }

    public ApiResponse<CollectionResponse<Group>> listModel() throws IOException {
        return model(
            GraphOperation.LISTGROUPS,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<Group>>() { }
        );
    }

    public ApiResponse<JsonNode> get(String groupId) throws IOException {
        return json(
            GraphOperation.GETGROUP,
            ApiRequest.builder().pathParam("group-id", groupId).build()
        );
    }

    public ApiResponse<Group> getModel(String groupId) throws IOException {
        return model(
            GraphOperation.GETGROUP,
            ApiRequest.builder().pathParam("group-id", groupId).build(),
            Group.class
        );
    }

    public ApiResponse<JsonNode> create(Object payload) throws IOException {
        return json(GraphOperation.CREATEGROUP, ApiRequest.builder().body(payload).build());
    }

    public ApiResponse<Group> create(GroupCreateRequest payload) throws IOException {
        return model(GraphOperation.CREATEGROUP, ApiRequest.builder().body(payload).build(), Group.class);
    }

    public ApiResponse<JsonNode> update(String groupId, Object payload) throws IOException {
        return json(
            GraphOperation.UPDATEGROUP,
            ApiRequest.builder()
                .pathParam("group-id", groupId)
                .body(payload)
                .build()
        );
    }

    public ApiResponse<Group> update(String groupId, GroupUpdateRequest payload) throws IOException {
        return model(
            GraphOperation.UPDATEGROUP,
            ApiRequest.builder()
                .pathParam("group-id", groupId)
                .body(payload)
                .build(),
            Group.class
        );
    }

    public ApiResponse<Void> delete(String groupId) throws IOException {
        return empty(
            GraphOperation.DELETEGROUP,
            ApiRequest.builder().pathParam("group-id", groupId).build()
        );
    }

    public ApiResponse<JsonNode> listMembers(String groupId) throws IOException {
        return json(
            GraphOperation.LISTMEMBERS,
            ApiRequest.builder().pathParam("group-id", groupId).build()
        );
    }

    public ApiResponse<CollectionResponse<Identity>> listMembersModel(String groupId) throws IOException {
        return model(
            GraphOperation.LISTMEMBERS,
            ApiRequest.builder().pathParam("group-id", groupId).build(),
            new TypeReference<CollectionResponse<Identity>>() { }
        );
    }

    public ApiResponse<Void> addMember(String groupId, String directoryObjectId) throws IOException {
        return empty(
            GraphOperation.ADDMEMBER,
            ApiRequest.builder()
                .pathParam("group-id", groupId)
                .body(GraphPayload.object()
                    .put("@odata.id", directoryObjectReference(directoryObjectId))
                    .build())
                .build()
        );
    }

    public ApiResponse<Void> deleteMember(String groupId, String directoryObjectId) throws IOException {
        return empty(
            GraphOperation.DELETEMEMBER,
            ApiRequest.builder()
                .pathParam("group-id", groupId)
                .pathParam("directory-object-id", directoryObjectId)
                .build()
        );
    }

    private String directoryObjectReference(String directoryObjectId) {
        return graphApi.getBaseGraphUrl() + "/v1.0/directoryObjects/" + directoryObjectId;
    }
}
