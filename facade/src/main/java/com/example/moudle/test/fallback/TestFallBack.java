package com.example.moudle.test.fallback;

import com.example.moudle.test.TestFacade;
import org.springframework.stereotype.Component;

@Component
public class TestFallBack implements TestFacade {
    @Override
    public String test() {
        return "test fallback";
    }
}
