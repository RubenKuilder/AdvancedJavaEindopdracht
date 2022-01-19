package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.UserAvailabilityDTO;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;
import org.AdvancedJavaEindopdracht.resource.service.UserAvailabilityService;
import org.springframework.http.HttpStatus;
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

    /**
     * Returns a list of all user availabilities.
     *
     * @return      response entity with list of all user availabilities
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserAvailabilityDTO>> getUserAvailabilities(){
        return ResponseEntity.ok(service.getUserAvailabilities());
    }

    /**
     * Returns a single user availability.
     *
     * @param id    id of the user availability to find
     * @return      response entity with single user availability
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserAvailabilityDTO> getUserAvailability(@PathVariable("id") final Integer id){
        return ResponseEntity.ok(service.getUserAvailability(id));
    }

    /**
     * Post a single user availability.
     *
     * @param availability  user availability to post
     * @return              response entity with posted user availability
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserAvailabilityDTO> postAvailability(@Valid @RequestBody UserAvailability availability){
        return ResponseEntity.ok(service.create(availability));
    }

    /**
     * Put a single user availability.
     *
     * @param id            id of the user availability to put
     * @param availability  user availability to put
     * @return              response entity with put user availability
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserAvailabilityDTO> putAvailability(@PathVariable("id") final Integer id, @Valid @RequestBody UserAvailability availability){
        return ResponseEntity.ok(service.update(availability, id));
    }

    /**
     * Delete a single user availability.
     *
     * @param id    id of the user availability to delete
     * @return      response entity with deleted user availability
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<UserAvailabilityDTO> deleteAvailability(@PathVariable("id") final Integer id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}
