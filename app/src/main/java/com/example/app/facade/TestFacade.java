package com.example.app.facade;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestFacade {

    @GetMapping(value = "test")
    public String test() {
        return "Hello Nacos Discovery ";
    }

}
