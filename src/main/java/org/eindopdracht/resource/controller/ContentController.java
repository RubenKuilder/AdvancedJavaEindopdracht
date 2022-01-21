package org.eindopdracht.resource.controller;

import org.eindopdracht.resource.model.event.content.ContentDto;
import org.eindopdracht.resource.service.ContentService;
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
     * @return response entity with list of all content
     */
    @GetMapping
    public ResponseEntity<List<ContentDto>> get() {
        return ResponseEntity.ok(contentService.get());
    }

    /**
     * Returns a single content.
     *
     * @param id id of the content to find
     * @return response entity with single content
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContentDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(contentService.getById(id));
    }

    /**
     * Post a single content.
     *
     * @param contentDto content to post
     * @return response entity with posted content
     */
    @PostMapping
    public ResponseEntity<ContentDto> post(@RequestBody @Valid ContentDto contentDto) {
        return ResponseEntity.ok(contentService.persist(contentDto));
    }

    /**
     * Put a single content.
     *
     * @param id         id of the content to put
     * @param contentDto content to put
     * @return response entity with put content
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContentDto> put(@PathVariable long id, @RequestBody @Valid ContentDto contentDto) {
        return ResponseEntity.ok(contentService.put(id, contentDto));
    }
}
