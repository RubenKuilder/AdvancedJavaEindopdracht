package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.model.event.content.ContentDto;
import org.AdvancedJavaEindopdracht.resource.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    /**
     * Returns a list of all content.
     *
     * @return      response entity with list of all content
     */
    @GetMapping
    public ResponseEntity<List<ContentDto>> get() {
        return ResponseEntity.ok(contentService.get());
    }

    /**
     * Returns a single content.
     *
     * @return      response entity with single content
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContentDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(contentService.getById(id));
    }

    /**
     * Post a single content.
     *
     * @return      response entity with posted content
     */
    @PostMapping
    public ResponseEntity<ContentDto> post(@RequestBody @Valid ContentDto contentDto) {
        return ResponseEntity.ok(contentService.persist(contentDto));
    }

    /**
     * Put a single content.
     *
     * @return      response entity with put content
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContentDto> put(@PathVariable long id, @RequestBody @Valid ContentDto contentDto) {
        return ResponseEntity.ok(contentService.put(id, contentDto));
    }

    /**
     * Patch a single content.
     *
     * @return      response entity with patched content
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ContentDto> patch(@PathVariable int id, @RequestBody ContentDto contentDto) {
        return ResponseEntity.ok(contentService.patch(id, contentDto));
    }
}
