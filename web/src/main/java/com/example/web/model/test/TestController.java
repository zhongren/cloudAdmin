package com.example.web.model.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    private final RestTemplate restTemplate;

    @Autowired
    public TestController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}



    @GetMapping(value = "test")
    public String test() {

        return restTemplate.getForObject("http://127.0.0.1:8080/test" , String.class);

    }



}
