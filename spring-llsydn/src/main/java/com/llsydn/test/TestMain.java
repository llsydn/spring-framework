package com.llsydn.test;

import com.llsydn.config.Config1;
import com.llsydn.dao.IndexDao;
import com.llsydn.dao.MyDao;
import com.llsydn.proxy.MyInvocationHandler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

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
		// 手动添加自定义的BeanFactoryPostProcessor，没有@Component注解。有@Component注解的，就不知自定义的，是系统的
		// applicationContext.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());
		applicationContext.refresh();

		// UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		// System.out.println(userDao);
		// MyDao myDao = (MyDao) applicationContext.getBean("myDao");
		// System.out.println(myDao);

		// IndexDao indexDao = (IndexDao) applicationContext.getBean("indexDao");
		// System.out.println(indexDao);




		IndexDao indexDao1 = (IndexDao) Proxy.newProxyInstance(TestMain.class.getClassLoader(), new Class[]{IndexDao.class}, new MyInvocationHandler());
		indexDao1.query();
		System.out.println(indexDao1);
	}

}
