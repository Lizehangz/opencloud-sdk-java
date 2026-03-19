package com.opencloud.sdk.graph;

import java.util.LinkedHashMap;
import java.util.Map;

public final class GraphPayload {
    private GraphPayload() {
    }

    public static Builder object() {
        return new Builder();
    }

    public static final class Builder {
        private final Map<String, Object> values = new LinkedHashMap<String, Object>();

        public Builder put(String key, Object value) {
            values.put(key, value);
            return this;
        }

        public Map<String, Object> build() {
            return new LinkedHashMap<String, Object>(values);
        }
    }
}
