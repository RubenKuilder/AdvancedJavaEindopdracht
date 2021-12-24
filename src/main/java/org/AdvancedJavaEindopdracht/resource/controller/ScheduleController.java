package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.model.event.content.ContentDto;
import org.AdvancedJavaEindopdracht.resource.model.schedule.ScheduleDto;
import org.AdvancedJavaEindopdracht.resource.service.ContentService;
import org.AdvancedJavaEindopdracht.resource.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDto>> get() {
        return ResponseEntity.ok(scheduleService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(scheduleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ScheduleDto> post(@RequestBody @Valid ScheduleDto scheduleDto) {
        return ResponseEntity.ok(scheduleService.persist(scheduleDto));
    }
}
