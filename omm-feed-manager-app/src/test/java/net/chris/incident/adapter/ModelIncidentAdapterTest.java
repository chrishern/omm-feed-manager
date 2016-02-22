package net.chris.incident.adapter;


import com.williamhill.rnd.football.minutemarkets.domain.FootballIncident;
import com.williamhill.rnd.football.minutemarkets.villager.dto.IncidentDto;
import net.chris.api.caerus.output.Incident;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

public class ModelIncidentAdapterTest {

    public static final int GAME_SECONDS = 20;
    public static final String PERIOD = "FIRST_HALF";
    public static final String INCIDENT_TYPE = "CARD_YELLOW_AWAY";

    @Test
    public void mapsFromCaerusIncident() {
        // arrange
        final Incident caerusIncident = new Incident();

        caerusIncident.setGameSeconds(GAME_SECONDS);
        caerusIncident.setPeriod(PERIOD);
        caerusIncident.setType(INCIDENT_TYPE);

        final IncidentDto expectedModelIncident = new IncidentDto(FootballIncident.AWAY_YELLOW_CARD, GAME_SECONDS, PERIOD);

        // act
        final IncidentDto actualModelIncident = ModelIncidentAdapter.fromCaerusIncident(caerusIncident);

        // assert
        ReflectionAssert.assertReflectionEquals(expectedModelIncident, actualModelIncident);
    }

    @Test
    public void mapsFromCaerusIncidentAndConvertsIncidentType() {
        // arrange
        final Incident caerusIncident = new Incident();

        caerusIncident.setGameSeconds(GAME_SECONDS);
        caerusIncident.setPeriod("PRE_GAME");
        caerusIncident.setType(INCIDENT_TYPE);

        final IncidentDto expectedModelIncident = new IncidentDto(FootballIncident.AWAY_YELLOW_CARD, GAME_SECONDS, "PRE_MATCH");

        // act
        final IncidentDto actualModelIncident = ModelIncidentAdapter.fromCaerusIncident(caerusIncident);

        // assert
        ReflectionAssert.assertReflectionEquals(expectedModelIncident, actualModelIncident);
    }
}
