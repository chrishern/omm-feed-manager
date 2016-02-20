package net.chris.messaging;

import org.springframework.jms.annotation.JmsListener;

public class IncomingMessageListener {

	@JmsListener(destination = "minuteMarketsUpdateTopic")
	public void receiveMessage(final String message) {
		System.out.println("MESSAGE RECEIVED: " + message);
	}
}
