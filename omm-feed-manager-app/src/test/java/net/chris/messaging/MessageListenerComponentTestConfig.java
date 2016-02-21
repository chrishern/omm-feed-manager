package net.chris.messaging;

import static org.mockito.Mockito.mock;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.chris.configuration.Application;
import net.chris.incident.IncidentProcessor;

@SpringBootApplication
@EnableJms
public class MessageListenerComponentTestConfig {

	@Bean
	public ActiveMQTopic oneMinuteMarketsTopic() {
	    return new ActiveMQTopic("minuteMarketsUpdateTopic");
	}
	
	@Bean
	public IncomingMessageListener incomingMessageListener() {
		return new IncomingMessageListener(objectMapper(), incidentProcessor());
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public IncidentProcessor incidentProcessor() {
		return mock(IncidentProcessor.class);
	}

	public static void main (final String [] args) {
		SpringApplication.run(Application.class, args);
	}
}
