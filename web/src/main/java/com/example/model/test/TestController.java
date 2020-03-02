package com.example.model.test;

import com.example.facade.TestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private TestFacade testFacade;


    @GetMapping(value = "test")
    public String test() {
        return testFacade.test();

    }



}
