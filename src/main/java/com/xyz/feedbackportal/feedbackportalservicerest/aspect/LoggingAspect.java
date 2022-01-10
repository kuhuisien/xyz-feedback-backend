package com.xyz.feedbackportal.feedbackportalservicerest.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Pointcut("execution(* com.xyz.feedbackportal.feedbackportalservicerest.rest.*.*(..))")
    private void forControllerPackage() {}
    
    @Pointcut("execution(* com.xyz.feedbackportal.feedbackportalservicerest.service.*.*(..))")
    private void forServicePackage() {}
    
    @Pointcut("execution(* com.xyz.feedbackportal.feedbackportalservicerest.dao.*.*(..))")
    private void forDaoPackage() {}
    
    /**
     * Pointcut that matches all Web REST endpoints, services and DAO.
     */
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "forAppFlow()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    	logger.error("Exception in {}.{}() with message = {}", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), e.getMessage() != null ? e.getMessage() : "NULL");
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("forAppFlow()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	logger.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    	
        try {
            Object result = joinPoint.proceed();
            logger.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result != null ? result.toString() : "NULL");
            return result;
        } catch (IllegalArgumentException e) {
        	logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}
