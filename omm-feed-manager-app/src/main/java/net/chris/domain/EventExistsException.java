package net.chris.domain;


public class EventExistsException extends Exception {

    private String openBetEventId;

    public EventExistsException(final Builder builder) {
        this.openBetEventId = builder.openBetEventId;
    }

    @Override
    public String getMessage() {
        return String.format("Event with OpenBet ID %s already exists", openBetEventId);
    }

    public static final Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private String openBetEventId;

        public Builder withOpenBetEventId(String openBetEventId) {
            this.openBetEventId = openBetEventId;
            return this;
        }

        public EventExistsException build() {
            return new EventExistsException(this);
        }
    }
}
