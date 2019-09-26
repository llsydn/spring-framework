package com.web.init;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class SpringApplicationLi {

	/**
	 * mvc
	 * request ----> !servlet.class（这是不可能实现的）
	 * request ----> servlet ----> controller（只能是方法调用，不然无法实现）
	 * 							   -----（方法调用，底层一定是反射技术：indexController:index()）
	 *
	 * request ----> servlet.class
	 */
	public static void run() throws LifecycleException {
		//spring ioc 环境
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(Appconfig.class);
		ac.refresh();

		File base = new File(System.getProperty("java.io.tmpdir"));
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(9090);

		/**
		 * tomcat.addWebapp表示这是一个web项目
		 * contextPath：tomcat的访问路径
		 * docBase：项目的web目录
		 * 所以这里不能用addWebapp（springboot也不是这样做的）
		 * Context context = tomcat.addWebapp("/", "/llsydn");
		 */
		Context context = tomcat.addContext("", base.getAbsolutePath());

		//spring web 环境
		DispatcherServlet dispatcher = new DispatcherServlet(ac);
		//表明tomcat启动的过程当中就会调用DispatcherServlet#init()方法
		//初始化controller和请求映射
		Tomcat.addServlet(context, "mvc", dispatcher).setLoadOnStartup(-1);
		context.addServletMapping("/", "mvc");
		tomcat.start();
		tomcat.getServer().await();
	}


	/**
	 * 请求映射：
	 *
	 * map1---------------------------------------->BeanNameUrlHandlerMapping（类名）
	 * /index1:indexController1:handleRequest------>拿到的是类，可直接调用handleRequest方法
	 *
	 * map2---------------------------------------->RequestMappingHandlerMapping
	 * /index:index()------------------------------>拿到的是方法，需要反射调用
	 *
	 */

	/**
	 * BeanNameUrlHandlerMapping,工作机制：
	 * 在init{
	 *     扫描所有实现了Controller接口的类，
	 *     map<'/index1', IndexController1>
	 * }
	 *
	 * RequestMappingHandlerMapping,工作机制：
	 * 在init{
	 *     map<'/index', index()>
	 * }
	 *
	 * 但是springboot，可以直接访问到静态资源；/index.html
	 * 但是通过这两个handlermapping，找不到对应的处理。
	 * 说明了springboot肯定是增加了自己的handlermapping，处理静态资源
	 */




}
