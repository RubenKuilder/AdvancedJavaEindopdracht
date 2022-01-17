package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentTypeDto;
import org.AdvancedJavaEindopdracht.resource.service.ContentTypeService;
import org.springframework.http.HttpStatus;
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

    /**
     * Returns a list of all content types.
     *
     * @return      response entity with list of all content types
     */
    @GetMapping
    public ResponseEntity<List<ContentTypeDto>> get() {
        return ResponseEntity.ok(contentTypeService.get());
    }

    /**
     * Returns a single content type.
     *
     * @return      response entity with single content type
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContentTypeDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(contentTypeService.getById(id));
    }

    /**
     * Post a single content type.
     *
     * @return      response entity with posted content type
     */
    @PostMapping
    public ResponseEntity<ContentTypeDto> post(@RequestBody @Valid ContentTypeDto contentTypeDto) {
        return ResponseEntity.ok(contentTypeService.persist(contentTypeDto));
    }

    /**
     * Put a single content type.
     *
     * @return      response entity with put content type
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContentTypeDto> put(@PathVariable long id, @RequestBody @Valid ContentTypeDto contentTypeDto) {
        return ResponseEntity.ok(contentTypeService.put(id, contentTypeDto));
    }

    /**
     * Patch a single content type.
     *
     * @return      response entity with patched content type
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ContentTypeDto> patch(@PathVariable int id, @RequestBody ContentTypeDto contentTypeDto) {
        return ResponseEntity.ok(contentTypeService.patch(id, contentTypeDto));
    }

    /**
     * Delete a single content type.
     *
     * @return      response entity with deleted content type
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ContentTypeDto> delete(@PathVariable int id) {
        return new ResponseEntity<>(contentTypeService.delete(id), HttpStatus.OK);
    }
}
