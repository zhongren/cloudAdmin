package com.example.moudle.user.dto;

import com.example.core.base.BaseDto;
import lombok.Data;
import lombok.ToString;

@Data
public class UserPo extends BaseDto {
    private String username;
    private String realName;
    private String passwd;
    private String status;

}