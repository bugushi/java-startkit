package com.example.demo.user.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    USER_ALREADY_EXIST("100000", "用户已存在");

    /**
     * 设计原则
     * service错误码6位数
     * User模块1开头
     */
    private String code;

    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
