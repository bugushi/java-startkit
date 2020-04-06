package com.example.demo.user.vo.result;

import com.example.demo.user.enums.ApplicationStatus;
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
        code = ApplicationStatus.SUCCESS.getCode();
        message = ApplicationStatus.SUCCESS.getMessage();
        this.data = data;
    }

    public Result(ApplicationStatus applicationStatus) {
        code = applicationStatus.getCode();
        message = applicationStatus.getMessage();

        if(applicationStatus.getCode() == "2000") {
            success = true;
        } else {
            success = false;
        }
    }

    public Result(ApplicationStatus applicationStatus, T data) {
        this.data = data;
        code = applicationStatus.getCode();
        message = applicationStatus.getMessage();

        if(applicationStatus.getCode() == "2000") {
            success = true;
        } else {
            success = false;
        }
    }
}
