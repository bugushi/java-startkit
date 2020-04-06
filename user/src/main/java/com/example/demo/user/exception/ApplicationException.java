package com.example.demo.user.exception;

import com.example.demo.user.enums.ApplicationStatus;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -1240188816582846748L;
    /**
     * 每一个抛出的异常都必须对应一个 ApplicationEnum
     */
    private ApplicationStatus applicationStatus;

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public ApplicationException(ApplicationStatus applicationStatus) {
        super(applicationStatus.getMessage());
        this.applicationStatus = applicationStatus;
    }

}
