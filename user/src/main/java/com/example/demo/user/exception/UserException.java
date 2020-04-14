package com.example.demo.user.exception;

import com.example.demo.user.enums.ErrorCode;

public class UserException extends RuntimeException {
    private static final long serialVersionUID = 1790679395537685742L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;


    public UserException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
