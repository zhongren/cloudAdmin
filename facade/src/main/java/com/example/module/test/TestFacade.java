package com.example.module.test;

import com.example.constant.AppConstant;
import com.example.module.test.fallback.TestFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = AppConstant.PROVIDER_NAME,fallback = TestFallBack.class)
public interface TestFacade {

    @GetMapping(value = "test")
    public String test();
}
