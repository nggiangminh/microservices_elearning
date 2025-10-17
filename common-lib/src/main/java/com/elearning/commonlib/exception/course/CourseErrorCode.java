package com.elearning.commonlib.exception.course;

import com.elearning.commonlib.exception.core.ErrorCode;
import org.springframework.http.HttpStatus;

public enum CourseErrorCode implements ErrorCode {
    COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "COURSE-001", "Course not found"),
    INVALID_COURSE_DATA(HttpStatus.BAD_REQUEST, "COURSE-002", "Invalid course data"),
    COURSE_ALREADY_EXISTS(HttpStatus.CONFLICT, "COURSE-003", "Course already exists");

    private final HttpStatus status;
    private final String code;
    private final String message;

    CourseErrorCode(HttpStatus status, String code, String message) {
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
