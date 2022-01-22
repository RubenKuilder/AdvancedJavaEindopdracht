package org.eindopdracht.resource.controller;

import org.eindopdracht.resource.dto.ContentTypeDTO;
import org.eindopdracht.resource.service.ContentTypeService;
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
    public ResponseEntity<List<ContentTypeDTO>> get() {
        return ResponseEntity.ok(contentTypeService.get());
    }

    /**
     * Returns a single content type.
     *
     * @param id    id of the content type to find
     * @return      response entity with single content type
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContentTypeDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(contentTypeService.getById(id));
    }

    /**
     * Post a single content type.
     *
     * @param contentTypeDto    content type to post
     * @return                  response entity with posted content type
     */
    @PostMapping
    public ResponseEntity<ContentTypeDTO> post(@RequestBody @Valid ContentTypeDTO contentTypeDto) {
        return ResponseEntity.ok(contentTypeService.persist(contentTypeDto));
    }

    /**
     * Put a single content type.
     *
     * @param id                id of the content type to put
     * @param contentTypeDto    content type to put
     * @return                  response entity with put content type
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContentTypeDTO> put(@PathVariable long id, @RequestBody @Valid ContentTypeDTO contentTypeDto) {
        return ResponseEntity.ok(contentTypeService.put(id, contentTypeDto));
    }

    /**
     * Patch a single content type.
     *
     * @param id                id of the content type to patch
     * @param contentTypeDto    content type to patch
     * @return                  response entity with patched content type
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ContentTypeDTO> patch(@PathVariable int id, @RequestBody ContentTypeDTO contentTypeDto) {
        return ResponseEntity.ok(contentTypeService.patch(id, contentTypeDto));
    }

    /**
     * Delete a single content type.
     *
     * @param id    id of the content type to delete
     * @return      response entity with deleted content type
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ContentTypeDTO> delete(@PathVariable int id) {
        return new ResponseEntity<>(contentTypeService.delete(id), HttpStatus.OK);
    }
}
