package com.example.facade;

import com.example.facade.fallback.TestFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "app",fallback = TestFallBack.class)
public interface TestFacade {


    @GetMapping(value = "test")
    public String test();
}
