package edu.miu.cs.cs545.propertymanagementsystem.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

@Pointcut("@annotation(edu.miu.cs.cs545.propertymanagementsystem.aop.ExecutionTime)")
    public void LogMeAnnotation(){

}
    @Around("LogMeAnnotation()")
    public Object calculateExecutionTime(ProceedingJoinPoint call) throws Throwable{
     long start= System.nanoTime();
     var action=call.proceed();
     long end=System.nanoTime();
     long totalTime=end-start;
        System.out.println(call.getSignature().getName()+ " takes : "+totalTime +"  nanoseconds ");
     return action;
    }

}
