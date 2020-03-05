package com.example.module.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestFacadeImpl implements TestFacade {


    @GetMapping(value = "test")
    public String test() {
        return "Hello Nacos Discovery ";
    }

}
