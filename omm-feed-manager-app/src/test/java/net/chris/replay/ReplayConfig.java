package net.chris.replay;

import javax.jms.ConnectionFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * Mat Joe Graham Tom
 */

public class ReplayConfig {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String[] JSON_KEYS = new String[]{"incidents", "variables"};
    private static final String GAMESECONDS_KEY = "gameSeconds";

    public static JmsTemplate createJmsTemplate(String user, String pass, String url){
        return new JmsTemplate(createConnectionFactory(user, pass, url));
    }

    private static ConnectionFactory createConnectionFactory(String user, String pass, String url){
        return new ActiveMQConnectionFactory(user, pass, URI.create(url));
    }

    public static Deque<ReplayFile> jsonFilesToReplay(File directory) throws IOException {

        File[] files = directory.listFiles(jsonFileFilter());

        List<ReplayFile> replayFiles = generateReplayFiles(files);

        Collections.sort(replayFiles);

        return toDeque(replayFiles);
    }

    private static Deque<ReplayFile> toDeque(Collection<ReplayFile> collection){
        ArrayDeque<ReplayFile> arrayDeque = new ArrayDeque<>();
        for (ReplayFile o : collection){
            arrayDeque.add(o);
        }
        return arrayDeque;
    }

    private static FileFilter jsonFileFilter(){
        return new FileFilter(){
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".json");
            }
        };
    }

    private static List<ReplayFile> generateReplayFiles(File[] files) throws IOException {
        List<ReplayFile> replayFiles = new ArrayList<ReplayFile>();

        for (File file: files){
            ReplayFile replayFile = ReplayFile.newBuilder()
                    .withFile(file)
                    .withWhenToSend(getWhenToSend(file))
                    .build();
            replayFiles.add(replayFile);
        }


        return replayFiles;
    }

    private static long getWhenToSend(File file) throws IOException {

        Map<String, Object> jsonObject = objectMapper.readValue(file, Map.class);


        List<Map<String, Object>> mapList = new ArrayList<>();

        for (String jsonKey : JSON_KEYS){

            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) jsonObject.get(jsonKey);
            mapList.addAll(listOfMaps);
        }

        long replayTime = largestGameSeconds(mapList);

        return replayTime;

    }

    private static long largestGameSeconds(List<Map<String, Object>> listOfMaps) {
        int largestSoFar = 0;
        for (Map<String, Object> map : listOfMaps){

            int gameSeconds = (int)map.get(GAMESECONDS_KEY);

            if (gameSeconds > largestSoFar){
                largestSoFar = gameSeconds;
            }

        }

        return largestSoFar;
    }

    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String replaceEventId(String json, String[] eventIdFields, String newEventId ) throws IOException {
        JsonNode root = objectMapper.readTree(json);
        replaceEventId(root, eventIdFields, newEventId);
        return objectMapper.writeValueAsString(root);
    }


    private static void replaceEventId(JsonNode node, String[] eventIdFields, String newEventId){
        Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
        while(iterator.hasNext()){

            Map.Entry<String, JsonNode> entry = iterator.next();
            String entryField = entry.getKey();

            for (String eventIdField : eventIdFields){
                    if (entryField.equals(eventIdField)){

                        //change Id
                        ObjectNode obj = (ObjectNode) node;
                        obj.put(entryField, newEventId);

                    }
            }

        }
    }

}
