package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.RoleDTO;
import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.AdvancedJavaEindopdracht.resource.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    /**
     * Returns a list of all roles.
     *
     * @return      response entity with list of all roles
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<RoleDTO>> getUsers(){
        return ResponseEntity.ok(service.getRoles());
    }

    /**
     * Returns a single role.
     *
     * @return      response entity with single role
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RoleDTO> getRole(@PathVariable("id") final Integer id){
        return ResponseEntity.ok(service.getRole(id));
    }

    /**
     * Post a single role.
     *
     * @return      response entity with posted role
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RoleDTO> postRole(@Valid @RequestBody Role role){
        return ResponseEntity.ok(service.create(role));
    }

    /**
     * Put a single role.
     *
     * @return      response entity with put role
     */
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> putRole(@PathVariable("id") final Integer id, @Valid @RequestBody Role role){
        return ResponseEntity.ok(service.update(role, id));
    }

    /**
     * Delete a single role.
     *
     * @return      response entity with deleted role
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<RoleDTO> deleteRole(@PathVariable("id") final Integer id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}
