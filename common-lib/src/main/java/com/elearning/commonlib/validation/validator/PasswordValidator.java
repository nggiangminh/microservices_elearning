package com.elearning.commonlib.validation.validator;

import com.elearning.commonlib.validation.annotation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private int minLength;
    private int maxLength;
    private boolean requireUpperCase;
    private boolean requireLowerCase;
    private boolean requireDigit;
    private boolean requireSpecialChar;

    @Override
    public void initialize(Password constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.requireUpperCase = constraintAnnotation.requireUpperCase();
        this.requireLowerCase = constraintAnnotation.requireLowerCase();
        this.requireDigit = constraintAnnotation.requireDigit();
        this.requireSpecialChar = constraintAnnotation.requireSpecialChar();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        context.disableDefaultConstraintViolation();

        if (password.length() < minLength || password.length() > maxLength) {
            context.buildConstraintViolationWithTemplate(
                String.format("Mật khẩu phải có độ dài từ %d đến %d ký tự", minLength, maxLength)
            ).addConstraintViolation();
            return false;
        }

        if (requireUpperCase && !Pattern.compile("[A-Z]").matcher(password).find()) {
            context.buildConstraintViolationWithTemplate(
                "Mật khẩu phải chứa ít nhất một chữ hoa"
            ).addConstraintViolation();
            return false;
        }

        if (requireLowerCase && !Pattern.compile("[a-z]").matcher(password).find()) {
            context.buildConstraintViolationWithTemplate(
                "Mật khẩu phải chứa ít nhất một chữ thường"
            ).addConstraintViolation();
            return false;
        }

        if (requireDigit && !Pattern.compile("\\d").matcher(password).find()) {
            context.buildConstraintViolationWithTemplate(
                "Mật khẩu phải chứa ít nhất một chữ số"
            ).addConstraintViolation();
            return false;
        }

        if (requireSpecialChar && !Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) {
            context.buildConstraintViolationWithTemplate(
                "Mật khẩu phải chứa ít nhất một ký tự đặc biệt"
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}
