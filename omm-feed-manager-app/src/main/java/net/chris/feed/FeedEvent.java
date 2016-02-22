package net.chris.feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Strongly typed representation of event data received from the Caerus Feed.
 */
public class FeedEvent {

    private int eventId;
    private String tradingId;
    private String homeTeamName;
    private String awayTeamName;
    private String period;
    private Boolean suspended;
    private List<FeedIncident> incidents = new ArrayList<FeedIncident>();
    private List<FeedVariable> variables = new ArrayList<FeedVariable>();

    protected FeedEvent() {
    }

    public FeedEvent(int eventId, String tradingId, String homeTeamName, String awayTeamName, String period,
                     Boolean suspended, List<FeedIncident> incidents, List<FeedVariable> variables) {

        this.eventId = eventId;
        this.tradingId = tradingId;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.period = period;
        this.suspended = suspended;
        this.incidents = incidents;
        this.variables = variables;
    }
}
