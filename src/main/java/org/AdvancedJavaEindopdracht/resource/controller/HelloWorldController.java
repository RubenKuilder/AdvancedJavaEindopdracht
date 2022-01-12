package org.AdvancedJavaEindopdracht.resource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/hello_world")
public class HelloWorldController {
    @GetMapping
    public String helloWorld() {
        return "Hello world!!";
    }
}
