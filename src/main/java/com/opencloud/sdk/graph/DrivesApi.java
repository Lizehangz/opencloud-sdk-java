package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.Drive;
import com.opencloud.sdk.graph.model.DriveCreateRequest;
import com.opencloud.sdk.graph.model.DriveItem;
import com.opencloud.sdk.graph.model.DriveItemCreateRequest;
import com.opencloud.sdk.graph.model.DriveItemUpdateRequest;
import com.opencloud.sdk.graph.model.DriveInviteRequest;
import com.opencloud.sdk.graph.model.DriveLinkRequest;
import com.opencloud.sdk.graph.model.DriveUpdateRequest;
import com.opencloud.sdk.graph.model.Permission;
import com.opencloud.sdk.graph.model.PermissionPasswordRequest;
import com.opencloud.sdk.graph.model.PermissionUpdateRequest;

import java.io.IOException;

public final class DrivesApi extends GraphResourceApi {
    DrivesApi(GraphApi graphApi) {
        super(graphApi);
    }

    public ApiResponse<JsonNode> list() throws IOException {
        return json(GraphOperation.LISTALLDRIVES, ApiRequest.builder().build());
    }

    public ApiResponse<CollectionResponse<Drive>> listModel() throws IOException {
        return model(
            GraphOperation.LISTALLDRIVES,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<Drive>>() { }
        );
    }

    public ApiResponse<CollectionResponse<Drive>> listBetaModel() throws IOException {
        return model(
            GraphOperation.LISTALLDRIVESBETA,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<Drive>>() { }
        );
    }

    public ApiResponse<JsonNode> list(String filter) throws IOException {
        ApiRequest.Builder builder = ApiRequest.builder();
        if (filter != null && !filter.trim().isEmpty()) {
            builder.queryParam("$filter", filter);
        }
        return json(GraphOperation.LISTALLDRIVES, builder.build());
    }

    public ApiResponse<CollectionResponse<Drive>> listModel(String filter) throws IOException {
        ApiRequest.Builder builder = ApiRequest.builder();
        if (filter != null && !filter.trim().isEmpty()) {
            builder.queryParam("$filter", filter);
        }
        return model(
            GraphOperation.LISTALLDRIVES,
            builder.build(),
            new TypeReference<CollectionResponse<Drive>>() { }
        );
    }

    public ApiResponse<JsonNode> get(String driveId) throws IOException {
        return json(
            GraphOperation.GETDRIVE,
            ApiRequest.builder().pathParam("drive-id", driveId).build()
        );
    }

    public ApiResponse<Drive> getModel(String driveId) throws IOException {
        return model(
            GraphOperation.GETDRIVE,
            ApiRequest.builder().pathParam("drive-id", driveId).build(),
            Drive.class
        );
    }

    public ApiResponse<JsonNode> create(Object payload) throws IOException {
        return json(GraphOperation.CREATEDRIVE, ApiRequest.builder().body(payload).build());
    }

    public ApiResponse<Drive> create(DriveCreateRequest payload) throws IOException {
        return model(GraphOperation.CREATEDRIVE, ApiRequest.builder().body(payload).build(), Drive.class);
    }

    public ApiResponse<JsonNode> update(String driveId, Object payload) throws IOException {
        return json(
            GraphOperation.UPDATEDRIVE,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .body(payload)
                .build()
        );
    }

    public ApiResponse<Drive> update(String driveId, DriveUpdateRequest payload) throws IOException {
        return model(
            GraphOperation.UPDATEDRIVE,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .body(payload)
                .build(),
            Drive.class
        );
    }

    public ApiResponse<Void> delete(String driveId) throws IOException {
        return empty(
            GraphOperation.DELETEDRIVE,
            ApiRequest.builder().pathParam("drive-id", driveId).build()
        );
    }

    public ApiResponse<JsonNode> getRoot(String driveId) throws IOException {
        return json(
            GraphOperation.GETROOT,
            ApiRequest.builder().pathParam("drive-id", driveId).build()
        );
    }

    public ApiResponse<DriveItem> getRootModel(String driveId) throws IOException {
        return model(
            GraphOperation.GETROOT,
            ApiRequest.builder().pathParam("drive-id", driveId).build(),
            DriveItem.class
        );
    }

    public ApiResponse<JsonNode> getItem(String driveId, String itemId) throws IOException {
        return json(
            GraphOperation.GETDRIVEITEM,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .build()
        );
    }

    public ApiResponse<DriveItem> getItemModel(String driveId, String itemId) throws IOException {
        return model(
            GraphOperation.GETDRIVEITEM,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .build(),
            DriveItem.class
        );
    }

    public ApiResponse<JsonNode> createItem(String driveId, Object payload) throws IOException {
        return json(
            GraphOperation.CREATEDRIVEITEM,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .body(payload)
                .build()
        );
    }

    public ApiResponse<DriveItem> createItem(String driveId, DriveItemCreateRequest payload) throws IOException {
        return model(
            GraphOperation.CREATEDRIVEITEM,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .body(payload)
                .build(),
            DriveItem.class
        );
    }

    public ApiResponse<DriveItem> updateItem(String driveId, String itemId, DriveItemUpdateRequest payload) throws IOException {
        return model(
            GraphOperation.UPDATEDRIVEITEM,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .body(payload)
                .build(),
            DriveItem.class
        );
    }

    public ApiResponse<Void> deleteItem(String driveId, String itemId) throws IOException {
        return empty(
            GraphOperation.DELETEDRIVEITEM,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .build()
        );
    }

    public ApiResponse<JsonNode> createLink(String driveId, String itemId, String type) throws IOException {
        DriveLinkRequest payload = new DriveLinkRequest();
        payload.setType(type);
        return json(
            GraphOperation.CREATELINK,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .body(payload)
                .build()
        );
    }

    public ApiResponse<Permission> createLinkModel(String driveId, String itemId, DriveLinkRequest payload) throws IOException {
        return model(
            GraphOperation.CREATELINK,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .body(payload)
                .build(),
            Permission.class
        );
    }

    public ApiResponse<Permission> createRootLinkModel(String driveId, DriveLinkRequest payload) throws IOException {
        return model(
            GraphOperation.CREATELINKSPACEROOT,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .body(payload)
                .build(),
            Permission.class
        );
    }

    public ApiResponse<JsonNode> invite(String driveId, String itemId, DriveInviteRequest payload) throws IOException {
        return json(
            GraphOperation.INVITE,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .body(payload)
                .build()
        );
    }

    public ApiResponse<JsonNode> inviteRoot(String driveId, DriveInviteRequest payload) throws IOException {
        return json(
            GraphOperation.INVITESPACEROOT,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .body(payload)
                .build()
        );
    }

    public ApiResponse<JsonNode> listPermissions(String driveId, String itemId) throws IOException {
        return json(
            GraphOperation.LISTPERMISSIONS,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .build()
        );
    }

    public ApiResponse<CollectionResponse<Permission>> listPermissionsModel(String driveId, String itemId) throws IOException {
        return model(
            GraphOperation.LISTPERMISSIONS,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .build(),
            new TypeReference<CollectionResponse<Permission>>() { }
        );
    }

    public ApiResponse<Permission> getPermissionModel(String driveId, String itemId, String permissionId) throws IOException {
        return model(
            GraphOperation.GETPERMISSION,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .pathParam("perm-id", permissionId)
                .build(),
            Permission.class
        );
    }

    public ApiResponse<Permission> updatePermission(String driveId, String itemId, String permissionId, PermissionUpdateRequest payload) throws IOException {
        return model(
            GraphOperation.UPDATEPERMISSION,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .pathParam("perm-id", permissionId)
                .body(payload)
                .build(),
            Permission.class
        );
    }

    public ApiResponse<Void> deletePermission(String driveId, String itemId, String permissionId) throws IOException {
        return empty(
            GraphOperation.DELETEPERMISSION,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .pathParam("perm-id", permissionId)
                .build()
        );
    }

    public ApiResponse<Void> setPermissionPassword(String driveId, String itemId, String permissionId, PermissionPasswordRequest payload) throws IOException {
        return empty(
            GraphOperation.SETPERMISSIONPASSWORD,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("item-id", itemId)
                .pathParam("perm-id", permissionId)
                .body(payload)
                .build()
        );
    }

    public ApiResponse<CollectionResponse<Permission>> listRootPermissionsModel(String driveId) throws IOException {
        return model(
            GraphOperation.LISTPERMISSIONSSPACEROOT,
            ApiRequest.builder().pathParam("drive-id", driveId).build(),
            new TypeReference<CollectionResponse<Permission>>() { }
        );
    }

    public ApiResponse<Permission> getRootPermissionModel(String driveId, String permissionId) throws IOException {
        return model(
            GraphOperation.GETPERMISSIONSPACEROOT,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("perm-id", permissionId)
                .build(),
            Permission.class
        );
    }

    public ApiResponse<Permission> updateRootPermission(String driveId, String permissionId, PermissionUpdateRequest payload) throws IOException {
        return model(
            GraphOperation.UPDATEPERMISSIONSPACEROOT,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("perm-id", permissionId)
                .body(payload)
                .build(),
            Permission.class
        );
    }

    public ApiResponse<Void> deleteRootPermission(String driveId, String permissionId) throws IOException {
        return empty(
            GraphOperation.DELETEPERMISSIONSPACEROOT,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("perm-id", permissionId)
                .build()
        );
    }

    public ApiResponse<Void> setRootPermissionPassword(String driveId, String permissionId, PermissionPasswordRequest payload) throws IOException {
        return empty(
            GraphOperation.SETPERMISSIONPASSWORDSPACEROOT,
            ApiRequest.builder()
                .pathParam("drive-id", driveId)
                .pathParam("perm-id", permissionId)
                .body(payload)
                .build()
        );
    }
}
