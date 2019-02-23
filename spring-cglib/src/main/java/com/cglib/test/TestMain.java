package com.cglib.test;

import com.cglib.config.AppConfig;
import com.cglib.dao.IndexDao;
import com.cglib.proxy.MyMethodInterceptor;
import com.cglib.service.IndexService;
import com.cglib.service.UserService;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/19 10:03
 */
public class TestMain {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AppConfig.class);
		applicationContext.refresh();

		IndexService indexService = applicationContext.getBean(IndexService.class);
		System.out.println(indexService);

		// AppConfig类，没有加@Configuration注解，就是一个普通类
		// 加了@Configuration注解之后，就变成了一个代理类：CGLIB代理类
		AppConfig appConfig = applicationContext.getBean(AppConfig.class);
		System.out.println(appConfig);

		// 对UserService进行cglib代理
		Enhancer enhancer = new Enhancer();
		// 增强父类，cglib是基于继承类的
		enhancer.setSuperclass(UserService.class);
		enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
		// 过滤方法，不能每次都去new
		enhancer.setCallback(new MyMethodInterceptor());
		UserService userService = (UserService) enhancer.create();
		userService.query();

		// @Aspect，springaop代理
		IndexDao indexDao = applicationContext.getBean(IndexDao.class);
		indexDao.query();

	}
}





















