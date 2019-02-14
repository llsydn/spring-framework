package com.llsydn.framework;

import com.llsydn.dao.IndexDao;
import com.llsydn.proxy.MyInvocationHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/14 11:26
 */
@Component
public class MyFactoryBean implements FactoryBean {
	Class clazz;

	public MyFactoryBean() {
	}

	public MyFactoryBean(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{IndexDao.class}, new MyInvocationHandler());
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}
}
