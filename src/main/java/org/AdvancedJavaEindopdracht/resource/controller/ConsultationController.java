package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.model.consultation.ConsultationDto;
import org.AdvancedJavaEindopdracht.resource.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {
    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultationDto>> get() {
        return ResponseEntity.ok(consultationService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(consultationService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ConsultationDto> post(@RequestBody @Valid ConsultationDto consultationDto) {
        return ResponseEntity.ok(consultationService.persist(consultationDto));
    }
}