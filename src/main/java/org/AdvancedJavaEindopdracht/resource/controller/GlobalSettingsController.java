package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.GlobalSettingsDto;
import org.AdvancedJavaEindopdracht.resource.model.GlobalSettings;
import org.AdvancedJavaEindopdracht.resource.service.GlobalSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/settings")
public class GlobalSettingsController
{
    private final GlobalSettingsService globalSettingsService;

    private GlobalSettingsController(GlobalSettingsService globalSettingsService)
    {
        this.globalSettingsService = globalSettingsService;
    }

    @GetMapping()
    public ResponseEntity<List<GlobalSettingsDto>> getSettings()
    {
        return new ResponseEntity<>(globalSettingsService.getGlobalSettings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalSettingsDto> getSettingsById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(globalSettingsService.getGlobalSettingsById(id), HttpStatus.OK);
    }

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalSettingsDto> createSettings(@RequestBody GlobalSettings globalSettings)
    {
        return new ResponseEntity<>(globalSettingsService.createGlobalSettings(globalSettings), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalSettingsDto> deleteSettings(@PathVariable Integer id)
    {
        return new ResponseEntity<>(globalSettingsService.deleteGlobalSettings(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalSettingsDto> updateSettings(@PathVariable Integer id, @RequestBody GlobalSettings globalSettings)
    {
        return new ResponseEntity<>(globalSettingsService.updateGlobalSettings(globalSettings, id), HttpStatus.OK);
    }
}
