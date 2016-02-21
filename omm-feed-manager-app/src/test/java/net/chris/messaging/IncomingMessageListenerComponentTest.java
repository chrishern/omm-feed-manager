package net.chris.messaging;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.chris.caerus.output.CaerusOutput;
import net.chris.incident.IncidentProcessor;
import net.chris.test.common.ActiveMqPublishSubscribeTest;

/**
 * Component test for the {@link IncomingMessageListener}.  Its aim is to make sure that the component is 
 * able to successfully read messages posted to the appropriate ActiveMQ topic and then pass them onto the 
 * interested component.  Any testing of internal logic will be done in a standalone unit test.
 * 
 * @author chris
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MessageListenerComponentTestConfig.class)
public class IncomingMessageListenerComponentTest extends ActiveMqPublishSubscribeTest {

	@Autowired
	public IncidentProcessor mockedIncidentProcessor;
	
	@Test
	public void testMessageIsReceivedAndPassedToProcessor() throws Exception {
		// arrange
		final TextMessage message = session.createTextMessage();
		message.setText(SAMPLE_MESSAGE);

		// act
		producer.send(message);
		
		// assert
		pauseForAsyncProcessing();	// avoid any timing issues due to async processing
		verify(mockedIncidentProcessor).processIncidentUpdate(any(CaerusOutput.class));
	}

}
