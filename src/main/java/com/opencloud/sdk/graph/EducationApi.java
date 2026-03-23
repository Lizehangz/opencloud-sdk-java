package com.opencloud.sdk.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.opencloud.sdk.ApiRequest;
import com.opencloud.sdk.ApiResponse;
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.EducationClass;
import com.opencloud.sdk.graph.model.EducationSchool;
import com.opencloud.sdk.graph.model.EducationUser;
import com.opencloud.sdk.graph.model.EducationUserCreateRequest;
import com.opencloud.sdk.graph.model.EducationUserUpdateRequest;
import com.opencloud.sdk.graph.model.ReferenceRequest;

import java.io.IOException;

public final class EducationApi extends GraphResourceApi {
    EducationApi(GraphApi graphApi) {
        super(graphApi);
    }

    public ApiResponse<CollectionResponse<EducationUser>> listUsersModel() throws IOException {
        return model(
            GraphOperation.LISTEDUCATIONUSERS,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<EducationUser>>() { }
        );
    }

    public ApiResponse<EducationUser> getUserModel(String userId) throws IOException {
        return model(
            GraphOperation.GETEDUCATIONUSER,
            ApiRequest.builder().pathParam("user-id", userId).build(),
            EducationUser.class
        );
    }

    public ApiResponse<EducationUser> createUser(EducationUserCreateRequest payload) throws IOException {
        return model(
            GraphOperation.CREATEEDUCATIONUSER,
            ApiRequest.builder().body(payload).build(),
            EducationUser.class
        );
    }

    public ApiResponse<EducationUser> updateUser(String userId, EducationUserUpdateRequest payload) throws IOException {
        return model(
            GraphOperation.UPDATEEDUCATIONUSER,
            ApiRequest.builder().pathParam("user-id", userId).body(payload).build(),
            EducationUser.class
        );
    }

    public ApiResponse<Void> deleteUser(String userId) throws IOException {
        return empty(
            GraphOperation.DELETEEDUCATIONUSER,
            ApiRequest.builder().pathParam("user-id", userId).build()
        );
    }

    public ApiResponse<CollectionResponse<EducationSchool>> listSchoolsModel() throws IOException {
        return model(
            GraphOperation.LISTSCHOOLS,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<EducationSchool>>() { }
        );
    }

    public ApiResponse<EducationSchool> getSchoolModel(String schoolId) throws IOException {
        return model(
            GraphOperation.GETSCHOOL,
            ApiRequest.builder().pathParam("school-id", schoolId).build(),
            EducationSchool.class
        );
    }

    public ApiResponse<EducationSchool> createSchool(EducationSchool payload) throws IOException {
        return model(
            GraphOperation.CREATESCHOOL,
            ApiRequest.builder().body(payload).build(),
            EducationSchool.class
        );
    }

    public ApiResponse<EducationSchool> updateSchool(String schoolId, EducationSchool payload) throws IOException {
        return model(
            GraphOperation.UPDATESCHOOL,
            ApiRequest.builder().pathParam("school-id", schoolId).body(payload).build(),
            EducationSchool.class
        );
    }

    public ApiResponse<Void> deleteSchool(String schoolId) throws IOException {
        return empty(
            GraphOperation.DELETESCHOOL,
            ApiRequest.builder().pathParam("school-id", schoolId).build()
        );
    }

    public ApiResponse<CollectionResponse<EducationClass>> listClassesModel() throws IOException {
        return model(
            GraphOperation.LISTCLASSES,
            ApiRequest.builder().build(),
            new TypeReference<CollectionResponse<EducationClass>>() { }
        );
    }

    public ApiResponse<EducationClass> getClassModel(String classId) throws IOException {
        return model(
            GraphOperation.GETCLASS,
            ApiRequest.builder().pathParam("class-id", classId).build(),
            EducationClass.class
        );
    }

    public ApiResponse<EducationClass> createClass(EducationClass payload) throws IOException {
        return model(
            GraphOperation.CREATECLASS,
            ApiRequest.builder().body(payload).build(),
            EducationClass.class
        );
    }

    public ApiResponse<EducationClass> updateClass(String classId, EducationClass payload) throws IOException {
        return model(
            GraphOperation.UPDATECLASS,
            ApiRequest.builder().pathParam("class-id", classId).body(payload).build(),
            EducationClass.class
        );
    }

    public ApiResponse<Void> deleteClass(String classId) throws IOException {
        return empty(
            GraphOperation.DELETECLASS,
            ApiRequest.builder().pathParam("class-id", classId).build()
        );
    }

    public ApiResponse<CollectionResponse<EducationUser>> listClassMembersModel(String classId) throws IOException {
        return model(
            GraphOperation.LISTCLASSMEMBERS,
            ApiRequest.builder().pathParam("class-id", classId).build(),
            new TypeReference<CollectionResponse<EducationUser>>() { }
        );
    }

    public ApiResponse<CollectionResponse<EducationUser>> listTeachersModel(String classId) throws IOException {
        return model(
            GraphOperation.GETTEACHERS,
            ApiRequest.builder().pathParam("class-id", classId).build(),
            new TypeReference<CollectionResponse<EducationUser>>() { }
        );
    }

    public ApiResponse<CollectionResponse<EducationUser>> listSchoolUsersModel(String schoolId) throws IOException {
        return model(
            GraphOperation.LISTSCHOOLUSERS,
            ApiRequest.builder().pathParam("school-id", schoolId).build(),
            new TypeReference<CollectionResponse<EducationUser>>() { }
        );
    }

    public ApiResponse<Void> addUserToSchool(String schoolId, String userId) throws IOException {
        return empty(
            GraphOperation.ADDUSERTOSCHOOL,
            ApiRequest.builder()
                .pathParam("school-id", schoolId)
                .body(ReferenceRequest.of(graphApi.getBaseGraphUrl() + "/v1.0/education/users/" + userId))
                .build()
        );
    }

    public ApiResponse<Void> deleteUserFromSchool(String schoolId, String userId) throws IOException {
        return empty(
            GraphOperation.DELETEUSERFROMSCHOOL,
            ApiRequest.builder()
                .pathParam("school-id", schoolId)
                .pathParam("user-id", userId)
                .build()
        );
    }

    public ApiResponse<CollectionResponse<EducationClass>> listSchoolClassesModel(String schoolId) throws IOException {
        return model(
            GraphOperation.LISTSCHOOLCLASSES,
            ApiRequest.builder().pathParam("school-id", schoolId).build(),
            new TypeReference<CollectionResponse<EducationClass>>() { }
        );
    }

    public ApiResponse<Void> addClassToSchool(String schoolId, String classId) throws IOException {
        return empty(
            GraphOperation.ADDCLASSTOSCHOOL,
            ApiRequest.builder()
                .pathParam("school-id", schoolId)
                .body(ReferenceRequest.of(graphApi.getBaseGraphUrl() + "/v1.0/education/classes/" + classId))
                .build()
        );
    }

    public ApiResponse<Void> deleteClassFromSchool(String schoolId, String classId) throws IOException {
        return empty(
            GraphOperation.DELETECLASSFROMSCHOOL,
            ApiRequest.builder()
                .pathParam("school-id", schoolId)
                .pathParam("class-id", classId)
                .build()
        );
    }

    public ApiResponse<Void> addUserToClass(String classId, String userId) throws IOException {
        return empty(
            GraphOperation.ADDUSERTOCLASS,
            ApiRequest.builder()
                .pathParam("class-id", classId)
                .body(ReferenceRequest.of(graphApi.getBaseGraphUrl() + "/v1.0/education/users/" + userId))
                .build()
        );
    }

    public ApiResponse<Void> deleteUserFromClass(String classId, String userId) throws IOException {
        return empty(
            GraphOperation.DELETEUSERFROMCLASS,
            ApiRequest.builder()
                .pathParam("class-id", classId)
                .pathParam("user-id", userId)
                .build()
        );
    }

    public ApiResponse<Void> addTeacherToClass(String classId, String userId) throws IOException {
        return empty(
            GraphOperation.ADDTEACHERTOCLASS,
            ApiRequest.builder()
                .pathParam("class-id", classId)
                .body(ReferenceRequest.of(graphApi.getBaseGraphUrl() + "/v1.0/education/users/" + userId))
                .build()
        );
    }

    public ApiResponse<Void> deleteTeacherFromClass(String classId, String userId) throws IOException {
        return empty(
            GraphOperation.DELETETEACHERFROMCLASS,
            ApiRequest.builder()
                .pathParam("class-id", classId)
                .pathParam("user-id", userId)
                .build()
        );
    }
}