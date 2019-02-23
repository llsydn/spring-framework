package com.cglib.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/23 8:46
 */
@Component
@Aspect //切面：@Aspect
public class MyAspect {

	// 切点：@Pointcut
	// 连接点：execution(* com.cglib.dao.*.*(..))
	@Pointcut("execution(* com.cglib.dao.*.*(..))")
	private void pointCut() {
	}

	// 通知：@Before
	@Before("pointCut()")
	public void doAccessCheck() {
		System.out.println("before ...");
	}
}
