package edu.miu.cs.cs545.propertymanagementsystem.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface ExecutionTime {
    //Transaction Id, Date, Time, Principle, Operation


}
