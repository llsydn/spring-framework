package com.cglib.factorybean;

import com.cglib.proxy.MyInvocationHandler;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/20 10:32
 */
public class CommonFactoryBean implements FactoryBean {
	Class common;

	public CommonFactoryBean(Class common) {
		// 通过构造方法传入
		// beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.cglib.dao.CityDao");
		this.common = common;
	}

	@Override
	public Object getObject() throws Exception {
		Class[] clazzs = new Class[]{common};
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
		return Proxy.newProxyInstance(CommonFactoryBean.class.getClassLoader(), clazzs, myInvocationHandler);
	}

	@Override
	public Class<?> getObjectType() {
		return common;
	}
}
