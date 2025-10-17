package com.elearning.commonlib.exception.core;

public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(CommonErrorCode.BAD_REQUEST, message);
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(String message, Throwable cause) {
        super(CommonErrorCode.BAD_REQUEST, message, cause);
    }

    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
