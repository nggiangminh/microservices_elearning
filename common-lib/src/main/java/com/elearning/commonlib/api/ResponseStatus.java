package com.elearning.commonlib.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {
    private String code;
    private String message;
    private String displayMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime responseTime;

    public static ResponseStatus success() {
        return ResponseStatus.builder()
                .code("200")
                .message("Operation completed successfully")
                .displayMessage("Operation completed successfully")
                .responseTime(LocalDateTime.now())
                .build();
    }

    public static ResponseStatus error(String code, String message, String displayMessage) {
        return ResponseStatus.builder()
                .code(code)
                .message(message)
                .displayMessage(displayMessage)
                .responseTime(LocalDateTime.now())
                .build();
    }
}
