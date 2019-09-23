package com.llsydn.app;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspectj {

	@Pointcut("execution(* com.llsydn.dao.*.*(..))")
	public void pointCut() {

	}

	@Before("pointCut()")
	public void before() {
		System.out.println("spring aop before");
	}
}
