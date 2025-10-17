package com.elearning.commonlib.exception.core;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final transient ErrorCode errorCode;

    protected BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    protected BaseException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    protected BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getCode() {
        return errorCode.getCode();
    }

    public int getHttpStatus() {
        return errorCode.getStatus().value();
    }

    public String getErrorMessage() {
        return errorCode.getMessage();
    }
}
