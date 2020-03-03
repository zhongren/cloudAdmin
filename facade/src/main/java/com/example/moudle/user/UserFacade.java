package com.example.moudle.user;

import com.example.config.ApiUrlConstant;
import com.example.config.AppConstant;
import com.example.moudle.user.dto.UserDto;
import com.example.moudle.user.fallback.UserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = AppConstant.PROVIDER_NAME,fallback = UserFallBack.class)
public interface UserFacade {

    @PostMapping(value = ApiUrlConstant.USER.CREATE_USER)
    public int create(UserDto userDto);
}
