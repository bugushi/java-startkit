package com.example.demo.api.exception;

import com.example.demo.api.enums.StatusEnum;
import lombok.Data;

@Data
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -1240188816582846748L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;


    public ApiException(StatusEnum statusEnum) {
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
    }

    /**
     * controller错误信息 + service错误信息
     * 如：注册失败 用户已存在
     * @param statusEnum
     * @param serviceErrorMsg
     */
    public ApiException(StatusEnum statusEnum, String serviceErrorMsg) {
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage() + ' ' + serviceErrorMsg;
    }
}
