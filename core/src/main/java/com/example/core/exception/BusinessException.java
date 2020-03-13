package com.example.core.exception;


import com.example.common.dto.ResultDto;

/**
 * Created by zhongr on 2017/8/25.
 */
public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(message, ResultDto.FAIL);
    }
}
