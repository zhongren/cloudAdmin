package com.example.moudle.user.fallback;

import com.example.moudle.user.UserFacade;
import com.example.moudle.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserFacade {

    @Override
    public int create(UserDto userDto) {
        return -1;
    }
}
