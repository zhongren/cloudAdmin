package com.example.moudle.user;

import cn.hutool.core.lang.Snowflake;
import com.example.moudle.test.TestFacade;
import com.example.moudle.user.dto.UserDto;
import com.example.moudle.user.dto.UserPo;
import com.example.moudle.user.repo.UserRepo;
import com.example.moudle.user.service.UserService;
import com.example.util.BeanUtil;
import com.example.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFacadeImpl implements UserFacade {

    @Autowired
    UserService userService;

    @Override
    public int create(UserDto userDto) {
        UserPo userPo=new UserPo();
        BeanUtil.copyProperties(userDto,userPo);
        userPo.setId(IdUtil.nextId());
        userPo.initCreate();
        return userService.create(userPo);
    }
}
