package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.model.consultation.ConsultationDto;
import org.AdvancedJavaEindopdracht.resource.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/consultation")
public class ConsultationController {
    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    /**
     * Returns a list of all consultations.
     *
     * @return      response entity with list of all consultations
     */
    @GetMapping
    public ResponseEntity<List<ConsultationDto>> get() {
        return ResponseEntity.ok(consultationService.get());
    }

    /**
     * Returns a single consultation.
     *
     * @param id    id of the consultation to find
     * @return      response entity with single consultation
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(consultationService.getById(id));
    }

    /**
     * Post a single consultation.
     *
     * @param consultationDto   consultation to post
     * @return                  response entity with posted consultation
     */
    @PostMapping
    public ResponseEntity<ConsultationDto> post(@RequestBody @Valid ConsultationDto consultationDto) {
        return ResponseEntity.ok(consultationService.persist(consultationDto));
    }

    /**
     * Put a single consultation.
     *
     * @param id                id of the consultation to put
     * @param consultationDto   consultation to put
     * @return                  response entity with put consultation
     */
    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDto> put(@PathVariable long id, @RequestBody @Valid ConsultationDto consultationDto) {
        return ResponseEntity.ok(consultationService.put(id, consultationDto));
    }

    /**
     * Patch a single consultation.
     *
     * @param id                id of the consultation to patch
     * @param consultationDto   consultation to patch
     * @return                  response entity with patched consultation
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ConsultationDto> patch(@PathVariable int id, @RequestBody ConsultationDto consultationDto) {
        return ResponseEntity.ok(consultationService.patch(id, consultationDto));
    }

    /**
     * Delete a single consultation.
     *
     * @param id    id of the consultation to delete
     * @return      response entity with deleted consultation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultationDto> delete(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(consultationService.delete(id));
    }
}
