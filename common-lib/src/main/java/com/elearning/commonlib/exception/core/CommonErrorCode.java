package com.elearning.commonlib.exception.core;

import org.springframework.http.HttpStatus;

public enum CommonErrorCode implements ErrorCode {
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-000", "Internal server error"),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "COMMON-001", "Validation failed"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON-002", "Invalid request"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON-003", "Access denied");

    private final HttpStatus status;
    private final String code;
    private final String message;

    CommonErrorCode(HttpStatus status, String code, String message) {
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
