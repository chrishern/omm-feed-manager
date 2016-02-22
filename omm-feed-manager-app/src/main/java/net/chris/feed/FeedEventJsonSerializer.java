package net.chris.feed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import net.chris.feed.FeedEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to convert a FeedEvent to JSON and back.
 */
public class FeedEventJsonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(FeedEvent obj) {

        String jsonData = "";

        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        mapper.registerModule(new JodaModule());

        if(obj!=null) {
            try {
                jsonData = mapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("FeedEvent JSON Serialization Exception", e);
            }
        }
        return jsonData;
    }

    public static String toJSON(List<FeedEvent> feedEventCollection) {

        String jsonData = "";

        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        mapper.registerModule(new JodaModule());

        if(feedEventCollection!=null) {
            try {
                jsonData = mapper.writeValueAsString(feedEventCollection);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("FeedEvent JSON Serialization Exception", e);
            }
        }
        return jsonData;
    }

    public static FeedEvent toObject(String feedEventJson) {

        FeedEvent feedEvent = new FeedEvent();

        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        mapper.registerModule(new JodaModule());

        if(feedEvent!=null) {
            try {
                feedEvent = mapper.readValue(feedEventJson, FeedEvent.class) ;
            } catch (IOException e) {
                throw new RuntimeException("FeedEvent JSON Deserialization Exception", e);
            }
        }
        return feedEvent;
    }

    public static List<FeedEvent> toObjectCollection(String rowCollectionJson) {

        List<FeedEvent> rows = new ArrayList<FeedEvent>();

        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        mapper.registerModule(new JodaModule());

        if(rowCollectionJson!=null && !rowCollectionJson.isEmpty()) {
            try {
                rows = mapper.readValue(rowCollectionJson, new TypeReference<List<FeedEvent>>() { });
            } catch (IOException e) {
                throw new RuntimeException("FeedEvent Collection JSON Deserialization Exception", e);
            }
        }
        return rows;
    }
}
