package com.example.facade.fallback;

import com.example.facade.TestFacade;
import org.springframework.stereotype.Component;

@Component
public class TestFallBack implements TestFacade {
    @Override
    public String test() {
        return "test fallback";
    }
}
