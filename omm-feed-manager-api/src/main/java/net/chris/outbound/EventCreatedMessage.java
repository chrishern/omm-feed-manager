package net.chris.outbound;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;

@JsonDeserialize(builder = EventCreatedMessage.Builder.class)
public class EventCreatedMessage implements OutboundMessage {

    private final String openBetId;

    public EventCreatedMessage(final Builder builder) {
        this.openBetId = builder.openBetId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("messageType", OutboundMessage.EVENT_CREATED)
                .add("openBetId", openBetId)
                .toString();
    }

    public static final class Builder {

        private String openBetId;

        public Builder withOpenBetId(String openBetId) {
            this.openBetId = openBetId;
            return this;
        }

        public EventCreatedMessage build() {
            return new EventCreatedMessage(this);
        }
    }
}
