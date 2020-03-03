package com.example.core.exception;


import com.example.core.exception.enums.AuthEnum;

/**
 * Created by zhongr on 2017/8/25.
 */
public class AuthException extends BaseException {

    public AuthException(AuthEnum authEnum) {
        super(authEnum.getMsg(), authEnum.getCode());
    }
}
