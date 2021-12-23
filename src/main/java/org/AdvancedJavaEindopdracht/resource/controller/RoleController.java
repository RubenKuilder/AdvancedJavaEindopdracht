package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.RoleDTO;
import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.AdvancedJavaEindopdracht.resource.service.RoleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<RoleDTO>> getUsers(){
        return ResponseEntity.ok(service.getRoles());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RoleDTO> getRole(@PathVariable("id") final Integer id){
        return ResponseEntity.ok(service.getRole(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RoleDTO> postRole(@Valid @RequestBody Role role){
        return ResponseEntity.ok(service.create(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> putRole(@PathVariable("id") final Integer id, @Valid @RequestBody Role role){
        return ResponseEntity.ok(service.update(role, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") final Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
