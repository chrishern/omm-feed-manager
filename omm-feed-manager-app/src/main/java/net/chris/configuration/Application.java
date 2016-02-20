package net.chris.configuration;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import net.chris.messaging.IncomingMessageListener;

@SpringBootApplication
@EnableJms
public class Application {

	@Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616/");
    }
	
	@Bean
	public ActiveMQTopic simpleTopic() {
	    return new ActiveMQTopic("minuteMarketsUpdateTopic");
	}
	
	@Bean
	public IncomingMessageListener incomingMessageListener() {
		return new IncomingMessageListener();
	}

	public static void main (final String [] args) {
		SpringApplication.run(Application.class, args);
	}
}
