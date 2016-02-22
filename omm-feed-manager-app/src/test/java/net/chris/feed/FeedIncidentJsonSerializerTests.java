package net.chris.feed;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests to confirm the FeedIncidentJsonSerializer works as expected.
 */
public class FeedIncidentJsonSerializerTests {

    private String incidentJSON = "{" +
            "          \"type\": \"AWAY_THROW_IN\"," +
            "          \"period\": \"FIRST_HALF\"," +
            "          \"gameSeconds\": 610" +
            "        }";

    @Test
    public void canConvertFeedJsonToObject() {

        FeedIncident deSerialisedObject = FeedIncidentJsonSerializer.toObject(incidentJSON);

        assertNotNull(deSerialisedObject);
        assertEquals("AWAY_THROW_IN", deSerialisedObject.getType());
        assertEquals("FIRST_HALF", deSerialisedObject.getPeriod());
        assertEquals(610, deSerialisedObject.getGameSeconds());
    }
    @Test
    public void canConvertFeedIncidentToJsonAndBack() {

        FeedIncident sourceObject = new FeedIncident("AWAY_THROW_IN", "FIRST_HALF", 610);
        String json = FeedIncidentJsonSerializer.toJSON(sourceObject);
        assertNotNull(json);

        FeedIncident deSerializedObject = FeedIncidentJsonSerializer.toObject(json);
        assertNotNull(deSerializedObject);

        assertEquals(sourceObject.getType(), deSerializedObject.getType());
        assertEquals(sourceObject.getPeriod(), deSerializedObject.getPeriod());
        assertEquals(sourceObject.getGameSeconds(), deSerializedObject.getGameSeconds());
    }
}
