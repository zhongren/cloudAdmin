package com.example.app.facade;

import com.example.facade.TestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestFacadeImpl implements TestFacade {


    @GetMapping(value = "test")
    public String test() {
        return "Hello Nacos Discovery ";
    }

}
