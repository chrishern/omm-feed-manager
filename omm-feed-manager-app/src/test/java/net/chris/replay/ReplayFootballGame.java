package net.chris.replay;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Deque;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

@Ignore
public class ReplayFootballGame {

    private static String data_directory = "/home/chrishern/hackathon/caerusfeed/259407";
    private static String eventIdFields[] = new String[]{"eventId"};
    private static String destination = "minuteMarketsUpdateTopic";

    private JmsTemplate replayJmsTemplate;

    @Before
    public void setup() {
        replayJmsTemplate = ReplayConfig.createJmsTemplate("admin", "admin", "tcp://localhost:61616");
        replayJmsTemplate.setPubSubDomain(true);
    }


    @Test
    public void replayFootballGame() throws IOException, InterruptedException {

        int openBetId = 12345; //Must be created via the /events controller

        Deque<ReplayFile> replayFileDeque = ReplayConfig.jsonFilesToReplay(new File(data_directory));

        DateTime startTimestamp = DateTime.now();
        long startTime = startTimestamp.getMillis();

        while (!replayFileDeque.isEmpty()) {

            ReplayFile replayFile = replayFileDeque.peek();

            if (isTimeToSend(startTime, replayFile.getWhenToSend())) {
                replayFileDeque.pop();

                //send
                DateTime now = DateTime.now();
                System.out.println(replayFile.getFile().getName() + "| " + now + " |  " + difference(startTimestamp, now));

                String jsonFromFile = ReplayConfig.readFile(replayFile.getFile().getPath(), Charset.defaultCharset());
                String modifiedJsonFromFile = ReplayConfig.replaceEventId(jsonFromFile,eventIdFields, "" + openBetId);

                replayJmsTemplate.convertAndSend(destination, modifiedJsonFromFile);

            } else {
                Thread.sleep(1);
            }

        }
    }

    private String difference(DateTime startTimestamp, DateTime now) {

        return "  " + (now.getMillis() - startTimestamp.getMillis());

    }

    private static boolean isTimeToSend(long replayerStartTime, long gameSeconds) {


        if (System.currentTimeMillis() > replayerStartTime + (gameSeconds * 1000)) {
            return true;
        }

        return false;

    }


}
