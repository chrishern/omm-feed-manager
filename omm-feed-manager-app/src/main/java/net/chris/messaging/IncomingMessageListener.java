package net.chris.messaging;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.chris.api.caerus.output.CaerusOutput;
import net.chris.incident.IncidentProcessor;
import org.springframework.jms.annotation.JmsListener;

public class IncomingMessageListener {

	private ObjectMapper objectMapper;
	private IncidentProcessor incidentProcessor;
	
	public IncomingMessageListener(final ObjectMapper objectMapper, final IncidentProcessor incidentProcessor) {
		this.objectMapper = objectMapper;
		this.incidentProcessor = incidentProcessor;
	}
	
	@JmsListener(destination = "minuteMarketsUpdateTopic")
	public void receiveMessage(final String message) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("MESSAGE RECEIVED: " + message);
		
		final CaerusOutput caerusOutput = objectMapper.readValue(message, CaerusOutput.class);
		
		System.out.println("SUCCESSFULLY READ MESSAGE");
		
		incidentProcessor.processIncidentUpdate(caerusOutput);
	}
}
