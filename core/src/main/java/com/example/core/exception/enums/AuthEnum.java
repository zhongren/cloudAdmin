package com.example.core.exception.enums;


import com.example.common.dto.ResultDto;

/**
 * Created by zhongr on 2017/8/25.
 */
public enum AuthEnum {
    UNKNOWN_ACCOUNT(ResultDto.FAIL, "账号不存在"), DISABLED_ACCOUNT(ResultDto.FAIL, "账号被禁用"),
    WRONG_PASSWORD(ResultDto.FAIL, "用户名或密码错误"), UNAUTHORIZED(ResultDto.NO_AUTH, "权限不足,请重新登陆"), UNLOGIN(ResultDto.NO_AUTH, "未登录,请先登陆");
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    AuthEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
