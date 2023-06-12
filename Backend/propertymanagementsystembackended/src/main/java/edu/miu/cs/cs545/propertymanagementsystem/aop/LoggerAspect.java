package edu.miu.cs.cs545.propertymanagementsystem.aop;


import edu.miu.cs.cs545.propertymanagementsystem.model.Logger;
import edu.miu.cs.cs545.propertymanagementsystem.repository.LoggerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class LoggerAspect {

    private final LoggerRepository loggerRepository;

    @Autowired
    public LoggerAspect(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

//    @Before("execution(* edu.miu.cs.cs545.propertymanagementsystem.controller.AuthController.*(..))")
//    public void logBefore(JoinPoint joinPoint) {
//        String principle = "FakeUser"; // Set the fake user for now
//        String operation = joinPoint.getSignature().getName();
//        Logger logger = new Logger();
//        logger.setDate(LocalDate.now());
//        logger.setTime(LocalTime.now());
//        logger.setPrinciple(principle);
//        logger.setOperation(operation);
//        loggerRepository.save(logger);
//    }
}
