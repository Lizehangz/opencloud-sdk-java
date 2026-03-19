package com.opencloud.sdk.graph;

import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.graph.model.TagAssignmentRequest;

import java.io.IOException;

public final class TagsApi extends GraphResourceApi {
    TagsApi(GraphApi graphApi) {
        super(graphApi);
    }

    public ApiResponse<JsonNode> get() throws IOException {
        return json(GraphOperation.GETTAGS, ApiRequest.builder().build());
    }

    public ApiResponse<JsonNode> assign(TagAssignmentRequest request) throws IOException {
        return json(GraphOperation.ASSIGNTAGS, ApiRequest.builder().body(request).build());
    }

    public ApiResponse<Void> unassign(TagAssignmentRequest request) throws IOException {
        return empty(GraphOperation.UNASSIGNTAGS, ApiRequest.builder().body(request).build());
    }
}
