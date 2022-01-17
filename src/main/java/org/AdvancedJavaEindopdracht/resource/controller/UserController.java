package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.resource.dto.UserDTO;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Returns a list of all users.
     *
     * @return      response entity with list of all users
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok(service.getUsers());
    }

    /**
     * Returns a single user.
     *
     * @return      response entity with single user
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") final Integer id){
        return ResponseEntity.ok(service.getUser(id));
    }

    /**
     * Post a single user.
     *
     * @return      response entity with posted user
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO> postUser(@Valid @RequestBody User user){
        return ResponseEntity.ok(service.create(user));
    }

    /**
     * Put a single user.
     *
     * @return      response entity with put user
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable("id") final Integer id, @Valid @RequestBody User user){
        service.update(user, id);
        return ResponseEntity.ok(service.update(user, id));
    }

    /**
     * Delete a single user.
     *
     * @return      response entity with deleted user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Integer id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}