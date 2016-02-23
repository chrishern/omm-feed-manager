package net.chris.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import net.chris.configuration.Application;
import org.springframework.beans.factory.annotation.Autowired;

public class DomainManager {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    public void createEvent(final EventDetails newEvent) throws EventExistsException {

        final IList<Object> openBetIds = hazelcastInstance.getList(Application.OPENBET_IDS_LIST);

        failIfEventExists(newEvent, openBetIds);

        final IMap<Object, Object> eventDetailsMap = hazelcastInstance.getMap(Application.EVENT_DETAILS_MAP);

        eventDetailsMap.put(newEvent.getCaerusId(), newEvent);
        openBetIds.add(newEvent.getOpenBetId());
    }

    public EventDetails getEvent(final String caerusId) throws EventNotFoundException, InvalidEventDetailsException {

        final IMap<Object, Object> eventDetailsMap = hazelcastInstance.getMap(Application.EVENT_DETAILS_MAP);

        final Object object = eventDetailsMap.get(caerusId);

        failIfObjectNotFound(caerusId, object);

        if (object instanceof EventDetails) {
            return (EventDetails) object;
        }

        final InvalidEventDetailsException invalidEventDetailsException = InvalidEventDetailsException.newBuilder()
                .withCaerusEventId(caerusId)
                .build();

        throw invalidEventDetailsException;
    }

    public List<EventDetails> getEvents() {
        final List<EventDetails> events = hazelcastInstance.getMap(Application.EVENT_DETAILS_MAP).values().stream().map(
                object -> (EventDetails) object)
                .collect(Collectors.toList());

        return events;
    }

    private void failIfObjectNotFound(String caerusId, Object object) throws EventNotFoundException {
        if (object == null) {
            final EventNotFoundException eventNotFoundException = EventNotFoundException.newBuilder()
                    .withCaerusEventId(caerusId)
                    .build();

            throw eventNotFoundException;
        }
    }

    private void failIfEventExists(final EventDetails newEvent, final IList<Object> openBetIds) throws EventExistsException {
        if (openBetIds.contains(newEvent.getOpenBetId())) {
            final EventExistsException eventExistsException = EventExistsException.newBuilder()
                    .withOpenBetEventId(newEvent.getOpenBetId())
                    .build();

            throw eventExistsException;
        }
    }
}
