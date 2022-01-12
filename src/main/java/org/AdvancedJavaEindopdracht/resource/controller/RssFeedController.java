package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.PowerpointDTO;
import org.AdvancedJavaEindopdracht.resource.dto.RssFeedDTO;
import org.AdvancedJavaEindopdracht.resource.dto.UserAvailabilityDTO;
import org.AdvancedJavaEindopdracht.resource.model.Powerpoint;
import org.AdvancedJavaEindopdracht.resource.model.RssFeed;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;
import org.AdvancedJavaEindopdracht.resource.service.PowerpointService;
import org.AdvancedJavaEindopdracht.resource.service.RssFeedService;
import org.AdvancedJavaEindopdracht.resource.service.UserAvailabilityService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/rss")
public class RssFeedController {

    private final RssFeedService service;

    public RssFeedController(RssFeedService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<RssFeedDTO>> getRssFeeds(){
        return ResponseEntity.ok(service.getRssFeeds());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RssFeedDTO> getRssFeed(@PathVariable("id") final Integer id){
        return ResponseEntity.ok(service.getRssFeed(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RssFeedDTO> postRssFeed(@Valid @RequestBody RssFeed rssFeed){
        return ResponseEntity.ok(service.create(rssFeed));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RssFeedDTO> putRssFeed(@PathVariable("id") final Integer id, @Valid @RequestBody RssFeed rssFeed){
        return ResponseEntity.ok(service.update(rssFeed, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRssFeed(@PathVariable("id") final Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
