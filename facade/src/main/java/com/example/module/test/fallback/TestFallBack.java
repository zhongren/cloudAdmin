package com.example.module.test.fallback;

import com.example.module.test.TestFacade;
import org.springframework.stereotype.Component;

@Component
public class TestFallBack implements TestFacade {
    @Override
    public String test() {
        return "test fallback";
    }
}
