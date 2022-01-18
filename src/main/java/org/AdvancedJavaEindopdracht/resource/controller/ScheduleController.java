package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.model.schedule.ScheduleDto;
import org.AdvancedJavaEindopdracht.resource.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Returns a list of all schedules.
     *
     * @return      response entity with list of all schedules
     */
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> get() {
        return ResponseEntity.ok(scheduleService.getAll());
    }

    /**
     * Returns a single schedule.
     *
     * @param id    id of the schedule to find
     * @return      response entity with single schedule
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(scheduleService.getById(id));
    }

    /**
     * Post a single schedule.
     *
     * @param scheduleDto   schedule to post
     * @return              response entity with posted schedule
     */
    @PostMapping
    public ResponseEntity<ScheduleDto> post(@RequestBody @Valid ScheduleDto scheduleDto) {
        return ResponseEntity.ok(scheduleService.persist(scheduleDto));
    }

    /**
     * Put a single schedule.
     *
     * @param id            id of the schedule to put
     * @param scheduleDto   schedule to put
     * @return              response entity with put schedule
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDto> put(@PathVariable long id, @RequestBody @Valid ScheduleDto scheduleDto) {
        return ResponseEntity.ok(scheduleService.put(id, scheduleDto));
    }

    /**
     * Patch a single schedule.
     *
     * @param id            id of the schedule to patch
     * @param scheduleDto   consultation to patch
     * @return              response entity with patched schedule
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleDto> patch(@PathVariable int id, @RequestBody ScheduleDto scheduleDto) {
        return ResponseEntity.ok(scheduleService.patch(id, scheduleDto));
    }

    /**
     * Delete a single schedule.
     *
     * @param id    id of the schedule to delete
     * @return      response entity with deleted schedule
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleDto> delete(@PathVariable int id) {
        return ResponseEntity.ok(scheduleService.delete(id));
    }
}
