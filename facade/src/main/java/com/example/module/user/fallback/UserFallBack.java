package com.example.module.user.fallback;

import com.example.core.dto.ResultDto;
import com.example.module.user.UserFacade;
import com.example.module.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserFacade {

    @Override
    public ResultDto create(UserDto userDto) {
        return ResultDto.fail();
    }
}
