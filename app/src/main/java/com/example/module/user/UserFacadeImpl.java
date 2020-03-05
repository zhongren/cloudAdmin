package com.example.module.user;

import com.example.constant.ApiUrlConstant;
import com.example.core.dto.ResultDto;
import com.example.core.util.BeanUtil;
import com.example.core.util.IdUtil;
import com.example.module.user.dto.UserDto;
import com.example.module.user.dto.UserPo;
import com.example.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFacadeImpl implements UserFacade {

    @Autowired
    UserService userService;

    @Override
    @PostMapping(value = ApiUrlConstant.USER.CREATE_USER)
    public ResultDto create(@RequestBody UserDto userDto) {
        UserPo userPo=new UserPo();
        BeanUtil.copyProperties(userDto,userPo);
        userPo.setId(IdUtil.nextId());
        userPo.initCreate();
        userService.create(userPo);
        return ResultDto.success();
    }
}
