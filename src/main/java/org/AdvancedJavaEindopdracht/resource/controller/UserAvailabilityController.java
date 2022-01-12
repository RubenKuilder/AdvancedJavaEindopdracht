package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.UserAvailabilityDTO;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;
import org.AdvancedJavaEindopdracht.resource.service.UserAvailabilityService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/availability")
public class UserAvailabilityController {

    private final UserAvailabilityService service;

    public UserAvailabilityController(UserAvailabilityService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserAvailabilityDTO>> getUserAvailabilities(){
        return ResponseEntity.ok(service.getUserAvailabilities());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserAvailabilityDTO> getUserAvailability(@PathVariable("id") final Integer id){
        return ResponseEntity.ok(service.getUserAvailability(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserAvailabilityDTO> postAvailability(@Valid @RequestBody UserAvailability availability){
        return ResponseEntity.ok(service.create(availability));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAvailabilityDTO> putAvailability(@PathVariable("id") final Integer id, @Valid @RequestBody UserAvailability availability){
        return ResponseEntity.ok(service.update(availability, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable("id") final Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
