package com.example.module.user;

import com.example.constant.ApiUrlConstant;
import com.example.constant.AppConstant;
import com.example.core.dto.ResultDto;
import com.example.module.user.dto.UserDto;
import com.example.module.user.fallback.UserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = AppConstant.PROVIDER_NAME,fallback = UserFallBack.class)
public interface UserFacade {

    @PostMapping(value = ApiUrlConstant.USER.CREATE_USER)
    public ResultDto create(UserDto userDto);
}
