package com.example.model.user.controller;


import com.example.config.ApiUrlConstant;
import com.example.core.base.BaseController;
import com.example.core.dto.ResultDto;
import com.example.model.test.req.UserCreateReq;
import com.example.moudle.test.TestFacade;
import com.example.moudle.user.UserFacade;
import com.example.moudle.user.dto.UserDto;
import com.example.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    @Autowired
    private UserFacade userFacade;


    @PostMapping(value = ApiUrlConstant.USER.CREATE_USER)
    public ResultDto createUser(@RequestBody UserCreateReq userCreateReq) {
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(userCreateReq, userDto);
        int i = userFacade.create(userDto);
        return ResultDto.success();

    }


}
