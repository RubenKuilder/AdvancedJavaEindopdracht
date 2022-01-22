package org.eindopdracht.resource.controller;

import org.eindopdracht.resource.dto.UserDTO;
import org.eindopdracht.resource.model.User;
import org.eindopdracht.resource.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin()
@RequestMapping("/signup")
public class SignupController {

    private final UserService service;

    public SignupController(UserService service) {
        this.service = service;
    }

    /**
     * Post a single user.
     *
     * @param userDTO  user to post
     * @return      response entity with user
     */
    @PostMapping
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok(service.create(userDTO));
    }
}
