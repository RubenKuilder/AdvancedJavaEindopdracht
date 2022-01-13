package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.PowerpointDTO;
import org.AdvancedJavaEindopdracht.resource.model.Powerpoint;
import org.AdvancedJavaEindopdracht.resource.service.PowerpointService;
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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PowerpointDTO>> getPowerpoints(){
        return ResponseEntity.ok(service.getPowerpoints());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PowerpointDTO> getPowerpoint(@PathVariable("id") final Integer id){
        return ResponseEntity.ok(service.getPowerpoint(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PowerpointDTO> postPowerpoint(@Valid @RequestBody Powerpoint powerpoint){
        return ResponseEntity.ok(service.create(powerpoint));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PowerpointDTO> putPowerpoint(@PathVariable("id") final Integer id, @Valid @RequestBody Powerpoint powerpoint){
        return ResponseEntity.ok(service.update(powerpoint, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PowerpointDTO> deletePowerpoint(@PathVariable("id") final Integer id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}
