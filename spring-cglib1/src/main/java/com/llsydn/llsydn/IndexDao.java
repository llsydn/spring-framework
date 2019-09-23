package com.llsydn.llsydn;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

@Repository
public class IndexDao implements ApplicationContextAware {
	//@Autowired
	private ApplicationContext applicationContext; //自动装配时忽略注入

	public void test() {
		System.out.println(applicationContext);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

	}
}
