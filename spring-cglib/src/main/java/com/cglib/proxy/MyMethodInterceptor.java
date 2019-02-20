package com.cglib.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/19 10:41
 */
public class MyMethodInterceptor implements MethodInterceptor {

	/**
	 * spring的过滤器有三个：BeanMethodInterceptor(), BeanFactoryAwareMethodInterceptor(), NoOp.INSTANCE
	 */

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("intercept cglib ...");
		// 执行父类方法
		return methodProxy.invokeSuper(o, objects);
		// return null; //（也可以不执行）
	}
}
