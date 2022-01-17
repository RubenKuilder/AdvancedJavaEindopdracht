package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.model.event.EventDto;
import org.AdvancedJavaEindopdracht.resource.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Returns a list of all events.
     *
     * @return      response entity with list of all events
     */
    @GetMapping
    public ResponseEntity<List<EventDto>> get() {
        return ResponseEntity.ok(eventService.get());
    }

    /**
     * Returns a single event.
     *
     * @return      response entity with single event
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    /**
     * Post a single event.
     *
     * @return      response entity with posted event
     */
    @PostMapping
    public ResponseEntity<EventDto> post(@RequestBody @Valid EventDto eventDto) {
        return ResponseEntity.ok(eventService.persist(eventDto));
    }

    /**
     * Put a single event.
     *
     * @return      response entity with put event
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventDto> put(@PathVariable long id, @RequestBody @Valid EventDto eventDto) {
        return ResponseEntity.ok(eventService.put(id, eventDto));
    }

    /**
     * Patch a single event.
     *
     * @return      response entity with patched event
     */
    @PatchMapping("/{id}")
    public ResponseEntity<EventDto> patch(@PathVariable int id, @RequestBody EventDto eventDto) {
        return ResponseEntity.ok(eventService.patch(id, eventDto));
    }

    /**
     * Delete a single event.
     *
     * @return      response entity with deleted event
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<EventDto> delete(@PathVariable int id) {
        return ResponseEntity.ok(eventService.delete(id));
    }
}
