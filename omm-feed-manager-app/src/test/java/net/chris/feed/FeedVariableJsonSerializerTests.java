package net.chris.feed;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests to confirm the FeedVariableJsonSerializer works as expected.
 */
public class FeedVariableJsonSerializerTests {

    private String variableJson = "{" +
            "          \"name\": \"Goal scored time\"," +
            "          \"value\": \"0\"," +
            "          \"period\": \"FIRST_HALF\"," +
            "          \"gameSeconds\": 171," +
            "          \"supplierName\": \"Inplay GUI\"," +
            "          \"updateTime\": 1453889256217" +
            "        }";

    @Test
    public void canConvertFeedJsonToObject() {

        FeedVariable deSerialisedObject = FeedVariableJsonSerializer.toObject(variableJson);

        assertNotNull(deSerialisedObject);
        assertEquals("Goal scored time", deSerialisedObject.getName());
        assertEquals("0", deSerialisedObject.getValue());
        assertEquals("FIRST_HALF", deSerialisedObject.getPeriod());
        assertEquals(171, deSerialisedObject.getGameSeconds());
        assertEquals("Inplay GUI", deSerialisedObject.getSupplierName());
        assertEquals(1453889256217l, deSerialisedObject.getUpdateTime().longValue());
    }
    @Test
    public void canConvertFeedVariableToJsonAndBack() {

        FeedVariable sourceObject = new FeedVariable("Goal scored time", "0", "FIRST_HALF", 171, "Inplay GUI", 1453889256217l);
        String json = FeedVariableJsonSerializer.toJSON(sourceObject);
        assertNotNull(json);

        FeedVariable deSerializedObject = FeedVariableJsonSerializer.toObject(json);
        assertNotNull(deSerializedObject);

        assertEquals(sourceObject.getName(), deSerializedObject.getName());
        assertEquals(sourceObject.getValue(), deSerializedObject.getValue());
        assertEquals(sourceObject.getPeriod(), deSerializedObject.getPeriod());
        assertEquals(sourceObject.getGameSeconds(), deSerializedObject.getGameSeconds());
        assertEquals(sourceObject.getSupplierName(), deSerializedObject.getSupplierName());
        assertEquals(sourceObject.getUpdateTime(), deSerializedObject.getUpdateTime());
    }
}
