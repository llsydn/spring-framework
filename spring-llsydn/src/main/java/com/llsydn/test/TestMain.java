package com.llsydn.test;

import com.llsydn.config.Config1;
import com.llsydn.dao.IndexDao;
import com.llsydn.dao.MyDao;
import com.llsydn.dao.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/1/31 15:56
 */
public class TestMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 将Config1类注册放到spring的map中
		applicationContext.register(Config1.class);
		applicationContext.refresh();

		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		System.out.println(userDao);
		MyDao myDao = (MyDao) applicationContext.getBean("myDao");
		System.out.println(myDao);

		IndexDao indexDao = (IndexDao) applicationContext.getBean("indexDao");
		System.out.println(indexDao);
	}

}
