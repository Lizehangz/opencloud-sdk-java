# OpenCloud Sdk Java

This project is a Java SDK scaffold for OpenCloud built on top of `OkHttp`.

It currently covers three OpenCloud HTTP API families:

- `LibreGraph`
- `OCS`
- `WebDAV`

## What Was Upgraded

- Added a resource-oriented Graph API on top of the generic executor.
- Kept the full LibreGraph operation catalog so every documented operation can still be called directly.
- Added strong typed POJOs for common Graph resources and collection responses.
- Added builder-style request models to make request construction easier in Java 8.
- Expanded the README with end-to-end usage examples.

## Requirements

- Java 8+
- Maven 3.8+

## Dependency

If you publish this module locally or to your artifact repository, consumer projects can depend on it as a normal Java library.

```xml
<dependency>
    <groupId>eu.opencloud</groupId>
    <artifactId>opencloud-sdk-java</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

## Packaging

This project is configured to build both a regular jar and a shaded fat jar with runtime dependencies included.

```bash
mvn clean package
```

The dependency-inclusive artifact is generated as:

```text
target/opencloud-sdk-java-0.1.0-SNAPSHOT-all.jar
```

Use the `-all.jar` when downstream projects should not need to declare `OkHttp` or `Jackson` themselves.

Run tests:

```bash
mvn test
```

## Quick Start

```java
import com.opencloud.sdk.OpenCloudClient;
import com.opencloud.sdk.OpenCloudClientConfig;
import com.opencloud.sdk.auth.BearerTokenAuthProvider;

OpenCloudClient client = OpenCloudClient.create(
    OpenCloudClientConfig.builder()
        .baseUrl("https://cloud.example.com")
        .authProvider(new BearerTokenAuthProvider("YOUR_TOKEN"))
        .build()
);
```

## Authentication

Bearer token:

```java
OpenCloudClient bearerClient = OpenCloudClient.create(
    OpenCloudClientConfig.builder()
        .baseUrl("https://cloud.example.com")
        .authProvider(new BearerTokenAuthProvider("YOUR_TOKEN"))
        .build()
);
```

Basic auth:

```java
import com.opencloud.sdk.auth.BasicAuthProvider;

OpenCloudClient basicClient = OpenCloudClient.create(
    OpenCloudClientConfig.builder()
        .baseUrl("https://cloud.example.com")
        .authProvider(new BasicAuthProvider("username", "password"))
        .build()
);
```

## LibreGraph Usage

The recommended way is now the typed API. `JsonNode` access is still available for uncommon or fast-moving endpoints.

Get current user:

```java
import com.opencloud.sdk.graph.model.User;

User me = client.graph().me().getProfileModel().getBody();
```

Update current user:

```java
import com.opencloud.sdk.graph.model.User;
import com.opencloud.sdk.graph.model.UserUpdateRequest;

UserUpdateRequest request = UserUpdateRequest.builder()
    .preferredLanguage("en")
    .displayName("Albert Einstein")
    .build();

User updated = client.graph().me().updateProfile(request).getBody();
```

List users:

```java
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.User;

CollectionResponse<User> users = client.graph().users().listModel().getBody();
```

Get a user:

```java
User user = client.graph().users().getModel("90eedea1-dea1-90ee-a1de-ee90a1deee90").getBody();
```

Create a user:

```java
import com.opencloud.sdk.graph.model.UserCreateRequest;

UserCreateRequest request = UserCreateRequest.builder()
    .onPremisesSamAccountName("max.mustermann")
    .displayName("Max Mustermann")
    .mail("max.mustermann@example.org")
    .build();

User createdUser = client.graph().users().create(request).getBody();
```

List drives:

```java
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.Drive;

CollectionResponse<Drive> drives = client.graph().drives().listModel().getBody();
```

Filter drives:

```java
CollectionResponse<Drive> projectDrives = client.graph().drives().listModel("driveType eq 'project'").getBody();
```

List beta drives:

```java
CollectionResponse<Drive> betaDrives = client.graph().drives().listBetaModel().getBody();
```

Create a drive:

```java
import com.opencloud.sdk.graph.model.Drive;
import com.opencloud.sdk.graph.model.DriveCreateRequest;

DriveCreateRequest request = DriveCreateRequest.builder()
    .name("Mars")
    .description("Team space mars project")
    .build();

Drive createdDrive = client.graph().drives().create(request).getBody();
```

Get a drive root:

```java
import com.opencloud.sdk.graph.model.DriveItem;

DriveItem root = client.graph().drives().getRootModel("a0ca6a90-a365-4782-871e-d44447bbc668").getBody();
```

Create a share link:

```java
import com.opencloud.sdk.graph.model.DriveLinkRequest;
import com.opencloud.sdk.graph.model.Permission;

DriveLinkRequest request = DriveLinkRequest.builder()
    .type("view")
    .build();

Permission link = client.graph().drives()
    .createLinkModel("drive-id", "item-id", request)
    .getBody();
```

List groups:

```java
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.Group;

CollectionResponse<Group> groups = client.graph().groups().listModel().getBody();
```

Create a group:

```java
import com.opencloud.sdk.graph.model.GroupCreateRequest;
import com.opencloud.sdk.graph.model.Group;

GroupCreateRequest request = GroupCreateRequest.builder()
    .displayName("Physics")
    .build();

Group createdGroup = client.graph().groups().create(request).getBody();
```

Create an invitation:

```java
import com.opencloud.sdk.graph.model.Invitation;
import com.opencloud.sdk.graph.model.InvitationCreateRequest;

InvitationCreateRequest request = InvitationCreateRequest.builder()
    .invitedUserEmail("guest@example.com")
    .invitedUserDisplayName("Guest User")
    .sendInvitationMessage(true)
    .inviteRedirectUrl("https://cloud.example.com")
    .build();

Invitation invitation = client.graph().invitations().create(request).getBody();
```

List applications:

```java
import com.opencloud.sdk.graph.model.Application;
import com.opencloud.sdk.graph.model.CollectionResponse;

CollectionResponse<Application> applications = client.graph().applications()
    .listApplicationsModel()
    .getBody();
```

Get a permission role definition:

```java
import com.opencloud.sdk.graph.model.RoleDefinition;

RoleDefinition roleDefinition = client.graph().applications()
    .getRoleDefinitionModel("role-id")
    .getBody();
```

List education schools:

```java
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.EducationSchool;

CollectionResponse<EducationSchool> schools = client.graph().education()
    .listSchoolsModel()
    .getBody();
```

List education classes:

```java
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.EducationClass;

CollectionResponse<EducationClass> classes = client.graph().education()
    .listClassesModel()
    .getBody();
```

Get activities:

```java
import com.opencloud.sdk.graph.model.Activity;
import com.opencloud.sdk.graph.model.CollectionResponse;

CollectionResponse<Activity> activities = client.graph().activities()
    .listModel("resourceid:a-space-id depth:2")
    .getBody();
```

List items shared with me:

```java
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.DriveItem;

CollectionResponse<DriveItem> sharedWithMe = client.graph().me()
    .listSharedWithMeModel()
    .getBody();
```

List permissions for a drive item:

```java
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.Permission;

CollectionResponse<Permission> permissions = client.graph().drives()
    .listPermissionsModel("drive-id", "item-id")
    .getBody();
```

Update a permission:

```java
import com.opencloud.sdk.graph.model.Permission;
import com.opencloud.sdk.graph.model.PermissionUpdateRequest;

PermissionUpdateRequest request = PermissionUpdateRequest.builder()
    .expirationDateTime("2026-12-31T23:59:59Z")
    .build();

Permission permission = client.graph().drives()
    .updatePermission("drive-id", "item-id", "perm-id", request)
    .getBody();
```

Create a root share link:

```java
import com.opencloud.sdk.graph.model.Permission;
import com.opencloud.sdk.graph.model.DriveLinkRequest;

Permission rootLink = client.graph().drives()
    .createRootLinkModel(
        "drive-id",
        DriveLinkRequest.builder().type("view").build()
    )
    .getBody();
```

Invite users to a drive item:

```java
import java.util.Arrays;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.graph.model.DriveInviteRequest;
import com.opencloud.sdk.graph.model.InviteRecipient;

JsonNode inviteResult = client.graph().drives().invite(
    "drive-id",
    "item-id",
    DriveInviteRequest.builder()
        .recipients(Arrays.asList(InviteRecipient.of("guest@example.com")))
        .roles(Arrays.asList("read"))
        .sendInvitation(true)
        .build()
).getBody();
```

Assign tags:

```java
import java.util.Arrays;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.graph.model.TagAssignmentRequest;

TagAssignmentRequest request = TagAssignmentRequest.builder()
    .resourceId("drive-id!item-id")
    .tags(Arrays.asList("tag-a", "tag-b"))
    .build();

JsonNode tags = client.graph().tags().assign(request).getBody();
```

Add a group member:

```java
client.graph().groups().addMember("group-id", "directory-object-id");
```

List app role assignments for a user:

```java
import com.opencloud.sdk.graph.model.AppRoleAssignment;
import com.opencloud.sdk.graph.model.CollectionResponse;

CollectionResponse<AppRoleAssignment> assignments = client.graph().users()
    .listAppRoleAssignmentsModel("user-id")
    .getBody();
```

Create an app role assignment:

```java
import com.opencloud.sdk.graph.model.AppRoleAssignment;

AppRoleAssignment request = AppRoleAssignment.builder()
    .appRoleId("app-role-id")
    .principalId("user-id")
    .resourceId("resource-id")
    .build();

AppRoleAssignment assignment = client.graph().users()
    .createAppRoleAssignment("user-id", request)
    .getBody();
```

Export personal data:

```java
client.graph().users().exportPersonalData("user-id");
```

Add a user to a school:

```java
client.graph().education().addUserToSchool("school-id", "user-id");
```

Add a class to a school:

```java
client.graph().education().addClassToSchool("school-id", "class-id");
```

Add a teacher to a class:

```java
client.graph().education().addTeacherToClass("class-id", "user-id");
```

## Generic LibreGraph Usage

The typed resource APIs cover the most common paths. For less common endpoints, you can still call the raw Graph operation catalog directly.

```java
import com.opencloud.sdk.ApiRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencloud.sdk.graph.GraphOperation;

JsonNode result = client.graph().execute(
    GraphOperation.GETPERMISSIONROLEDEFINITION,
    ApiRequest.builder()
        .pathParam("role-id", "role-id-value")
        .build()
).getBody();
```

## OCS Usage

Get capabilities:

```java
import com.fasterxml.jackson.databind.JsonNode;

JsonNode capabilities = client.ocs().getCapabilities().getBody();
```

Get user signing key:

```java
import com.fasterxml.jackson.databind.JsonNode;

JsonNode signingKey = client.ocs().getUserSigningKey().getBody();
```

Call an arbitrary OCS endpoint:

```java
import com.opencloud.sdk.ApiRequest;
import com.fasterxml.jackson.databind.JsonNode;

JsonNode response = client.ocs().execute(
    "GET",
    "/cloud/capabilities",
    ApiRequest.builder().build()
).getBody();
```

## WebDAV Usage

Upload a file:

```java
client.webDav().upload(
    "/files/demo/readme.txt",
    "hello opencloud".getBytes("UTF-8"),
    "text/plain"
);
```

Download a file:

```java
byte[] content = client.webDav().download("/files/demo/readme.txt").getBody();
```

Create a folder:

```java
client.webDav().makeCollection("/files/demo/docs");
```

Copy a file:

```java
client.webDav().copy(
    "/files/demo/readme.txt",
    "/files/demo/readme-copy.txt",
    true
);
```

Move a file:

```java
client.webDav().move(
    "/files/demo/readme-copy.txt",
    "/files/demo/archive/readme.txt",
    true
);
```

Delete a file:

```java
client.webDav().delete("/files/demo/archive/readme.txt");
```

## Current Structure

- `OpenCloudClient` is the main SDK entry point.
- `graph()` exposes typed resource APIs for `me`, `users`, `drives`, `groups`, `invitations`, `applications`, `education`, `tags`, and `activities`, plus the low-level operation executor.
- `ocs()` exposes convenience methods plus a generic executor.
- `webDav()` exposes common file operations plus a generic executor.

## Release Assets

- `LICENSE` contains the Apache 2.0 license text.
- `CHANGELOG.md` tracks notable SDK changes.
- `example/` contains a minimal consumer project that shows how to use the SDK as a dependency.
- `.github/workflows/ci.yml` runs Maven tests and package builds on GitHub Actions.
- `.github/workflows/publish.yml` publishes the package to GitHub Packages on tag push or manual trigger.

## GitHub Packages

The Maven package repository is configured as:

```text
https://maven.pkg.github.com/Lizehangz/opencloud-sdk-java
```

The included publish workflow uses `GITHUB_TOKEN` and the `github` server id from `pom.xml`.

## Notes

- This version is intentionally pragmatic: it is easy to extend without code generation tooling.
- The official LibreGraph spec is included in the repository as `libre-graph-v1.0.yaml`.
- The SDK now supports both typed POJO access and `JsonNode` fallback access.
