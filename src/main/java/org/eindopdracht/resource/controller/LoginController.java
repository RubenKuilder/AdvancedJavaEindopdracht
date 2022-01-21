package org.eindopdracht.resource.controller;

import org.eindopdracht.resource.dto.LoginDTO;
import org.eindopdracht.security.JWTProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/authenticate")
public class LoginController
{

    private final AuthenticationManager authenticationManager;

    private final JWTProvider jwtProvider;

    public LoginController(AuthenticationManager authenticationManager, JWTProvider jwtProvider)
    {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    /**
     * Post a single login.
     *
     * @param login     login to post
     * @return          response entity with token
     */
    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO login)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword()));

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        String token = jwtProvider.createToken(username);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
