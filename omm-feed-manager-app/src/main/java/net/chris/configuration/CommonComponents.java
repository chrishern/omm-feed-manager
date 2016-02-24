package net.chris.configuration;


import javax.jms.ConnectionFactory;

import net.chris.domain.DomainManager;
import net.chris.messaging.OutboundMessageSender;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 * Components used in multiple other beans.
 */
@Configuration
public class CommonComponents {

    @Bean
	public DomainManager domainManager() {
		return new DomainManager();
	}

    @Bean
    public OutboundMessageSender outboundMessageSender(final ConnectionFactory connectionFactory) {
        return new OutboundMessageSender(modelJmsTemplate(connectionFactory), modelUpdateTopic(), incidentUpdateTopic());
    }

    @Bean
    public JmsTemplate modelJmsTemplate(final ConnectionFactory connectionFactory) {
        final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);

        return jmsTemplate;
    }

    @Bean
    public ActiveMQTopic modelUpdateTopic() {
        return new ActiveMQTopic("minuteMarketsModelUpdateTopic");
    }

    @Bean
    public ActiveMQTopic incidentUpdateTopic() {
        return new ActiveMQTopic("incidentsTopic");
    }

//    @Bean
//    public JmsTemplate incidentJmsTemplate(final ConnectionFactory connectionFactory) {
//        final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//        final ActiveMQTopic topic = new ActiveMQTopic("incidentsTopic");
//
//        jmsTemplate.setDefaultDestination(topic);
//
//        return jmsTemplate;
//    }
}
