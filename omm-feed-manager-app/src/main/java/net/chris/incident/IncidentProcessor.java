/**
 * 
 */
package net.chris.incident;

import java.util.List;
import java.util.stream.Collectors;

import com.williamhill.rnd.football.minutemarkets.villager.dto.IncidentDto;
import com.williamhill.rnd.football.minutemarkets.villager.dto.MinuteMarketsFootballEventDto;
import net.chris.api.caerus.output.CaerusOutput;
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

        final List<IncidentDto> modelIncidents = incidentMessage.getIncidents().stream().map(
                caerusIncident -> ModelIncidentAdapter.fromCaerusIncident(caerusIncident))
                .collect(Collectors.toList());

        // TODO - These need to be picked up from some storage
        final MinuteMarketsFootballEventDto modelInput = new MinuteMarketsFootballEventDto(
                "Home Team",
                "Away Team",
                3,
                "FIRST_HALF",
                2.15,
                3.1,
                3.3,
                2.0,
                19.0,
                40.0,
                11.0,
                40.0,
                27.0,
                19.0,
                8.0,
                1.0,
                modelIncidents
        );

        modelClient.sendIncidentUpdate(modelInput);
    }
}
