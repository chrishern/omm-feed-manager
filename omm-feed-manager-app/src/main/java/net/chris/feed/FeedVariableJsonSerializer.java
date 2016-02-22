package net.chris.feed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to convert a FeedVariable to JSON and back.
 */
public class FeedVariableJsonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(FeedVariable obj) {

        String jsonData = "";

        if (obj != null) {
            try {
                jsonData = mapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("FeedVariable JSON Serialization Exception", e);
            }
        }
        return jsonData;
    }

    public static String toJSON(List<FeedVariable> feedVariableCollection) {

        String jsonData = "";

        if (feedVariableCollection != null) {
            try {
                jsonData = mapper.writeValueAsString(feedVariableCollection);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("FeedVariable JSON Serialization Exception", e);
            }
        }
        return jsonData;
    }

    public static FeedVariable toObject(String feedVariableJson) {

        FeedVariable feedVariable = new FeedVariable();

        if (feedVariable != null) {
            try {
                feedVariable = mapper.readValue(feedVariableJson, FeedVariable.class);
            } catch (IOException e) {
                throw new RuntimeException("FeedVariable JSON Deserialization Exception", e);
            }
        }
        return feedVariable;
    }

    public static List<FeedVariable> toObjectCollection(String feedVariableCollectionJson) {

        List<FeedVariable> incidents = new ArrayList<FeedVariable>();

        if (feedVariableCollectionJson != null && !feedVariableCollectionJson.isEmpty()) {
            try {
                incidents = mapper.readValue(feedVariableCollectionJson, new TypeReference<List<FeedVariable>>() {
                });
            } catch (IOException e) {
                throw new RuntimeException("FeedVariable Collection JSON Deserialization Exception", e);
            }
        }
        return incidents;
    }
}
