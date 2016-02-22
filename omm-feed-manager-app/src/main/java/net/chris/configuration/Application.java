package net.chris.configuration;

import javax.jms.ConnectionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import net.chris.incident.IncidentProcessor;
import net.chris.messaging.IncomingMessageListener;
import net.chris.model.ModelClient;
import net.chris.model.ModelOutputProcessor;
import net.chris.model.ModelRestClient;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJms
public class Application {

	@Bean
	public ActiveMQTopic oneMinuteMarketsTopic() {
	    return new ActiveMQTopic("minuteMarketsUpdateTopic");
	}
	
	@Bean
	public IncomingMessageListener incomingMessageListener(final ConnectionFactory connectionFactory) {
		return new IncomingMessageListener(objectMapper(), incidentProcessor(connectionFactory));
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		final ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.registerModule(new JodaModule());

		return objectMapper;
	}
	
	@Bean
	public IncidentProcessor incidentProcessor(final ConnectionFactory connectionFactory) {
		return new IncidentProcessor(modelClient(), modelOutputProcessor(connectionFactory));
	}

    @Bean
    public ModelClient modelClient() {
        return new ModelRestClient("http://localhost:8080", restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@Bean
	public JmsTemplate jmsTemplate(final ConnectionFactory connectionFactory) {
		final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		final ActiveMQTopic topic = new ActiveMQTopic("minuteMarketsModelUpdateTopic");

		jmsTemplate.setDefaultDestination(topic);

		return jmsTemplate;
	}

    @Bean
    public ModelOutputProcessor modelOutputProcessor(final ConnectionFactory connectionFactory) {
        return new ModelOutputProcessor(jmsTemplate(connectionFactory));
    }

	public static void main (final String [] args) {
		SpringApplication.run(Application.class, args);
	}
}
