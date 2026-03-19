package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.Invitation;
import com.opencloud.sdk.graph.model.InvitationCreateRequest;

import java.io.IOException;

public final class InvitationsApi extends GraphResourceApi {
    InvitationsApi(GraphApi graphApi) {
        super(graphApi);
    }

    public ApiResponse<JsonNode> list() throws IOException {
        return json(GraphOperation.LISTINVITATIONS, ApiRequest.builder().build());
    }

    public ApiResponse<CollectionResponse<Invitation>> listModel() throws IOException {
        return model(
            GraphOperation.LISTINVITATIONS,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<Invitation>>() { }
        );
    }

    public ApiResponse<JsonNode> get(String invitationId) throws IOException {
        return json(
            GraphOperation.GETINVITATION,
            ApiRequest.builder().pathParam("invitation-id", invitationId).build()
        );
    }

    public ApiResponse<Invitation> getModel(String invitationId) throws IOException {
        return model(
            GraphOperation.GETINVITATION,
            ApiRequest.builder().pathParam("invitation-id", invitationId).build(),
            Invitation.class
        );
    }

    public ApiResponse<JsonNode> create(Object payload) throws IOException {
        return json(GraphOperation.CREATEINVITATION, ApiRequest.builder().body(payload).build());
    }

    public ApiResponse<Invitation> create(InvitationCreateRequest payload) throws IOException {
        return model(
            GraphOperation.CREATEINVITATION,
            ApiRequest.builder().body(payload).build(),
            Invitation.class
        );
    }
}
