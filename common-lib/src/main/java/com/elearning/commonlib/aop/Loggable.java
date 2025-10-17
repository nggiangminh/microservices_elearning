package com.elearning.commonlib.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Loggable {
    boolean logArgs() default true;

    boolean logReturn() default true;

    boolean logExecutionTime() default true;

    String value() default "";
}
