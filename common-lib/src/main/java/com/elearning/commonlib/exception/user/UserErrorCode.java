package com.elearning.commonlib.exception.user;

import com.elearning.commonlib.exception.core.CommonErrorCode;
import com.elearning.commonlib.exception.core.ErrorCode;
import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(CommonErrorCode.BAD_REQUEST.getStatus(), "USER-001", "User not found"),
    EMAIL_ALREADY_EXISTS(CommonErrorCode.BAD_REQUEST.getStatus(), "USER-002", "Email already exists");

    private final HttpStatus status;
    private final String code;
    private final String message;

    UserErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
