package edu.miu.cs.cs545.propertymanagementsystem.aop;


import edu.miu.cs.cs545.propertymanagementsystem.model.ExceptionTable;
import edu.miu.cs.cs545.propertymanagementsystem.repository.ExceptionRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class ExceptionAspect {
    private final ExceptionRepository exceptionRepository;

    public ExceptionAspect(ExceptionRepository exceptionRepository) {
        this.exceptionRepository = exceptionRepository;
    }

    @AfterThrowing(pointcut = "execution(* edu.miu.cs.cs545.propertymanagementsystem.controller.AuthController.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        String principle = "FakeUser"; // Set the fake user for now
        String operation = joinPoint.getSignature().getName();
        ExceptionTable exceptionLog = new ExceptionTable();
        exceptionLog.setDate(LocalDate.now());
        exceptionLog.setTime(LocalTime.now());
        exceptionLog.setPrinciple(principle);
        exceptionLog.setOperation(operation);
        exceptionLog.setExceptionType(exception.getClass().getSimpleName());
        exceptionRepository.save(exceptionLog);
        // Additional handling or logging for the exception
    }
}
