package net.chris.configuration;

import javax.jms.ConnectionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hazelcast.config.Config;
import com.hazelcast.config.ListConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.HazelcastInstance;
import net.chris.controller.EventController;
import net.chris.domain.DomainManager;
import net.chris.event.EventProcessor;
import net.chris.incident.IncidentProcessor;
import net.chris.messaging.IncomingMessageListener;
import net.chris.messaging.OutboundMessageSender;
import net.chris.model.ModelClient;
import net.chris.model.ModelRestClient;
import net.chris.web.SimpleCORSFilter;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJms
public class Application {

    public final static String EVENT_DETAILS_MAP = "eventDetailsMap";
    public final static String OPENBET_IDS_LIST = "openBetIdsList";

    @Autowired
    private HazelcastInstance instance;

    @Autowired
    private DomainManager domainManager;

    @Autowired
    private OutboundMessageSender outboundMessageSender;

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
		return new IncidentProcessor(modelClient(), outboundMessageSender, domainManager);
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
    public Config hazelcastConfig() {

        final MapConfig mapConfig = new MapConfig();
        mapConfig.setName(EVENT_DETAILS_MAP);

        final ListConfig listConfig = new ListConfig();
        listConfig.setName(OPENBET_IDS_LIST);

        return new Config()
                .addListConfig(listConfig)
                .addMapConfig(mapConfig);
    }

    @Bean
    public EventProcessor eventProcessor(final ConnectionFactory connectionFactory) {
        return new EventProcessor(domainManager, outboundMessageSender);
    }

    @Bean
    public EventController eventController(final ConnectionFactory connectionFactory) {
        return new EventController(eventProcessor(connectionFactory));
    }

    @Bean
    public SimpleCORSFilter corsFilter() {
        return new SimpleCORSFilter();
    }

	public static void main (final String [] args) {
		SpringApplication.run(Application.class, args);
	}
}
