package com.example.module.user.dto;

import com.example.common.dto.BasePo;
import lombok.Data;


@Data
public class UserPo extends BasePo {
    private String username;
    private String realName;
    private String passwd;
    private String status;

}
