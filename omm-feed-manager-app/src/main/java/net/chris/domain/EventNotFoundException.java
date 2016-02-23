package net.chris.domain;


public class EventNotFoundException extends Exception {

    private String caerusEventId;

    public EventNotFoundException(final Builder builder) {
        this.caerusEventId = builder.caerusEventId;
    }

    @Override
    public String getMessage() {
        return String.format("Event with Caerus ID %s not found", caerusEventId);
    }

    public static final Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private String caerusEventId;

        public Builder withCaerusEventId(String caerusEventId) {
            this.caerusEventId = caerusEventId;
            return this;
        }

        public EventNotFoundException build() {
            return new EventNotFoundException(this);
        }
    }
}
