package com.cglib.test;

import com.cglib.config.AppConfig1;
import com.cglib.dao.CityDao;
import com.cglib.dao.CityDao1;
import com.cglib.factorybean.MyFactoryBean;
import com.cglib.service.CityService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/19 10:03
 */
public class TestMain1 {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AppConfig1.class);
		applicationContext.refresh();

		CityDao1 cityDao = (CityDao1) applicationContext.getBean("cityDao1");
		System.out.println(cityDao.query());

		System.out.println(applicationContext.getBean("cityService"));
		System.out.println(applicationContext.getBean("cityService1"));

		// 这个获取到的是springbean对象
		System.out.println(applicationContext.getBean("myFactoryBean"));
		// 这个是获取到的是myfactorybean原对象（前面加&符号）
		MyFactoryBean myFactoryBean = (MyFactoryBean) applicationContext.getBean("&myFactoryBean");
		System.out.println(myFactoryBean);

		CityService cityService = (CityService) applicationContext.getBean("cityService");
		System.out.println(cityService);
		// 这里会输出两次查询数据库的sql
		cityService.query();
		cityService.query();
	}
}





















