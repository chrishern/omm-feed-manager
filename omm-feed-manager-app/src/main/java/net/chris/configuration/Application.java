package net.chris.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import net.chris.incident.IncidentProcessor;
import net.chris.messaging.IncomingMessageListener;
import net.chris.model.ModelClient;
import net.chris.model.ModelRestClient;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJms
public class Application {

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
		final ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.registerModule(new JodaModule());

		return objectMapper;
	}
	
	@Bean
	public IncidentProcessor incidentProcessor() {
		return new IncidentProcessor(modelClient());
	}

    @Bean
    public ModelClient modelClient() {
        return new ModelRestClient("http://localhost:8080", restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main (final String [] args) {
		SpringApplication.run(Application.class, args);
	}
}
