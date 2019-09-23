package com.llsydn.llsydn;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class DaoFactoryBean implements FactoryBean {
	private String msg;

	public void test() {
		System.out.println("dao factory bean");
	}

	@Override
	public Object getObject() throws Exception {
		TempDaoFactoryBean temp = new TempDaoFactoryBean();
		String[] msgArray = msg.split(",");
		temp.setMsg1(msgArray[0]);
		temp.setMsg1(msgArray[1]);
		temp.setMsg1(msgArray[2]);
		return temp;
	}

	@Override
	public Class<?> getObjectType() {
		return TempDaoFactoryBean.class;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
