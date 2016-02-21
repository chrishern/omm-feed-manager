package net.chris.messaging;

import java.io.IOException;

import org.springframework.jms.annotation.JmsListener;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.chris.caerus.output.CaerusOutput;

public class IncomingMessageListener {

	private ObjectMapper objectMapper;
	
	public IncomingMessageListener(final ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@JmsListener(destination = "minuteMarketsUpdateTopic")
	public void receiveMessage(final String message) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("MESSAGE RECEIVED: " + message);
		
		final CaerusOutput caerusOutput = objectMapper.readValue(message, CaerusOutput.class);
		
		System.out.println("SUCCESSFULLY READ MESSAGE");
	}
}
