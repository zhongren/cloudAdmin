package com.example.module.user;

import com.example.common.dto.ParamDto;
import com.example.constant.ApiUrlConstant;
import com.example.common.dto.ResultDto;
import com.example.core.dto.PageInfoDto;
import com.example.core.util.BeanUtil;
import com.example.core.util.IdUtil;
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
    @PostMapping(value = ApiUrlConstant.USER.USER_CREATE)
    public ResultDto create(@RequestBody UserPo userDto) {
        UserPo userPo=new UserPo();
        BeanUtil.copyProperties(userDto,userPo);
        userPo.setId(IdUtil.nextId());
        userPo.initCreate();
        userService.create(userPo);
        return ResultDto.success();
    }

    @Override
    @PostMapping(value = ApiUrlConstant.USER.USER_PAGE)
    public ResultDto page(@RequestBody ParamDto paramDto) {
        PageInfoDto<UserPo> pageInfoDto= userService.page(paramDto, UserPo.class);

        return ResultDto.success(pageInfoDto);
    }
}
