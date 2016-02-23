package net.chris.event;

import java.util.List;

import net.chris.domain.DomainManager;
import net.chris.domain.EventDetails;
import net.chris.domain.EventExistsException;
import net.chris.domain.EventNotFoundException;
import net.chris.domain.InvalidEventDetailsException;
import net.chris.messaging.OutboundMessageSender;
import net.chris.outbound.EventCreatedMessage;

public class EventProcessor {

    private DomainManager domainManager;
    private OutboundMessageSender outboundMessageSender;

    public EventProcessor(final DomainManager domainManager, final OutboundMessageSender outboundMessageSender) {
        this.domainManager = domainManager;
        this.outboundMessageSender = outboundMessageSender;
    }

    public void processEventCreation(final EventDetails event) throws EventExistsException {
        domainManager.createEvent(event);

        final EventCreatedMessage eventCreatedMessage = EventCreatedMessage.newBuilder()
                .withOpenBetId(event.getOpenBetId())
                .build();

        outboundMessageSender.sendEventCreatedMessage(eventCreatedMessage);
    }

    public EventDetails getEvent(final String eventId) throws EventNotFoundException, InvalidEventDetailsException {
        return domainManager.getEvent(eventId);
    }

    public List<EventDetails> getEvents() {
        return domainManager.getEvents();
    }
}
