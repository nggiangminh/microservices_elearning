package com.elearning.commonlib.validation.validator;

import com.elearning.commonlib.validation.annotation.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(?:\\+?84|0)[35789]\\d{8}$");

    @Override
    public void initialize(Phone constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null) {
            return false;
        }

        context.disableDefaultConstraintViolation();

        if (!PHONE_PATTERN.matcher(phone).matches()) {
            context.buildConstraintViolationWithTemplate(
                "Số điện thoại không đúng định dạng (VD: 0912345678 hoặc +84912345678)"
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}
