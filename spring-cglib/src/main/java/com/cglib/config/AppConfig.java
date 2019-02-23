package com.cglib.config;

import com.cglib.service.IndexService;
import com.cglib.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/19 10:02
 */
@Configuration //这个注解不加，也能运行，加了之后这个类就变成了cglib代理类
@ComponentScan(value = "com.cglib")
@EnableAspectJAutoProxy //开启@Aspect注解
public class AppConfig {

	@Bean
	public static IndexService indexService() {
		return new IndexService();
	}

	@Bean
	public UserService userService() {
		// 又调用了一次初始化indexService
		// 在这个类没有加@Configuration注解，indexService会被初始化两次；加了@Configuration就只会执行一次
		// 说明indexService()没有起作用，没有被调用到
		indexService();
		return new UserService();
	}

}

/**
 * 生成的cglib代理类，继承AppConfig类，实现EnhancedConfiguration接口，添加$$beanFactory属性
 * public class AppConfig$$CGLIB extends AppConfig implements EnhancedConfiguration{
 *     BeanFactory $$beanFactory; //需要一个构造器注入BeanFactory
 *
 *     @Bean
 * 	   public UserService userService() {
 * 	       spring 底层重要的代码
 * 	        if (isCurrentlyInvokedFactoryMethod(beanMethod)) {
 * 				// 执行父类的方法
 * 				return cglibMethodProxy.invokeSuper(enhancedConfigInstance, beanMethodArgs);
 * 			}
 * 			// 正在调用的方法 != 正在执行的方法
 * 			return resolveBeanReference(beanMethod, beanMethodArgs, beanFactory, beanName);
 * 	   }
 *
 * 	   我们自己猜想
 * 	   @Bean
 *     public UserService userService() {
 *         if (first) {
 *
 * 			   return new xxxx;
 * 		   }else{
 * 		       $$beanFactory.getBean(xxxx);
 * 		   }
 *     }
 * }
 */


