package net.chris.incident.adapter;


import com.williamhill.rnd.football.minutemarkets.domain.FootballIncident;
import com.williamhill.rnd.football.minutemarkets.villager.dto.IncidentDto;
import net.chris.api.caerus.output.Incident;

public final class ModelIncidentAdapter {

    public static IncidentDto fromCaerusIncident(final Incident caerusIncident) {

        final String modelPeriod = toModelPeriod(caerusIncident.getPeriod());
        final FootballIncident modelIncidentType = FootballIncident.getByType(caerusIncident.getType());

        final IncidentDto modelIncident = new IncidentDto(modelIncidentType, caerusIncident.getGameSeconds(), modelPeriod);

        return modelIncident;
    }

    public static String toModelPeriod(final String caerusPeriod) {
        if (caerusPeriod.equals("PRE_GAME")) {
            return "PRE_MATCH";
        }

        return caerusPeriod;
    }
}
