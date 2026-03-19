package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.graph.model.Activity;
import com.opencloud.sdk.graph.model.CollectionResponse;

import java.io.IOException;

public final class ActivitiesApi extends GraphResourceApi {
    ActivitiesApi(GraphApi graphApi) {
        super(graphApi);
    }

    public ApiResponse<JsonNode> list(String kql) throws IOException {
        ApiRequest.Builder builder = ApiRequest.builder();
        if (kql != null && !kql.trim().isEmpty()) {
            builder.queryParam("kql", kql);
        }
        return json(GraphOperation.GETACTIVITIES, builder.build());
    }

    public ApiResponse<CollectionResponse<Activity>> listModel(String kql) throws IOException {
        ApiRequest.Builder builder = ApiRequest.builder();
        if (kql != null && !kql.trim().isEmpty()) {
            builder.queryParam("kql", kql);
        }
        return model(
            GraphOperation.GETACTIVITIES,
            builder.build(),
            new TypeReference<CollectionResponse<Activity>>() { }
        );
    }
}
