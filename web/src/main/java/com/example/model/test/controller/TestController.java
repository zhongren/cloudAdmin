package com.example.model.test.controller;


import com.example.core.base.BaseController;
import com.example.moudle.test.TestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController {

    @Autowired
    private TestFacade testFacade;


    @GetMapping(value = "test")
    public String test() {
        return testFacade.test();

    }



}