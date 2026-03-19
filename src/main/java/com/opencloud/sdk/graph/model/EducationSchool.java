package com.opencloud.sdk.graph.model;

import java.util.Objects;

public class EducationSchool {
    private String id;
    private String displayName;
    private String schoolNumber;
    private String terminationDate;

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    public static final class Builder {
        private final EducationSchool school = new EducationSchool();

        public Builder displayName(String value) {
            school.setDisplayName(value);
            return this;
        }

        public Builder schoolNumber(String value) {
            school.setSchoolNumber(value);
            return this;
        }

        public Builder terminationDate(String value) {
            school.setTerminationDate(value);
            return this;
        }

        public EducationSchool build() {
            return school;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EducationSchool)) {
            return false;
        }
        EducationSchool that = (EducationSchool) o;
        return Objects.equals(id, that.id)
            && Objects.equals(displayName, that.displayName)
            && Objects.equals(schoolNumber, that.schoolNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, schoolNumber);
    }

    @Override
    public String toString() {
        return "EducationSchool{id='" + id + "', displayName='" + displayName + "'}";
    }
}
