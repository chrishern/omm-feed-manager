package net.chris.messaging;

import net.chris.outbound.EventCreatedMessage;
import net.chris.outbound.ModelUpdateMessage;
import org.springframework.jms.core.JmsTemplate;

public class OutboundMessageSender {

    private JmsTemplate jmsTemplate;

    public OutboundMessageSender(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void processModelOutput(final ModelUpdateMessage modelOutput) {
        System.out.println("Publishing to topic:" + modelOutput);

        jmsTemplate.convertAndSend(modelOutput);
    }

    public void sendEventCreatedMessage(final EventCreatedMessage message) {
        System.out.println("Publishing to topic:" + message);

        jmsTemplate.convertAndSend(message);
    }
}
