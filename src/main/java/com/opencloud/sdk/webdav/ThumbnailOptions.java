package com.opencloud.sdk.webdav;

public final class ThumbnailOptions {
    private final String processor;
    private final Boolean scalingUp;
    private final Boolean aspectRatio;
    private final String cacheKey;

    private ThumbnailOptions(Builder builder) {
        this.processor = builder.processor;
        this.scalingUp = builder.scalingUp;
        this.aspectRatio = builder.aspectRatio;
        this.cacheKey = builder.cacheKey;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getProcessor() {
        return processor;
    }

    public Boolean getScalingUp() {
        return scalingUp;
    }

    public Boolean getAspectRatio() {
        return aspectRatio;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public static final class Builder {
        private String processor;
        private Boolean scalingUp;
        private Boolean aspectRatio;
        private String cacheKey;

        public Builder processor(String processor) {
            this.processor = processor;
            return this;
        }

        public Builder scalingUp(Boolean scalingUp) {
            this.scalingUp = scalingUp;
            return this;
        }

        public Builder aspectRatio(Boolean aspectRatio) {
            this.aspectRatio = aspectRatio;
            return this;
        }

        public Builder cacheKey(String cacheKey) {
            this.cacheKey = cacheKey;
            return this;
        }

        public ThumbnailOptions build() {
            return new ThumbnailOptions(this);
        }
    }
}
