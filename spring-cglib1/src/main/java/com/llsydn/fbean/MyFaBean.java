package com.llsydn.fbean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

@Service
public class MyFaBean implements FactoryBean {
	@Override
	public Object getObject() throws Exception {
		return new MyBean();
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}
}
