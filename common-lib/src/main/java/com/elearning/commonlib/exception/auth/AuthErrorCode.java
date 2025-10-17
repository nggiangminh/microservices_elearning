package com.elearning.commonlib.exception.auth;

import com.elearning.commonlib.exception.core.ErrorCode;
import org.springframework.http.HttpStatus;

public enum AuthErrorCode implements ErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH-001", "Unauthorized access"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "AUTH-002", "Invalid username or password"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH-003", "Expired authentication token");

    private final HttpStatus status;
    private final String code;
    private final String message;

    AuthErrorCode(HttpStatus status, String code, String message) {
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
