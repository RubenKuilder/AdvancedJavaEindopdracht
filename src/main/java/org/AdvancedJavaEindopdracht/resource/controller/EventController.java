package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.model.event.EventDto;
import org.AdvancedJavaEindopdracht.resource.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> get() {
        return ResponseEntity.ok(eventService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EventDto> post(@RequestBody @Valid EventDto eventDto) {
        return ResponseEntity.ok(eventService.persist(eventDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> put(@PathVariable long id, @RequestBody @Valid EventDto eventDto) {
        return ResponseEntity.ok(eventService.put(id, eventDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventDto> patch(@PathVariable int id, @RequestBody EventDto eventDto) {
        return ResponseEntity.ok(eventService.patch(id, eventDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        eventService.delete(id);
        return ResponseEntity.ok(String.format("Event with ID %d successfully deleted.", id));
    }
}
