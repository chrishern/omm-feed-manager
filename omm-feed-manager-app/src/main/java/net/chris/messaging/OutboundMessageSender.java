package net.chris.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.chris.api.model.output.OutboundMessage;
import net.chris.outbound.EventCreatedMessage;
import org.springframework.jms.core.JmsTemplate;

public class OutboundMessageSender {

    private JmsTemplate jmsTemplate;

    public OutboundMessageSender(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void processModelOutput(final OutboundMessage modelOutput) {

        // TODO - The OutboundMessage is not serializable
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String message = objectMapper.writeValueAsString(modelOutput);

            System.out.println("Publishing to topic:" + message);

            jmsTemplate.convertAndSend(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendEventCreatedMessage(final EventCreatedMessage message) {
        System.out.println("Publishing to topic:" + message);

        jmsTemplate.convertAndSend(message);
    }
}
