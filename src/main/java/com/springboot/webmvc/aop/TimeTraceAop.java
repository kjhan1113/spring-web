package com.springboot.webmvc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Lazy;

@Aspect
public class TimeTraceAop {

    @Around("execution(* com.springboot.webmvc.controller..*(..))")
    public Object execute(ProceedingJoinPoint jointPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + jointPoint.toString());
        try {
            return jointPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + jointPoint.toString() + " " + timeMs + "ms");
        }
    }
}
