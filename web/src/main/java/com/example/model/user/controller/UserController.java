package com.example.model.user.controller;


import cn.hutool.core.bean.BeanUtil;
import com.example.common.dto.ParamDto;
import com.example.constant.ApiUrlConstant;
import com.example.common.dto.ResultDto;
import com.example.model.BaseController;
import com.example.model.user.req.UserCreateReq;
import com.example.module.user.UserFacade;
import com.example.module.user.dto.UserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@SpringQueryMap
@Api(tags = "用户管理")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserFacade userFacade;

    @ApiOperation(value = "新增用户")
    @PostMapping(value = ApiUrlConstant.USER.USER_CREATE)
    public ResultDto userCreate(@RequestBody UserCreateReq userCreateReq) {
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(userCreateReq, userDto);
        ResultDto resultDto=userFacade.create(userDto);
        return resultDto;
    }

    @ApiOperation(value = "查询用户")
    @GetMapping(value = ApiUrlConstant.USER.USER_PAGE)
    public ResultDto userPage() {
        ParamDto paramDto= getParam();
        ResultDto resultDto=userFacade.page(paramDto);
        return resultDto;
    }

}
