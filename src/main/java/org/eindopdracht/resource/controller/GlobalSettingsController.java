package org.eindopdracht.resource.controller;

import org.eindopdracht.resource.dto.GlobalSettingsDto;
import org.eindopdracht.resource.model.GlobalSettings;
import org.eindopdracht.resource.service.GlobalSettingsService;
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

    /**
     * Returns a list of all global settings.
     *
     * @return      response entity with list of all global settings
     */
    @GetMapping()
    public ResponseEntity<List<GlobalSettingsDto>> getSettings()
    {
        return new ResponseEntity<>(globalSettingsService.getGlobalSettings(), HttpStatus.OK);
    }

    /**
     * Returns a single global setting.
     *
     * @param id    id of the global setting to find
     * @return      response entity with single global setting
     */
    @GetMapping("/{id}")
    public ResponseEntity<GlobalSettingsDto> getSettingsById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(globalSettingsService.getGlobalSettingsById(id), HttpStatus.OK);
    }

    /**
     * Post a single global setting.
     *
     * @param globalSettings    global setting to post
     * @return                  response entity with posted global setting
     */
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalSettingsDto> createSettings(@RequestBody GlobalSettings globalSettings)
    {
        return new ResponseEntity<>(globalSettingsService.createGlobalSettings(globalSettings), HttpStatus.OK);
    }

    /**
     * Delete a single global setting.
     *
     * @param id    id of the global setting to delete
     * @return      response entity with deleted global setting
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalSettingsDto> deleteSettings(@PathVariable Integer id)
    {
        return new ResponseEntity<>(globalSettingsService.deleteGlobalSettings(id), HttpStatus.OK);
    }

    /**
     * Put a single global setting.
     *
     * @param id                id of the global setting to put
     * @param globalSettings    global setting to put
     * @return                  response entity with put global setting
     */
    @PutMapping(value = "/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalSettingsDto> updateSettings(@PathVariable Integer id, @RequestBody GlobalSettings globalSettings)
    {
        return new ResponseEntity<>(globalSettingsService.updateGlobalSettings(globalSettings, id), HttpStatus.OK);
    }
}
