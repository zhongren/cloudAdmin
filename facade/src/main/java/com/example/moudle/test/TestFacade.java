package com.example.moudle.test;

import com.example.config.AppConstant;
import com.example.moudle.test.fallback.TestFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = AppConstant.PROVIDER_NAME,fallback = TestFallBack.class)
public interface TestFacade {

    @GetMapping(value = "test")
    public String test();
}
