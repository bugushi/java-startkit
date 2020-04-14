package com.example.demo.api.vo.result;

import com.example.demo.api.enums.StatusEnum;
import com.example.demo.api.exception.ApiException;
import lombok.Data;

@Data
public class Result<T> {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 业务状态码
     */
    private String code;

    /**
     * 消息
     */
    private String message;

    /**
     * 结果数据
     */
    private T data;

    public Result(T data) {
        success = true;
        code = StatusEnum.SUCCESS.getCode();
        message = StatusEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public Result(ApiException e) {
        success = false;
        code = e.getCode();
        message = e.getMessage();
    }

    public Result(StatusEnum statusEnum) {
        code = statusEnum.getCode();
        message = statusEnum.getMessage();

        if(statusEnum.getCode() == "2000") {
            success = true;
        } else {
            success = false;
        }
    }

    public Result(StatusEnum statusEnum, T data) {
        this.data = data;
        code = statusEnum.getCode();
        message = statusEnum.getMessage();

        if(statusEnum.getCode() == "2000") {
            success = true;
        } else {
            success = false;
        }
    }
}
