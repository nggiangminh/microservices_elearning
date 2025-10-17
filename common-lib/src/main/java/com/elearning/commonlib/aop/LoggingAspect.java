package com.elearning.commonlib.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // --- Pointcut: tất cả controller
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointcut() {}

    // --- Pointcut: các method annotate @Loggable
    @Pointcut("@annotation(com.elearning.commonlib.aop.Loggable)")
    public void loggableAnnotatedMethods() {}

    // --- Around: controller tự động log request/response
    @Around("controllerPointcut()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String traceId = UUID.randomUUID().toString();
        String userId = request.getHeader("X-User-Id"); // tuỳ chỉnh theo hệ thống

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        log.info("[TRACEID={}][USER={}] >>> [Request] {}.{} args={}", traceId, userId, className, methodName, args);

        long start = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            log.info("[TRACEID={}][USER={}] <<< [Response] {}.{} returned={} ({} ms)",
                    traceId, userId, className, methodName, result, duration);
        } catch (Exception ex) {
            long duration = System.currentTimeMillis() - start;
            log.error("[TRACEID={}][USER={}] !!! [Exception] {}.{} after {} ms: {}",
                    traceId, userId, className, methodName, duration, ex.getMessage(), ex);
            throw ex;
        }
        return result;
    }

    // --- Around: log các method service annotate @Loggable
    @Around("loggableAnnotatedMethods() && @annotation(loggable)")
    public Object logAnnotatedMethod(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String prefix = loggable.value().isEmpty() ? "" : loggable.value() + " ";

        long startTime = System.currentTimeMillis();

        if (loggable.logArgs()) {
            log.info("{} → {}.{}() started with args={}", prefix, className, methodName, Arrays.toString(joinPoint.getArgs()));
        }

        try {
            Object result = joinPoint.proceed();

            if (loggable.logReturn()) {
                log.info("{} ← {}.{}() returned={}", prefix, className, methodName, result);
            }
            if (loggable.logExecutionTime()) {
                long duration = System.currentTimeMillis() - startTime;
                log.info("{} *** {}.{}() executed in {} ms", prefix, className, methodName, duration);
            }
            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            log.error("{} ✗ {}.{}() failed after {}ms: {}", prefix, className, methodName, duration, e.getMessage(), e);
            throw e;
        }
    }
}
