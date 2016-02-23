package net.chris.messaging;

import com.google.gson.Gson;
import net.chris.outbound.EventCreatedMessage;
import net.chris.outbound.ModelUpdateMessage;
import org.springframework.jms.core.JmsTemplate;

public class OutboundMessageSender {

    private JmsTemplate jmsTemplate;
    private Gson gson;

    public OutboundMessageSender(final JmsTemplate jmsTemplate) {
        this.gson = new Gson();
        this.jmsTemplate = jmsTemplate;
    }

    public void processModelOutput(final ModelUpdateMessage modelOutput) {
        System.out.println("Publishing to topic:" + modelOutput);

        jmsTemplate.convertAndSend(gson.toJson(modelOutput));
    }

    public void sendEventCreatedMessage(final EventCreatedMessage message) {
//        System.out.println("Publishing to topic:" + message);
//
//        jmsTemplate.convertAndSend(gson.toJson(message));
    }
}
