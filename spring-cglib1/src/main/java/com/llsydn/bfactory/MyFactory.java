package com.llsydn.bfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyFactory implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		GenericBeanDefinition ctorsService = (GenericBeanDefinition) beanFactory.getBeanDefinition("ctorsService");
		ctorsService.getConstructorArgumentValues().addGenericArgumentValue("com.llsydn.service.MyCtors");

	}
}
