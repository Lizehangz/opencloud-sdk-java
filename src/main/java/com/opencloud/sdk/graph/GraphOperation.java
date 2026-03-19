package com.opencloud.sdk.graph;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public enum GraphOperation {
    UPDATEOWNUSER("UpdateOwnUser", "PATCH", "/v1.0/me"),
    GETOWNUSER("GetOwnUser", "GET", "/v1.0/me"),
    CHANGEOWNPASSWORD("ChangeOwnPassword", "POST", "/v1.0/me/changePassword"),
    GETOWNUSERPHOTO("GetOwnUserPhoto", "GET", "/v1.0/me/photo/$value"),
    UPDATEOWNUSERPHOTOPUT("UpdateOwnUserPhotoPut", "PUT", "/v1.0/me/photo/$value"),
    UPDATEOWNUSERPHOTOPATCH("UpdateOwnUserPhotoPatch", "PATCH", "/v1.0/me/photo/$value"),
    DELETEOWNUSERPHOTO("DeleteOwnUserPhoto", "DELETE", "/v1.0/me/photo/$value"),
    GETACTIVITIES("GetActivities", "GET", "/v1beta1/extensions/org.libregraph/activities"),
    LISTMYDRIVESBETA("ListMyDrivesBeta", "GET", "/v1beta1/me/drives"),
    LISTMYDRIVES("ListMyDrives", "GET", "/v1.0/me/drives"),
    LISTALLDRIVESBETA("ListAllDrivesBeta", "GET", "/v1beta1/drives"),
    LISTALLDRIVES("ListAllDrives", "GET", "/v1.0/drives"),
    CREATEDRIVE("CreateDrive", "POST", "/v1.0/drives"),
    GETDRIVE("GetDrive", "GET", "/v1.0/drives/{drive-id}"),
    UPDATEDRIVE("UpdateDrive", "PATCH", "/v1.0/drives/{drive-id}"),
    DELETEDRIVE("DeleteDrive", "DELETE", "/v1.0/drives/{drive-id}"),
    CREATEDRIVEITEM("CreateDriveItem", "POST", "/v1beta1/drives/{drive-id}/root/children"),
    CREATELINKSPACEROOT("CreateLinkSpaceRoot", "POST", "/v1beta1/drives/{drive-id}/root/createLink"),
    INVITESPACEROOT("InviteSpaceRoot", "POST", "/v1beta1/drives/{drive-id}/root/invite"),
    LISTPERMISSIONSSPACEROOT("ListPermissionsSpaceRoot", "GET", "/v1beta1/drives/{drive-id}/root/permissions"),
    GETPERMISSIONSPACEROOT("GetPermissionSpaceRoot", "GET", "/v1beta1/drives/{drive-id}/root/permissions/{perm-id}"),
    UPDATEPERMISSIONSPACEROOT("UpdatePermissionSpaceRoot", "PATCH", "/v1beta1/drives/{drive-id}/root/permissions/{perm-id}"),
    DELETEPERMISSIONSPACEROOT("DeletePermissionSpaceRoot", "DELETE", "/v1beta1/drives/{drive-id}/root/permissions/{perm-id}"),
    SETPERMISSIONPASSWORDSPACEROOT("SetPermissionPasswordSpaceRoot", "POST", "/v1beta1/drives/{drive-id}/root/permissions/{perm-id}/setPassword"),
    GETDRIVEITEM("GetDriveItem", "GET", "/v1beta1/drives/{drive-id}/items/{item-id}"),
    UPDATEDRIVEITEM("UpdateDriveItem", "PATCH", "/v1beta1/drives/{drive-id}/items/{item-id}"),
    DELETEDRIVEITEM("DeleteDriveItem", "DELETE", "/v1beta1/drives/{drive-id}/items/{item-id}"),
    CREATELINK("CreateLink", "POST", "/v1beta1/drives/{drive-id}/items/{item-id}/createLink"),
    INVITE("Invite", "POST", "/v1beta1/drives/{drive-id}/items/{item-id}/invite"),
    LISTPERMISSIONS("ListPermissions", "GET", "/v1beta1/drives/{drive-id}/items/{item-id}/permissions"),
    GETPERMISSION("GetPermission", "GET", "/v1beta1/drives/{drive-id}/items/{item-id}/permissions/{perm-id}"),
    UPDATEPERMISSION("UpdatePermission", "PATCH", "/v1beta1/drives/{drive-id}/items/{item-id}/permissions/{perm-id}"),
    DELETEPERMISSION("DeletePermission", "DELETE", "/v1beta1/drives/{drive-id}/items/{item-id}/permissions/{perm-id}"),
    SETPERMISSIONPASSWORD("SetPermissionPassword", "POST", "/v1beta1/drives/{drive-id}/items/{item-id}/permissions/{perm-id}/setPassword"),
    GETROOT("GetRoot", "GET", "/v1.0/drives/{drive-id}/root"),
    LISTGROUPS("ListGroups", "GET", "/v1.0/groups"),
    CREATEGROUP("CreateGroup", "POST", "/v1.0/groups"),
    GETGROUP("GetGroup", "GET", "/v1.0/groups/{group-id}"),
    UPDATEGROUP("UpdateGroup", "PATCH", "/v1.0/groups/{group-id}"),
    DELETEGROUP("DeleteGroup", "DELETE", "/v1.0/groups/{group-id}"),
    LISTMEMBERS("ListMembers", "GET", "/v1.0/groups/{group-id}/members"),
    ADDMEMBER("AddMember", "POST", "/v1.0/groups/{group-id}/members/$ref"),
    DELETEMEMBER("DeleteMember", "DELETE", "/v1.0/groups/{group-id}/members/{directory-object-id}/$ref"),
    GETHOME("GetHome", "GET", "/v1.0/me/drive"),
    HOMEGETROOT("HomeGetRoot", "GET", "/v1.0/me/drive/root"),
    HOMEGETCHILDREN("HomeGetChildren", "GET", "/v1.0/me/drive/root/children"),
    LISTSHAREDBYME("ListSharedByMe", "GET", "/v1beta1/me/drive/sharedByMe"),
    FOLLOWDRIVEITEM("FollowDriveItem", "POST", "/v1.0/me/drive/items/{item-id}/follow"),
    UNFOLLOWDRIVEITEM("UnfollowDriveItem", "DELETE", "/v1.0/me/drive/following/{item-id}"),
    LISTSHAREDWITHME("ListSharedWithMe", "GET", "/v1beta1/me/drive/sharedWithMe"),
    CREATEINVITATION("CreateInvitation", "POST", "/v1.0/invitations"),
    LISTINVITATIONS("ListInvitations", "GET", "/v1.0/invitations"),
    GETINVITATION("GetInvitation", "GET", "/v1.0/invitations/{invitation-id}"),
    LISTUSERS("ListUsers", "GET", "/v1.0/users"),
    CREATEUSER("CreateUser", "POST", "/v1.0/users"),
    GETUSER("GetUser", "GET", "/v1.0/users/{user-id}"),
    UPDATEUSER("UpdateUser", "PATCH", "/v1.0/users/{user-id}"),
    DELETEUSER("DeleteUser", "DELETE", "/v1.0/users/{user-id}"),
    EXPORTPERSONALDATA("ExportPersonalData", "POST", "/v1.0/users/{user-id}/exportPersonalData"),
    USER_LISTAPPROLEASSIGNMENTS("user.ListAppRoleAssignments", "GET", "/v1.0/users/{user-id}/appRoleAssignments"),
    USER_CREATEAPPROLEASSIGNMENTS("user.CreateAppRoleAssignments", "POST", "/v1.0/users/{user-id}/appRoleAssignments"),
    USER_DELETEAPPROLEASSIGNMENTS("user.DeleteAppRoleAssignments", "DELETE", "/v1.0/users/{user-id}/appRoleAssignments/{appRoleAssignment-id}"),
    GETUSERPHOTO("GetUserPhoto", "GET", "/v1.0/users/{user-id}/photo/$value"),
    LISTEDUCATIONUSERS("ListEducationUsers", "GET", "/v1.0/education/users"),
    CREATEEDUCATIONUSER("CreateEducationUser", "POST", "/v1.0/education/users"),
    GETEDUCATIONUSER("GetEducationUser", "GET", "/v1.0/education/users/{user-id}"),
    UPDATEEDUCATIONUSER("UpdateEducationUser", "PATCH", "/v1.0/education/users/{user-id}"),
    DELETEEDUCATIONUSER("DeleteEducationUser", "DELETE", "/v1.0/education/users/{user-id}"),
    LISTSCHOOLS("ListSchools", "GET", "/v1.0/education/schools"),
    CREATESCHOOL("CreateSchool", "POST", "/v1.0/education/schools"),
    GETSCHOOL("GetSchool", "GET", "/v1.0/education/schools/{school-id}"),
    UPDATESCHOOL("UpdateSchool", "PATCH", "/v1.0/education/schools/{school-id}"),
    DELETESCHOOL("DeleteSchool", "DELETE", "/v1.0/education/schools/{school-id}"),
    LISTSCHOOLUSERS("ListSchoolUsers", "GET", "/v1.0/education/schools/{school-id}/users"),
    ADDUSERTOSCHOOL("AddUserToSchool", "POST", "/v1.0/education/schools/{school-id}/users/$ref"),
    DELETEUSERFROMSCHOOL("DeleteUserFromSchool", "DELETE", "/v1.0/education/schools/{school-id}/users/{user-id}/$ref"),
    LISTSCHOOLCLASSES("ListSchoolClasses", "GET", "/v1.0/education/schools/{school-id}/classes"),
    ADDCLASSTOSCHOOL("AddClassToSchool", "POST", "/v1.0/education/schools/{school-id}/classes/$ref"),
    DELETECLASSFROMSCHOOL("DeleteClassFromSchool", "DELETE", "/v1.0/education/schools/{school-id}/classes/{class-id}/$ref"),
    LISTCLASSES("ListClasses", "GET", "/v1.0/education/classes"),
    CREATECLASS("CreateClass", "POST", "/v1.0/education/classes"),
    GETCLASS("GetClass", "GET", "/v1.0/education/classes/{class-id}"),
    UPDATECLASS("UpdateClass", "PATCH", "/v1.0/education/classes/{class-id}"),
    DELETECLASS("DeleteClass", "DELETE", "/v1.0/education/classes/{class-id}"),
    LISTCLASSMEMBERS("ListClassMembers", "GET", "/v1.0/education/classes/{class-id}/members"),
    ADDUSERTOCLASS("AddUserToClass", "POST", "/v1.0/education/classes/{class-id}/members/$ref"),
    DELETEUSERFROMCLASS("DeleteUserFromClass", "DELETE", "/v1.0/education/classes/{class-id}/members/{user-id}/$ref"),
    GETTEACHERS("GetTeachers", "GET", "/v1.0/education/classes/{class-id}/teachers"),
    ADDTEACHERTOCLASS("AddTeacherToClass", "POST", "/v1.0/education/classes/{class-id}/teachers/$ref"),
    DELETETEACHERFROMCLASS("DeleteTeacherFromClass", "DELETE", "/v1.0/education/classes/{class-id}/teachers/{user-id}/$ref"),
    GETTAGS("GetTags", "GET", "/v1.0/extensions/org.libregraph/tags"),
    ASSIGNTAGS("AssignTags", "PUT", "/v1.0/extensions/org.libregraph/tags"),
    UNASSIGNTAGS("UnassignTags", "DELETE", "/v1.0/extensions/org.libregraph/tags"),
    LISTAPPLICATIONS("ListApplications", "GET", "/v1.0/applications"),
    GETAPPLICATION("GetApplication", "GET", "/v1.0/applications/{application-id}"),
    LISTPERMISSIONROLEDEFINITIONS("ListPermissionRoleDefinitions", "GET", "/v1beta1/roleManagement/permissions/roleDefinitions"),
    GETPERMISSIONROLEDEFINITION("GetPermissionRoleDefinition", "GET", "/v1beta1/roleManagement/permissions/roleDefinitions/{role-id}");

    private static final Map<String, GraphOperation> BY_OPERATION_ID;

    static {
        Map<String, GraphOperation> values = new LinkedHashMap<String, GraphOperation>();
        for (GraphOperation operation : values()) {
            values.put(operation.operationId, operation);
        }
        BY_OPERATION_ID = Collections.unmodifiableMap(values);
    }

    private final String operationId;
    private final String method;
    private final String path;

    GraphOperation(String operationId, String method, String path) {
        this.operationId = operationId;
        this.method = method;
        this.path = path;
    }

    public String getOperationId() {
        return operationId;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public static GraphOperation fromOperationId(String operationId) {
        GraphOperation operation = BY_OPERATION_ID.get(operationId);
        if (operation == null) {
            throw new IllegalArgumentException("Unknown Graph operationId: " + operationId);
        }
        return operation;
    }
}
