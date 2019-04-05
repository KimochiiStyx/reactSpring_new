package fi.vamk.tka.reactspring.web;

import fi.vamk.tka.reactspring.model.EventRepository;
import fi.vamk.tka.reactspring.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);
    private EventRepository EventRepository;

    public EventController(EventRepository EventRepository) {
        this.EventRepository = EventRepository;
    }

    @GetMapping("/Events")
    Collection<Event> Events() {
        return EventRepository.findAll();
    }

    @GetMapping("/Event/{id}")
    ResponseEntity<?> getEvent(@PathVariable Long id) {
        Optional<Event> Event = EventRepository.findById(id);
        return Event.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/Event")
    ResponseEntity<Event> createEvent(@Valid @RequestBody Event Event) throws URISyntaxException {
        log.info("Request to create Event: {}", Event);
        Event result = EventRepository.save(Event);
        return ResponseEntity.created(new URI("/api/Event/" + result.getId()))
                .body(result);
    }

    @PutMapping("/Event")
    ResponseEntity<Event> updateEvent(@Valid @RequestBody Event Event) {
        log.info("Request to update Event: {}", Event);
        Event result = EventRepository.save(Event);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/Event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        log.info("Request to delete Event: {}", id);
        EventRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}