package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.UserDTO;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin()
@RequestMapping("/signup")
public class SignupController{

    private final UserService service;

    public SignupController(UserService service) {
            this.service = service;
        }

    @PostMapping
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody User user)
    {
        return ResponseEntity.ok(service.create(user));
    }
}
