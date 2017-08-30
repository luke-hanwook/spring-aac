package com.dasol.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceCheckAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(PerformanceCheckAdvice.class);
	
	@Around("execution(* com.dasol.service.CampingApiService.insertAPI(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("==================================");
		long startTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : " + ((endTime - startTime) / 1000.0) + " sec");
		logger.info("==================================");
		return result;
	}
}
