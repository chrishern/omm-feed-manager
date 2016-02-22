package net.chris.feed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to convert a FeedIncident to JSON and back.
 */
public class FeedIncidentJsonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(FeedIncident obj) {

        String jsonData = "";

        if(obj!=null) {
            try {
                jsonData = mapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("FeedIncident JSON Serialization Exception", e);
            }
        }
        return jsonData;
    }

    public static String toJSON(List<FeedIncident> feedIncidentCollection) {

        String jsonData = "";

        if(feedIncidentCollection!=null) {
            try {
                jsonData = mapper.writeValueAsString(feedIncidentCollection);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("FeedIncident JSON Serialization Exception", e);
            }
        }
        return jsonData;
    }

    public static FeedIncident toObject(String feedIncidentJson) {

        FeedIncident feedIncident = new FeedIncident();

        if(feedIncident!=null) {
            try {
                feedIncident = mapper.readValue(feedIncidentJson, FeedIncident.class) ;
            } catch (IOException e) {
                throw new RuntimeException("FeedIncident JSON Deserialization Exception", e);
            }
        }
        return feedIncident;
    }

    public static List<FeedIncident> toObjectCollection(String feedIncidentCollectionJson) {

        List<FeedIncident> incidents = new ArrayList<FeedIncident>();

        if(feedIncidentCollectionJson!=null && !feedIncidentCollectionJson.isEmpty()) {
            try {
                incidents = mapper.readValue(feedIncidentCollectionJson, new TypeReference<List<FeedIncident>>() { });
            } catch (IOException e) {
                throw new RuntimeException("FeedIncident Collection JSON Deserialization Exception", e);
            }
        }
        return incidents;
    }
}