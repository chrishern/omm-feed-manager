package net.chris.feed;

/**
 * Strongly typed representation of incident data received from the Caerus Feed.
 */
public class FeedIncident {

    private String type;
    private String period;
    private int gameSeconds;

    public String getType() {
        return type;
    }

    public String getPeriod() {
        return period;
    }

    public int getGameSeconds() {
        return gameSeconds;
    }

    protected FeedIncident() {
    }

    public FeedIncident(String type, String period, int gameSeconds) {
        this.type = type;
        this.period = period;
        this.gameSeconds = gameSeconds;
    }
}


