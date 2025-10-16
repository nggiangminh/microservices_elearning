package com.elearning.commonlib.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private String details;

    public static ErrorResponse of(String code, String message) {
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .build();
    }

    public static ErrorResponse of(String code, String message, String details) {
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .details(details)
                .build();
    }
}
