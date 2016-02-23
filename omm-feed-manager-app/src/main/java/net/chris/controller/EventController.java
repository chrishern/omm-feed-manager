package net.chris.controller;

import java.net.URI;

import net.chris.domain.DomainManager;
import net.chris.domain.EventDetails;
import net.chris.domain.EventExistsException;
import net.chris.domain.EventNotFoundException;
import net.chris.domain.InvalidEventDetailsException;
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

    private DomainManager domainManager;

    public EventController(final DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createEvent(final @RequestBody EventDetails event) throws EventExistsException {
        domainManager.createEvent(event);

        return ResponseEntity.created(URI.create(event.getCaerusId())).build();
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventDetails getEvent(final @PathVariable("eventId") String eventId) throws EventNotFoundException, InvalidEventDetailsException {
        return domainManager.getEvent(eventId);
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
