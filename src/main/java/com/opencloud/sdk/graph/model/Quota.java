package com.opencloud.sdk.graph.model;

public class Quota {
    private Long total;
    private Long remaining;
    private Long used;
    private String state;

    public static Builder builder() {
        return new Builder();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getRemaining() {
        return remaining;
    }

    public void setRemaining(Long remaining) {
        this.remaining = remaining;
    }

    public Long getUsed() {
        return used;
    }

    public void setUsed(Long used) {
        this.used = used;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static final class Builder {
        private final Quota quota = new Quota();

        public Builder total(Long value) {
            quota.setTotal(value);
            return this;
        }

        public Builder remaining(Long value) {
            quota.setRemaining(value);
            return this;
        }

        public Builder used(Long value) {
            quota.setUsed(value);
            return this;
        }

        public Builder state(String value) {
            quota.setState(value);
            return this;
        }

        public Quota build() {
            return quota;
        }
    }
}
