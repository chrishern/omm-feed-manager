package net.chris.incident.adapter;


import net.chris.api.caerus.output.Incident;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

public class ModelIncidentAdapterTest {

    public static final int GAME_SECONDS = 20;
    public static final String PERIOD = "FIRST_HALF";
    public static final String INCIDENT_TYPE = "BOOKING";

    @Test
    public void mapsFromCaerusIncident() {
        // arrange
        final Incident caerusIncident = new Incident();

        caerusIncident.setGameSeconds(GAME_SECONDS);
        caerusIncident.setPeriod(PERIOD);
        caerusIncident.setType(INCIDENT_TYPE);

        final net.chris.api.model.input.Incident expectedModelIncident = new net.chris.api.model.input.Incident();
        expectedModelIncident.setGameSeconds(GAME_SECONDS);
        expectedModelIncident.setIncidentType(INCIDENT_TYPE);
        expectedModelIncident.setPeriod(PERIOD);

        // act
        final net.chris.api.model.input.Incident actualModelIncident = ModelIncidentAdapter.fromCaerusIncident(caerusIncident);

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

        final net.chris.api.model.input.Incident expectedModelIncident = new net.chris.api.model.input.Incident();
        expectedModelIncident.setGameSeconds(GAME_SECONDS);
        expectedModelIncident.setIncidentType(INCIDENT_TYPE);
        expectedModelIncident.setPeriod("PRE_MATCH");

        // act
        final net.chris.api.model.input.Incident actualModelIncident = ModelIncidentAdapter.fromCaerusIncident(caerusIncident);

        // assert
        ReflectionAssert.assertReflectionEquals(expectedModelIncident, actualModelIncident);
    }
}
