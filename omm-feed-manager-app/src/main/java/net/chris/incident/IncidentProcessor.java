/**
 * 
 */
package net.chris.incident;

import java.util.List;
import java.util.stream.Collectors;

import net.chris.api.caerus.output.CaerusOutput;
import net.chris.api.model.input.Incident;
import net.chris.api.model.input.ModelInput;
import net.chris.incident.adapter.ModelIncidentAdapter;
import net.chris.model.ModelClient;

public class IncidentProcessor {

	private ModelClient modelClient;

    public IncidentProcessor(final ModelClient modelClient) {
        this.modelClient = modelClient;
    }

	public void processIncidentUpdate(final CaerusOutput incidentMessage) {
        sendUpdateToModel(incidentMessage);
	}

    private void sendUpdateToModel(final CaerusOutput incidentMessage) {
        final ModelInput modelInput = new ModelInput();

        // TODO - These need to be picked up from some storage
        modelInput.setAwayTeamName("Away Team");
        modelInput.setCurrentGameSeconds(3);
        modelInput.setCurrentPeriod("FIRST_HALF");
        modelInput.setHomeTeamName("Home Team");
        modelInput.setPreMatchAwayOverRoundedPrice(3.3);
        modelInput.setPreMatchBookingsPointsExpectancy(40);
        modelInput.setPreMatchCornersExpectancy(11);
        modelInput.setPreMatchDrawOverRoundedPrice(3.1);
        modelInput.setPreMatchFreeKickExpectancy(27);
        modelInput.setPreMatchGoalKickExpectancy(19);
        modelInput.setPreMatchGoalsExpectancy(2.67);
        modelInput.setPreMatchHomeOverRoundedPrice(2.15);
        modelInput.setPreMatchShotOnTargetExpectancy(8);
        modelInput.setPreMatchThrowInExpectancy(40);
        modelInput.setPreMatchWoodworkExpectancy(1);

        final List<Incident> modelIncidents = incidentMessage.getIncidents().stream().map(
                caerusIncident -> ModelIncidentAdapter.fromCaerusIncident(caerusIncident))
                    .collect(Collectors.toList());
        modelInput.setIncidents(modelIncidents);

        modelClient.sendIncidentUpdate(modelInput);
    }
}
