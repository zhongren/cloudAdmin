package com.example.module.user.fallback;

import com.example.common.dto.ParamDto;
import com.example.common.dto.ResultDto;
import com.example.module.user.UserFacade;
import com.example.module.user.dto.UserPo;
import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserFacade {

    @Override
    public ResultDto create(UserPo userDto) {
        return ResultDto.error();
    }

    @Override
    public ResultDto page(ParamDto paramDto) {
        return ResultDto.error();
    }
}
