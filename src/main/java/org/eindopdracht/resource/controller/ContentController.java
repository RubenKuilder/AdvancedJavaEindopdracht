package org.eindopdracht.resource.controller;

import org.eindopdracht.resource.dto.ContentDTO;
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
    public ResponseEntity<List<ContentDTO>> get() {
        return ResponseEntity.ok(contentService.get());
    }

    /**
     * Returns a single content.
     *
     * @param id id of the content to find
     * @return response entity with single content
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContentDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(contentService.getById(id));
    }

    /**
     * Post a single content.
     *
     * @param contentDto content to post
     * @return response entity with posted content
     */
    @PostMapping
    public ResponseEntity<ContentDTO> post(@RequestBody @Valid ContentDTO contentDto) {
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
    public ResponseEntity<ContentDTO> put(@PathVariable int id, @RequestBody @Valid ContentDTO contentDto) {
        return ResponseEntity.ok(contentService.put(id, contentDto));
    }
}
