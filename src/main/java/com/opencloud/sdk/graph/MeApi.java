package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.Drive;
import com.opencloud.sdk.graph.model.DriveItem;
import com.opencloud.sdk.graph.model.PasswordChangeRequest;
import com.opencloud.sdk.graph.model.User;
import com.opencloud.sdk.graph.model.UserUpdateRequest;

import java.io.IOException;

public final class MeApi extends GraphResourceApi {
    MeApi(GraphApi graphApi) {
        super(graphApi);
    }

    public ApiResponse<JsonNode> getProfile() throws IOException {
        return json(GraphOperation.GETOWNUSER, ApiRequest.builder().build());
    }

    public ApiResponse<User> getProfileModel() throws IOException {
        return model(GraphOperation.GETOWNUSER, ApiRequest.builder().build(), User.class);
    }

    public ApiResponse<JsonNode> updateProfile(Object payload) throws IOException {
        return json(GraphOperation.UPDATEOWNUSER, ApiRequest.builder().body(payload).build());
    }

    public ApiResponse<User> updateProfile(UserUpdateRequest payload) throws IOException {
        return model(GraphOperation.UPDATEOWNUSER, ApiRequest.builder().body(payload).build(), User.class);
    }

    public ApiResponse<Void> changePassword(String currentPassword, String newPassword) throws IOException {
        PasswordChangeRequest request = new PasswordChangeRequest();
        request.setCurrentPassword(currentPassword);
        request.setNewPassword(newPassword);
        return changePassword(request);
    }

    public ApiResponse<Void> changePassword(PasswordChangeRequest request) throws IOException {
        return empty(GraphOperation.CHANGEOWNPASSWORD, ApiRequest.builder().body(request).build());
    }

    public ApiResponse<byte[]> getPhoto() throws IOException {
        return bytes(GraphOperation.GETOWNUSERPHOTO, ApiRequest.builder().accept("*/*").build());
    }

    public ApiResponse<Void> updatePhoto(byte[] photoBytes) throws IOException {
        return empty(
            GraphOperation.UPDATEOWNUSERPHOTOPUT,
            ApiRequest.builder()
                .binaryBody(photoBytes)
                .contentType("image/jpeg")
                .accept("*/*")
                .build()
        );
    }

    public ApiResponse<Void> patchPhoto(byte[] photoBytes) throws IOException {
        return empty(
            GraphOperation.UPDATEOWNUSERPHOTOPATCH,
            ApiRequest.builder()
                .binaryBody(photoBytes)
                .contentType("image/jpeg")
                .accept("*/*")
                .build()
        );
    }

    public ApiResponse<Void> deletePhoto() throws IOException {
        return empty(GraphOperation.DELETEOWNUSERPHOTO, ApiRequest.builder().accept("*/*").build());
    }

    public ApiResponse<JsonNode> listMyDrives() throws IOException {
        return json(GraphOperation.LISTMYDRIVES, ApiRequest.builder().build());
    }

    public ApiResponse<CollectionResponse<Drive>> listMyDrivesModel() throws IOException {
        return model(
            GraphOperation.LISTMYDRIVES,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<Drive>>() { }
        );
    }

    public ApiResponse<CollectionResponse<Drive>> listMyDrivesBetaModel() throws IOException {
        return model(
            GraphOperation.LISTMYDRIVESBETA,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<Drive>>() { }
        );
    }

    public ApiResponse<JsonNode> getHomeDrive() throws IOException {
        return json(GraphOperation.GETHOME, ApiRequest.builder().build());
    }

    public ApiResponse<Drive> getHomeDriveModel() throws IOException {
        return model(GraphOperation.GETHOME, ApiRequest.builder().build(), Drive.class);
    }

    public ApiResponse<JsonNode> getHomeRoot() throws IOException {
        return json(GraphOperation.HOMEGETROOT, ApiRequest.builder().build());
    }

    public ApiResponse<DriveItem> getHomeRootModel() throws IOException {
        return model(GraphOperation.HOMEGETROOT, ApiRequest.builder().build(), DriveItem.class);
    }

    public ApiResponse<JsonNode> listHomeChildren() throws IOException {
        return json(GraphOperation.HOMEGETCHILDREN, ApiRequest.builder().build());
    }

    public ApiResponse<CollectionResponse<DriveItem>> listHomeChildrenModel() throws IOException {
        return model(
            GraphOperation.HOMEGETCHILDREN,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<DriveItem>>() { }
        );
    }

    public ApiResponse<JsonNode> listSharedWithMe() throws IOException {
        return json(GraphOperation.LISTSHAREDWITHME, ApiRequest.builder().build());
    }

    public ApiResponse<CollectionResponse<DriveItem>> listSharedWithMeModel() throws IOException {
        return model(
            GraphOperation.LISTSHAREDWITHME,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<DriveItem>>() { }
        );
    }

    public ApiResponse<JsonNode> listSharedByMe() throws IOException {
        return json(GraphOperation.LISTSHAREDBYME, ApiRequest.builder().build());
    }

    public ApiResponse<CollectionResponse<DriveItem>> listSharedByMeModel() throws IOException {
        return model(
            GraphOperation.LISTSHAREDBYME,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<DriveItem>>() { }
        );
    }

    public ApiResponse<Void> followDriveItem(String itemId) throws IOException {
        return empty(
            GraphOperation.FOLLOWDRIVEITEM,
            ApiRequest.builder().pathParam("item-id", itemId).build()
        );
    }

    public ApiResponse<Void> unfollowDriveItem(String itemId) throws IOException {
        return empty(
            GraphOperation.UNFOLLOWDRIVEITEM,
            ApiRequest.builder().pathParam("item-id", itemId).build()
        );
    }
}
