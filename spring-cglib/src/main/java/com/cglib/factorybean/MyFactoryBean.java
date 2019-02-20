package com.cglib.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author lilinshen
 * @title FactoryBean
 * @description 请填写相关描述
 * @date 2019/2/19 15:12
 */
@Component
public class MyFactoryBean implements FactoryBean {
	@Override
	public Object getObject() throws Exception {
		return new SpringBean();
	}

	@Override
	public Class<?> getObjectType() {
		return SpringBean.class;
	}
}
