package com.elearning.commonlib.validation.annotation;

import com.elearning.commonlib.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Mật khẩu không hợp lệ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int minLength() default 8;
    int maxLength() default 32;
    boolean requireUpperCase() default true;
    boolean requireLowerCase() default true;
    boolean requireDigit() default true;
    boolean requireSpecialChar() default true;
}
