package net.chris.integrationtest;

import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.chris.configuration.Application;
import net.chris.test.common.ActiveMqPublishSubscribeTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class EndToEndIT extends ActiveMqPublishSubscribeTest {
	
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
