package com.elearning.commonlib.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private ResponseStatus status;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return  ApiResponse.<T>builder()
                .status(ResponseStatus.success())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(String code, String message, String displayMessage) {
        return ApiResponse.<T>builder()
                .status(ResponseStatus.error(code, message, displayMessage))
                .build();
    }
}
