package com.example.core.exception.enums;


import com.example.core.dto.ResultDto;

/**
 * Created by zhongr on 2017/8/25.
 */
public enum BaseEnum {
    SERVICE_ERROR(ResultDto.FAIL, "系统异常");
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

    BaseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
