package com.opencloud.sdk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencloud.sdk.graph.model.EducationUserCreateRequest;
import com.opencloud.sdk.graph.model.EducationUserUpdateRequest;
import com.opencloud.sdk.graph.model.Invitation;
import com.opencloud.sdk.graph.model.InvitationCreateRequest;
import com.opencloud.sdk.graph.model.Permission;
import com.opencloud.sdk.graph.model.UserCreateRequest;
import com.opencloud.sdk.graph.model.UserUpdateRequest;
import org.junit.Assert;
import org.junit.Test;

public class UserSerializationTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldSerializeUserCreateRequestWithSchemaFieldNames() throws Exception {
        UserCreateRequest request = UserCreateRequest.builder()
            .onPremisesSamAccountName("max.mustermann")
            .displayName("Max Mustermann")
            .mail("max.mustermann@example.org")
            .build();

        JsonNode json = objectMapper.valueToTree(request);

        Assert.assertEquals("max.mustermann", json.get("onPremisesSamAccountName").asText());
        Assert.assertNull(json.get("onPremisesSAMAccountName"));
        Assert.assertNull(json.get("externalId"));
        Assert.assertNull(json.get("primaryRole"));
    }

    @Test
    public void shouldSerializeUserUpdateRequestWithSchemaFieldNames() throws Exception {
        UserUpdateRequest request = UserUpdateRequest.builder()
            .onPremisesSamAccountName("max.mustermann")
            .preferredLanguage("en")
            .build();

        JsonNode json = objectMapper.valueToTree(request);

        Assert.assertEquals("max.mustermann", json.get("onPremisesSamAccountName").asText());
        Assert.assertEquals("en", json.get("preferredLanguage").asText());
        Assert.assertNull(json.get("onPremisesSAMAccountName"));
    }

    @Test
    public void shouldSerializeEducationUserCreateRequestWithEducationFields() throws Exception {
        EducationUserCreateRequest request = EducationUserCreateRequest.builder()
            .onPremisesSamAccountName("student.001")
            .displayName("Student One")
            .externalId("EX12345")
            .primaryRole("student")
            .build();

        JsonNode json = objectMapper.valueToTree(request);

        Assert.assertEquals("student.001", json.get("onPremisesSamAccountName").asText());
        Assert.assertEquals("EX12345", json.get("externalId").asText());
        Assert.assertEquals("student", json.get("primaryRole").asText());
        Assert.assertNull(json.get("onPremisesSAMAccountName"));
        Assert.assertNull(json.get("preferredLanguage"));
    }

    @Test
    public void shouldSerializeEducationUserUpdateRequestWithoutPreferredLanguage() throws Exception {
        EducationUserUpdateRequest request = EducationUserUpdateRequest.builder()
            .displayName("Student One")
            .externalId("EX12345")
            .primaryRole("student")
            .build();

        JsonNode json = objectMapper.valueToTree(request);

        Assert.assertEquals("EX12345", json.get("externalId").asText());
        Assert.assertEquals("student", json.get("primaryRole").asText());
        Assert.assertNull(json.get("preferredLanguage"));
    }

    @Test
    public void shouldSerializeInvitationCreateRequestAsFlatEmailField() throws Exception {
        InvitationCreateRequest request = InvitationCreateRequest.builder()
            .invitedUserEmail("guest@example.org")
            .invitedUserDisplayName("Guest User")
            .sendInvitationMessage(true)
            .inviteRedirectUrl("https://localhost/accept")
            .build();

        JsonNode json = objectMapper.valueToTree(request);

        Assert.assertEquals("guest@example.org", json.get("invitedUserEmailAddress").asText());
        Assert.assertEquals("Guest User", json.get("invitedUserDisplayName").asText());
    }

    @Test
    public void shouldDeserializeInvitationAndPermissionLibreGraphFields() throws Exception {
        Invitation invitation = objectMapper.readValue(
            "{\"id\":\"inv-1\",\"invitedUserEmailAddress\":\"guest@example.org\",\"status\":\"pending\"}",
            Invitation.class
        );
        Permission permission = objectMapper.readValue(
            "{\"id\":\"perm-1\",\"@libre.graph.permissions.actions\":[\"libre.graph/driveItem/basic/read\"],\"link\":{\"@libre.graph.displayName\":\"Share Link\",\"@libre.graph.quickLink\":true},\"invitation\":{\"invitedBy\":{\"user\":{\"id\":\"u1\",\"displayName\":\"Admin\"}}}}",
            Permission.class
        );

        Assert.assertEquals("guest@example.org", invitation.getInvitedUserEmailAddress());
        Assert.assertEquals("pending", invitation.getStatus());
        Assert.assertEquals("Share Link", permission.getLink().getDisplayName());
        Assert.assertTrue(permission.getLink().getQuicklink());
        Assert.assertEquals(1, permission.getActions().size());
        Assert.assertEquals("u1", permission.getInvitation().getInvitedBy().getUser().getId());
    }
}
