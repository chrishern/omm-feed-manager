/**
 * 
 */
package net.chris.incident;

import java.util.List;
import java.util.stream.Collectors;

import com.williamhill.rnd.football.minutemarkets.villager.dto.IncidentDto;
import com.williamhill.rnd.football.minutemarkets.villager.dto.MinuteMarketsFootballEventDto;
import net.chris.api.caerus.output.CaerusOutput;
import net.chris.domain.DomainManager;
import net.chris.domain.EventDetails;
import net.chris.domain.EventNotFoundException;
import net.chris.domain.InvalidEventDetailsException;
import net.chris.incident.adapter.ModelIncidentAdapter;
import net.chris.messaging.OutboundMessageSender;
import net.chris.model.ModelClient;
import net.chris.model.output.ModelOutboundMessage;
import net.chris.outbound.ModelUpdateMessage;

public class IncidentProcessor {

	private ModelClient modelClient;
    private OutboundMessageSender outboundMessageSender;
    private DomainManager domainManager;

    public IncidentProcessor(final ModelClient modelClient, final OutboundMessageSender outboundMessageSender, final DomainManager domainManager) {
        this.modelClient = modelClient;
        this.outboundMessageSender = outboundMessageSender;
        this.domainManager = domainManager;
    }

	public void processIncidentUpdate(final CaerusOutput incidentMessage) throws EventNotFoundException, InvalidEventDetailsException {
        final EventDetails eventDetails = domainManager.getEvent(String.valueOf(incidentMessage.getEventId()));
        final ModelOutboundMessage modelOutput = sendUpdateToModel(incidentMessage, eventDetails);

        final ModelUpdateMessage modelUpdateMessage = ModelUpdateMessage.newBuilder()
                .withCaerusId(eventDetails.getCaerusId())
                .withOpenBetId(eventDetails.getOpenBetId())
                .withModelData(modelOutput)
                .build();

        outboundMessageSender.processModelOutput(modelUpdateMessage);
    }

    private ModelOutboundMessage sendUpdateToModel(final CaerusOutput incidentMessage, final EventDetails eventDetails) {

        final List<IncidentDto> modelIncidents = incidentMessage.getIncidents().stream().map(
                caerusIncident -> ModelIncidentAdapter.fromCaerusIncident(caerusIncident))
                .collect(Collectors.toList());

        final CurrentEventState currentState = getCurrentState(incidentMessage, modelIncidents);

        final MinuteMarketsFootballEventDto modelInput = new MinuteMarketsFootballEventDto(
                eventDetails.getHomeTeamName(),
                eventDetails.getAwayTeamName(),
                currentState.gameSeconds,
                currentState.period,
                eventDetails.getPreMatchHomeOverRoundedPrice(),
                eventDetails.getPreMatchDrawOverRoundedPrice(),
                eventDetails.getPreMatchAwayOverRoundedPrice(),
                eventDetails.getInPlayGoalsExpectedRemaining(),
                eventDetails.getPreMatchGoalsExpectancy(),
                eventDetails.getPreMatchBookingsPointsExpectancy(),
                eventDetails.getPreMatchCornersExpectancy(),
                eventDetails.getPreMatchThrowInExpectancy(),
                eventDetails.getPreMatchFreeKickExpectancy(),
                eventDetails.getPreMatchGoalKickExpectancy(),
                eventDetails.getPreMatchShotOnTargetExpectancy(),
                eventDetails.getPreMatchWoodworkExpectancy(),
                modelIncidents
        );

        return modelClient.sendIncidentUpdate(modelInput);
    }

    private CurrentEventState getCurrentState(final CaerusOutput incidentMessage, final List<IncidentDto> modelIncidents) {

        final CurrentEventState currentEventState = new CurrentEventState();

        currentEventState.period = ModelIncidentAdapter.toModelPeriod(incidentMessage.getPeriod());

        if (!incidentMessage.getPeriod().equals("PRE_MATCH")) {
            final IncidentDto finalIncident = modelIncidents.get(modelIncidents.size() - 1);
            currentEventState.gameSeconds = finalIncident.getGameSeconds();
        }

        return currentEventState;
    }

    private class CurrentEventState {

        private String period;
        private int gameSeconds;
    }
}
