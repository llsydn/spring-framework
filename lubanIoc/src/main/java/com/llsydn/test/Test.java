package com.llsydn.test;

import com.llsydn.app.AppConfig;
import com.llsydn.dao.IndexDao;
import com.llsydn.extend.MyIS;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		//System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:/cglib");
		//System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();

		IndexDao indexDao = ac.getBean(IndexDao.class);
		System.out.println(indexDao);
		indexDao.query();

		MyIS myIS = ac.getBean(MyIS.class);
		myIS.test();

	}
}
