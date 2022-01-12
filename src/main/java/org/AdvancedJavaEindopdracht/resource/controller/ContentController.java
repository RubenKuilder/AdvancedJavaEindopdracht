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

    @GetMapping
    public ResponseEntity<List<ContentDto>> get() {
        return ResponseEntity.ok(contentService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(contentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ContentDto> post(@RequestBody @Valid ContentDto contentDto) {
        return ResponseEntity.ok(contentService.persist(contentDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentDto> put(@PathVariable long id, @RequestBody @Valid ContentDto contentDto) {
        return ResponseEntity.ok(contentService.put(id, contentDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContentDto> patch(@PathVariable int id, @RequestBody ContentDto contentDto) {
        return ResponseEntity.ok(contentService.patch(id, contentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        contentService.delete(id);
        return ResponseEntity.ok(String.format("Content with ID %d successfully deleted.", id));
    }
}
