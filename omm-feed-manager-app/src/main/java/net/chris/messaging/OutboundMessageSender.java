package net.chris.messaging;

import com.google.gson.Gson;
import net.chris.outbound.EventCreatedMessage;
import net.chris.outbound.IncidentMessage;
import net.chris.outbound.ModelUpdateMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;

public class OutboundMessageSender {

    private JmsTemplate jmsTemplate;
    private ActiveMQTopic modelUpdateTopic;
    private ActiveMQTopic incidentTopic;
    private Gson gson;

    public OutboundMessageSender(final JmsTemplate jmsTemplate, final ActiveMQTopic modelUpdateTopic, final ActiveMQTopic incidentTopic) {
        this.gson = new Gson();
        this.jmsTemplate = jmsTemplate;
        this.modelUpdateTopic = modelUpdateTopic;
        this.incidentTopic = incidentTopic;
    }

    public void sendModelOutput(final ModelUpdateMessage modelOutput) {
        System.out.println("Publishing to model update topic:" + modelOutput);

        jmsTemplate.convertAndSend(modelUpdateTopic, gson.toJson(modelOutput));
    }

    public void sendIncidentOutput(final IncidentMessage incident) {
        System.out.println("Publishing to incident topic:" + incident);

        jmsTemplate.convertAndSend(incidentTopic, gson.toJson(incident));
    }

    public void sendEventCreatedMessage(final EventCreatedMessage message) {
//        System.out.println("Publishing to topic:" + message);
//
//        jmsTemplate.convertAndSend(gson.toJson(message));
    }
}
