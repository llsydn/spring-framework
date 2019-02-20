package com.cglib.framework;

import com.cglib.service.CityService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author lilinshen
 * @title BeanFactoryPostProcessor是spring提供的一个bean后置处理器（只能对spring的bean进行修改，无法新增）
 * @description 请填写相关描述
 * @date 2019/2/20 9:46
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition cityService1 = (GenericBeanDefinition) beanFactory.getBeanDefinition("cityService1");
		// 修改cityService1===> cityService对象（狸猫换太子）
		// spring实例化bean是通过beandefinition的beanclass属性去实例化的
		cityService1.setBeanClass(CityService.class);
	}
}
