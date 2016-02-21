package net.chris.integrationtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.chris.configuration.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class EndToEndIT {

	@Autowired
	private ConnectionFactory connectionFactory;
	
	private Connection connection;
	private MessageProducer producer;
	private Session session;
	
	private static final String SAMPLE_MESSAGE = "{\"eventId\":259407,\"tradingId\":\"264e7970-d499-11e5-82ae-005056b00125\",\"homeTeamName\":\"South China\",\"awayTeamName\":\"Yuen Long District\",\"period\":\"PRE_GAME\",\"suspended\":false,\"incidents\":[],\"variables\":[{\"name\":\"Goal scored time\",\"value\":\"0\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Inplay GUI\",\"updateTime\":1455618994323}]}";
	
	@Before
	public void setup() throws Exception {
		connection = connectionFactory.createConnection();
		
		connection.start();
		
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		final Topic topic = session.createTopic("minuteMarketsUpdateTopic");
		
		producer = session.createProducer(topic);
	}
	
	@After
	public void shutdown() throws Exception {
		Thread.sleep(100);	// Avoid connections being shut down too early
		producer.close();
        session.close();
	}
	
	@Test
	public void test() throws Exception {
        // arrange
		final TextMessage message = session.createTextMessage();
		message.setText(SAMPLE_MESSAGE);

		// act
		producer.send(message);
		
		// assert
	}
}
