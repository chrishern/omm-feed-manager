package net.chris.test.common;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ActiveMqPublishSubscribeTest {

	@Autowired
	private ConnectionFactory connectionFactory;
	
	protected Connection connection;
	protected MessageProducer producer;
	protected Session session;
	
	protected static final String SAMPLE_MESSAGE = "{\"eventId\":259407,\"tradingId\":\"264e7970-d499-11e5-82ae-005056b00125\",\"homeTeamName\":\"South China\",\"awayTeamName\":\"Yuen Long District\",\"period\":\"PRE_GAME\",\"suspended\":false,\"incidents\":[{\"type\":\"EXTERNAL_TEAM_IDS\",\"period\":\"PRE_GAME\",\"gameSeconds\":0}],\"variables\":[{\"name\":\"Goal scored time\",\"value\":\"0\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Inplay GUI\",\"updateTime\":1455618994323},{\"name\":\"PF Price Over Goal Line\",\"value\":\"0.68\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455620629277},{\"name\":\"PF Price Under Goal Line\",\"value\":\"1.08\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455620629277},{\"name\":\"PF Asian Goal Line\",\"value\":\"3.25\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455618995060},{\"name\":\"Price Away\",\"value\":\"1.38\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455622129259},{\"name\":\"Price Home\",\"value\":\"0.48\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455622129259},{\"name\":\"Asian Handicap Line\",\"value\":\"-1.5\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455618995060},{\"name\":\"Asian Goal Line\",\"value\":\"3.25\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455618995060},{\"name\":\"Price Over Goal Line\",\"value\":\"0.68\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455620629277},{\"name\":\"Price Under Goal Line\",\"value\":\"1.08\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455620629277},{\"name\":\"PF Asian Handicap Line\",\"value\":\"-1.5\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455618995060},{\"name\":\"PF Price Home\",\"value\":\"0.48\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455622129259},{\"name\":\"PF Price Away\",\"value\":\"1.38\",\"period\":\"PRE_GAME\",\"gameSeconds\":0,\"supplierName\":\"Prematch sbobet\",\"updateTime\":1455622129259}]}";
	
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
		pauseForAsyncProcessing();	// Avoid connections being shut down too early
		producer.close();
        session.close();
	}

	protected void pauseForAsyncProcessing() throws InterruptedException {
		Thread.sleep(500);
	}
}
