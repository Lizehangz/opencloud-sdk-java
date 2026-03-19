package com.opencloud.sdk.graph.model;

import java.util.Objects;

public class EducationClass {
    private String id;
    private String displayName;
    private String classification;
    private String externalId;

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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public static final class Builder {
        private final EducationClass educationClass = new EducationClass();

        public Builder displayName(String value) {
            educationClass.setDisplayName(value);
            return this;
        }

        public Builder classification(String value) {
            educationClass.setClassification(value);
            return this;
        }

        public Builder externalId(String value) {
            educationClass.setExternalId(value);
            return this;
        }

        public EducationClass build() {
            return educationClass;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EducationClass)) {
            return false;
        }
        EducationClass that = (EducationClass) o;
        return Objects.equals(id, that.id)
            && Objects.equals(displayName, that.displayName)
            && Objects.equals(classification, that.classification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, classification);
    }

    @Override
    public String toString() {
        return "EducationClass{id='" + id + "', displayName='" + displayName + "'}";
    }
}
