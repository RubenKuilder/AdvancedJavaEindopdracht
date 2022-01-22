package org.eindopdracht.resource.controller;

import org.eindopdracht.resource.dto.ScheduleDTO;
import org.eindopdracht.resource.service.ScheduleService;
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
     * @return response entity with list of all schedules
     */
    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> get() {
        return ResponseEntity.ok(scheduleService.getAll());
    }

    /**
     * Returns a single schedule.
     *
     * @param id id of the schedule to find
     * @return response entity with single schedule
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(scheduleService.getById(id));
    }

    /**
     * Post a single schedule.
     *
     * @param scheduleDto schedule to post
     * @return response entity with posted schedule
     */
    @PostMapping
    public ResponseEntity<ScheduleDTO> post(@RequestBody @Valid ScheduleDTO scheduleDto) {
        return ResponseEntity.ok(scheduleService.persist(scheduleDto));
    }

    /**
     * Put a single schedule.
     *
     * @param id          id of the schedule to put
     * @param scheduleDto schedule to put
     * @return response entity with put schedule
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> put(@PathVariable long id, @RequestBody @Valid ScheduleDTO scheduleDto) {
        return ResponseEntity.ok(scheduleService.put(id, scheduleDto));
    }

    /**
     * Delete a single schedule.
     *
     * @param id id of the schedule to delete
     * @return response entity with deleted schedule
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleDTO> delete(@PathVariable int id) {
        return ResponseEntity.ok(scheduleService.delete(id));
    }
}
