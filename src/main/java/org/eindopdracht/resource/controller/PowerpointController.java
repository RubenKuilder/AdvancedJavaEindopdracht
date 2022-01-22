package org.eindopdracht.resource.controller;

import org.eindopdracht.resource.dto.PowerpointDTO;
import org.eindopdracht.resource.model.Powerpoint;
import org.eindopdracht.resource.service.PowerpointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/powerpoint")
public class PowerpointController {

    private final PowerpointService service;

    public PowerpointController(PowerpointService service) {
        this.service = service;
    }

    /**
     * Returns a list of all powerpoints.
     *
     * @return response entity with list of all powerpoints
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PowerpointDTO>> getPowerpoints() {
        return ResponseEntity.ok(service.getPowerpoints());
    }

    /**
     * Returns a single powerpoint.
     *
     * @param id id of the powerpoint to find
     * @return response entity with single powerpoint
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PowerpointDTO> getPowerpoint(@PathVariable("id") final Integer id) {
        return ResponseEntity.ok(service.getPowerpoint(id));
    }

    /**
     * Post a single powerpoint.
     *
     * @param powerpointDTO    powerpoint to post
     * @return              response entity with posted powerpoint
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PowerpointDTO> postPowerpoint(@Valid @RequestBody PowerpointDTO powerpointDTO){
        return ResponseEntity.ok(service.create(powerpointDTO));
    }

    /**
     * Put a single powerpoint.
     *
     * @param id            id of the powerpoint to put
     * @param powerpointDTO    powerpoint to put
     * @return              response entity with put powerpoint
     */
    @PutMapping("/{id}")
    public ResponseEntity<PowerpointDTO> putPowerpoint(@PathVariable("id") final Integer id, @Valid @RequestBody PowerpointDTO powerpointDTO) {
        return ResponseEntity.ok(service.update(powerpointDTO, id));
    }

    /**
     * Delete a single powerpoint.
     *
     * @param id id of the powerpoint to delete
     * @return response entity with deleted powerpoint
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PowerpointDTO> deletePowerpoint(@PathVariable("id") final Integer id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}
