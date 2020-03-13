package com.example.module.user.dto;

import com.example.common.dto.BaseDto;
import com.example.common.dto.BasePo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto extends BaseDto {
    private Long id;
    @JsonIgnore
    private Long createTime;
    @JsonIgnore
    private Long updateTime;
    private String username;
    private String realName;
    private String passwd;
    private String status;

}
