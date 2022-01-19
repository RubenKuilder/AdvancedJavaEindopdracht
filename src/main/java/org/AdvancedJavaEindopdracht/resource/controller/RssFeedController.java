package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.RssFeedDTO;
import org.AdvancedJavaEindopdracht.resource.model.RssFeed;
import org.AdvancedJavaEindopdracht.resource.service.RssFeedService;
import org.springframework.http.HttpStatus;
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

    /**
     * Returns a list of all RSS feeds.
     *
     * @return      response entity with list of all RSS feeds
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<RssFeedDTO>> getRssFeeds(){
        return ResponseEntity.ok(service.getRssFeeds());
    }

    /**
     * Returns a single RSS feed.
     *
     * @param id    id of the RSS feed to find
     * @return      response entity with single RSS feed
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RssFeedDTO> getRssFeed(@PathVariable("id") final Integer id){
        return ResponseEntity.ok(service.getRssFeed(id));
    }

    /**
     * Post a single RSS feed.
     *
     * @param rssFeed   RSS feed to post
     * @return          response entity with posted RSS feed
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RssFeedDTO> postRssFeed(@Valid @RequestBody RssFeed rssFeed){
        return ResponseEntity.ok(service.create(rssFeed));
    }

    /**
     * Put a single RSS feed.
     *
     * @param id        id of the RSS feed to put
     * @param rssFeed   RSS feed to put
     * @return          response entity with put RSS feed
     */
    @PutMapping("/{id}")
    public ResponseEntity<RssFeedDTO> putRssFeed(@PathVariable("id") final Integer id, @Valid @RequestBody RssFeed rssFeed){
        return ResponseEntity.ok(service.update(rssFeed, id));
    }

    /**
     * Delete a single RSS feed.
     *
     * @param id    id of the RSS feed to delete
     * @return      response entity with deleted RSS feed
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<RssFeedDTO> deleteRssFeed(@PathVariable("id") final Integer id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}
