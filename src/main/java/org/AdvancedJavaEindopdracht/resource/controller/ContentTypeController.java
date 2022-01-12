package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentTypeDto;
import org.AdvancedJavaEindopdracht.resource.service.ContentTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/contenttype")
public class ContentTypeController {

    private final ContentTypeService contentTypeService;

    public ContentTypeController(ContentTypeService contentTypeService) {
        this.contentTypeService = contentTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ContentTypeDto>> get() {
        return ResponseEntity.ok(contentTypeService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentTypeDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(contentTypeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ContentTypeDto> post(@RequestBody @Valid ContentTypeDto contentTypeDto) {
        return ResponseEntity.ok(contentTypeService.persist(contentTypeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentTypeDto> put(@PathVariable long id, @RequestBody @Valid ContentTypeDto contentTypeDto) {
        return ResponseEntity.ok(contentTypeService.put(id, contentTypeDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContentTypeDto> patch(@PathVariable int id, @RequestBody ContentTypeDto contentTypeDto) {
        return ResponseEntity.ok(contentTypeService.patch(id, contentTypeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        contentTypeService.delete(id);
        return ResponseEntity.ok(String.format("Contenttype with ID %d successfully deleted.", id));
    }
}
