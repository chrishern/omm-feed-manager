package net.chris.domain;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = EventDetails.Builder.class)
public class EventDetails implements Serializable {

    private final String caerusId;
    private final String openBetId;

    private final String homeTeamName;
    private final String awayTeamName;

    private final double preMatchHomeOverRoundedPrice;
    private final double preMatchDrawOverRoundedPrice;
    private final double preMatchAwayOverRoundedPrice;

    private final Double inPlayGoalsExpectedRemaining;

    private final Double preMatchGoalsExpectancy;
    private final Double preMatchBookingsPointsExpectancy;
    private final Double preMatchCornersExpectancy;
    private final Double preMatchThrowInExpectancy;
    private final Double preMatchFreeKickExpectancy;
    private final Double preMatchGoalKickExpectancy;
    private final Double preMatchShotOnTargetExpectancy;
    private final Double preMatchWoodworkExpectancy;

    private EventDetails(final Builder builder) {
        this.caerusId = builder.caerusId;
        this.openBetId = builder.openBetId;
        this.homeTeamName = builder.homeTeamName;
        this.awayTeamName = builder.awayTeamName;
        this.preMatchHomeOverRoundedPrice = builder.preMatchHomeOverRoundedPrice;
        this.preMatchDrawOverRoundedPrice = builder.preMatchDrawOverRoundedPrice;
        this.preMatchAwayOverRoundedPrice = builder.preMatchAwayOverRoundedPrice;
        this.inPlayGoalsExpectedRemaining = builder.inPlayGoalsExpectedRemaining;
        this.preMatchGoalsExpectancy = builder.preMatchGoalsExpectancy;
        this.preMatchBookingsPointsExpectancy = builder.preMatchBookingsPointsExpectancy;
        this.preMatchCornersExpectancy = builder.preMatchCornersExpectancy;
        this.preMatchThrowInExpectancy = builder.preMatchThrowInExpectancy;
        this.preMatchFreeKickExpectancy = builder.preMatchFreeKickExpectancy;
        this.preMatchGoalKickExpectancy = builder.preMatchGoalKickExpectancy;
        this.preMatchShotOnTargetExpectancy = builder.preMatchShotOnTargetExpectancy;
        this.preMatchWoodworkExpectancy = builder.preMatchWoodworkExpectancy;
    }

    public String getCaerusId() {
        return caerusId;
    }

    public String getOpenBetId() {
        return openBetId;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public double getPreMatchHomeOverRoundedPrice() {
        return preMatchHomeOverRoundedPrice;
    }

    public double getPreMatchDrawOverRoundedPrice() {
        return preMatchDrawOverRoundedPrice;
    }

    public double getPreMatchAwayOverRoundedPrice() {
        return preMatchAwayOverRoundedPrice;
    }

    public Double getInPlayGoalsExpectedRemaining() {
        return inPlayGoalsExpectedRemaining;
    }

    public Double getPreMatchGoalsExpectancy() {
        return preMatchGoalsExpectancy;
    }

    public Double getPreMatchBookingsPointsExpectancy() {
        return preMatchBookingsPointsExpectancy;
    }

    public Double getPreMatchCornersExpectancy() {
        return preMatchCornersExpectancy;
    }

    public Double getPreMatchThrowInExpectancy() {
        return preMatchThrowInExpectancy;
    }

    public Double getPreMatchFreeKickExpectancy() {
        return preMatchFreeKickExpectancy;
    }

    public Double getPreMatchGoalKickExpectancy() {
        return preMatchGoalKickExpectancy;
    }

    public Double getPreMatchShotOnTargetExpectancy() {
        return preMatchShotOnTargetExpectancy;
    }

    public Double getPreMatchWoodworkExpectancy() {
        return preMatchWoodworkExpectancy;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private String caerusId;
        private String openBetId;
        private String homeTeamName;
        private String awayTeamName;
        private double preMatchHomeOverRoundedPrice;
        private double preMatchDrawOverRoundedPrice;
        private double preMatchAwayOverRoundedPrice;
        private Double inPlayGoalsExpectedRemaining;
        private Double preMatchGoalsExpectancy;
        private Double preMatchBookingsPointsExpectancy;
        private Double preMatchCornersExpectancy;
        private Double preMatchThrowInExpectancy;
        private Double preMatchFreeKickExpectancy;
        private Double preMatchGoalKickExpectancy;
        private Double preMatchShotOnTargetExpectancy;
        private Double preMatchWoodworkExpectancy;

        public Builder withCaerusId(String caerusId) {
            this.caerusId = caerusId;
            return this;
        }

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

        public Builder withPreMatchHomeOverRoundedPrice(double preMatchHomeOverRoundedPrice) {
            this.preMatchHomeOverRoundedPrice = preMatchHomeOverRoundedPrice;
            return this;
        }

        public Builder withPreMatchDrawOverRoundedPrice(double preMatchDrawOverRoundedPrice) {
            this.preMatchDrawOverRoundedPrice = preMatchDrawOverRoundedPrice;
            return this;
        }

        public Builder withPreMatchAwayOverRoundedPrice(double preMatchAwayOverRoundedPrice) {
            this.preMatchAwayOverRoundedPrice = preMatchAwayOverRoundedPrice;
            return this;
        }

        public Builder withInPlayGoalsExpectedRemaining(Double inPlayGoalsExpectedRemaining) {
            this.inPlayGoalsExpectedRemaining = inPlayGoalsExpectedRemaining;
            return this;
        }

        public Builder withPreMatchGoalsExpectancy(Double preMatchGoalsExpectancy) {
            this.preMatchGoalsExpectancy = preMatchGoalsExpectancy;
            return this;
        }

        public Builder withPreMatchBookingsPointsExpectancy(Double preMatchBookingsPointsExpectancy) {
            this.preMatchBookingsPointsExpectancy = preMatchBookingsPointsExpectancy;
            return this;
        }

        public Builder withPreMatchCornersExpectancy(Double preMatchCornersExpectancy) {
            this.preMatchCornersExpectancy = preMatchCornersExpectancy;
            return this;
        }

        public Builder withPreMatchThrowInExpectancy(Double preMatchThrowInExpectancy) {
            this.preMatchThrowInExpectancy = preMatchThrowInExpectancy;
            return this;
        }

        public Builder withPreMatchFreeKickExpectancy(Double preMatchFreeKickExpectancy) {
            this.preMatchFreeKickExpectancy = preMatchFreeKickExpectancy;
            return this;
        }

        public Builder withPreMatchGoalKickExpectancy(Double preMatchGoalKickExpectancy) {
            this.preMatchGoalKickExpectancy = preMatchGoalKickExpectancy;
            return this;
        }

        public Builder withPreMatchShotOnTargetExpectancy(Double preMatchShotOnTargetExpectancy) {
            this.preMatchShotOnTargetExpectancy = preMatchShotOnTargetExpectancy;
            return this;
        }

        public Builder withPreMatchWoodworkExpectancy(Double preMatchWoodworkExpectancy) {
            this.preMatchWoodworkExpectancy = preMatchWoodworkExpectancy;
            return this;
        }

        public EventDetails build() {
            return new EventDetails(this);
        }
    }
}
