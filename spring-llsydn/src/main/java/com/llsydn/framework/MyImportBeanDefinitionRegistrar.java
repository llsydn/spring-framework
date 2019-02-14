package com.llsydn.framework;

import com.llsydn.dao.IndexDao;
import com.llsydn.dao.MyDao;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lilinshen
 * @title 可是插手spring工厂的初始化过程，自己手动给spring工厂注册bean对象
 * @description 请填写相关描述
 * @date 2019/2/14 10:55
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// IndexDao indexDao = (IndexDao) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{IndexDao.class}, new MyInvocationHandler());
		// indexDao.query();
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(IndexDao.class);
		AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
		// 修改返回bean的class，使用FactoryBean的getObject()方法去获取IndexDao对象（否则不行，因为IndexDao是接口）
		// mybatis的源码就是使用这个方法，自己手动给spring工厂注册bean对象的
		beanDefinition.setBeanClass(MyFactoryBean.class);
		registry.registerBeanDefinition("indexDao", beanDefinition);


		// BeanDefinitionBuilder，bd的构造器
		builder = BeanDefinitionBuilder.genericBeanDefinition(MyDao.class);
		beanDefinition = builder.getBeanDefinition();
		registry.registerBeanDefinition("myDao", beanDefinition);

	}
}
