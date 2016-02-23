package net.chris.controller;

import java.net.URI;
import java.util.List;

import net.chris.domain.EventDetails;
import net.chris.domain.EventExistsException;
import net.chris.domain.EventNotFoundException;
import net.chris.domain.InvalidEventDetailsException;
import net.chris.event.EventProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventProcessor eventProcessor;

    public EventController(final EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createEvent(final @RequestBody EventDetails event) throws EventExistsException {
        eventProcessor.processEventCreation(event);

        return ResponseEntity.created(URI.create(event.getCaerusId())).build();
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventDetails getEvent(final @PathVariable("eventId") String eventId) throws EventNotFoundException, InvalidEventDetailsException {
        return eventProcessor.getEvent(eventId);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventDetails> getEvents() {
        return eventProcessor.getEvents();
    }

    @ExceptionHandler(EventExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void eventExists(final EventExistsException exception) {
        System.out.println(exception.getMessage());
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void eventNotFound(final EventNotFoundException exception) {
        System.out.println(exception.getMessage());
    }

    @ExceptionHandler(InvalidEventDetailsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void invalidEventDetails(final InvalidEventDetailsException exception) {
        System.out.println(exception.getMessage());
    }
}
