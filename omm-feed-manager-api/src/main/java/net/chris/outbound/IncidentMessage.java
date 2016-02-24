package net.chris.outbound;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = IncidentMessage.Builder.class)
public class IncidentMessage implements OutboundMessage {

    private final String openBetId;
    private final String homeTeamName;
    private final String awayTeamName;
    private final String type;
    private final Integer gameSeconds;

    public IncidentMessage(final Builder builder) {
        this.openBetId = builder.openBetId;
        this.homeTeamName = builder.homeTeamName;
        this.awayTeamName = builder.awayTeamName;
        this.type = builder.type;
        this.gameSeconds = builder.gameSeconds;
    }

    public String getOpenBetId() {
        return openBetId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getType() {
        return type;
    }

    public Integer getGameSeconds() {
        return gameSeconds;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String openBetId;
        private String homeTeamName;
        private String awayTeamName;
        private String type;
        private Integer gameSeconds;

        public Builder withOpenBetId(String openBetId) {
            this.openBetId = openBetId;
            return this;
        }

        public Builder withHomeTeamName(String homeTeamName) {
            this.homeTeamName = homeTeamName;
            return this;
        }

        public Builder withAwayTeamName(String awayTeamName) {
            this.awayTeamName = awayTeamName;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withGameSeconds(Integer gameSeconds) {
            this.gameSeconds = gameSeconds;
            return this;
        }

        public IncidentMessage build() {
            return new IncidentMessage(this);
        }
    }
}
