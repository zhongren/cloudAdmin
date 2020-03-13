package com.example.module.user;

import com.example.common.config.fegin.FeginConfig;
import com.example.constant.ApiUrlConstant;
import com.example.constant.AppConstant;
import com.example.common.dto.ParamDto;
import com.example.common.dto.ResultDto;
import com.example.module.user.dto.UserPo;
import com.example.module.user.fallback.UserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = AppConstant.PROVIDER_NAME, configuration = FeginConfig.class,fallback = UserFallBack.class)
public interface UserFacade {

    @PostMapping(value = ApiUrlConstant.USER.USER_CREATE)
    ResultDto create(@RequestBody UserPo userDto);

    @PostMapping(value = ApiUrlConstant.USER.USER_PAGE)
    ResultDto page(@RequestBody ParamDto paramDto);
}
