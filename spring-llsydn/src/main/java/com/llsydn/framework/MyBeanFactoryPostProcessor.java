package com.llsydn.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author lilinshen
 * @title BeanFactoryPostProcessor类可以插手spring工厂bean的初始化过程
 * BeanDefinitionRegistryPostProcessor是BeanFactoryPostProcessor的子类扩展了，增加了postProcessBeanDefinitionRegistry方法
 * @description 请填写相关描述
 * @date 2019/2/1 17:11
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	/**
	 * 在spring工厂实例化之前执行
	 *
	 * @param beanFactory the bean factory used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("----");
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userDao");
		// 修改userDao为prototype原型
		beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
		beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);

	}

}
