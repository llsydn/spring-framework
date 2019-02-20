package com.cglib.framework;

import com.cglib.dao.CityDao;
import com.cglib.factorybean.CommonFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/20 10:56
 */
@Component
public class CommonImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// 创建并得到一个BeanDefinition对象
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CityDao.class);
		AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
		// 修改返回bean的class，使用FactoryBean的getObject()方法去获取CityDao对象（否则不行，因为CityDao是接口）
		// mybatis的源码就是使用这个方法，自己手动给spring工厂注册bean对象的
		beanDefinition.setBeanClass(CommonFactoryBean.class);
		// 给构造方法，注入一个类（设置CommonFactoryBean返回的代理对象的类型）
		beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.cglib.dao.CityDao1");
		registry.registerBeanDefinition("cityDao1", beanDefinition);

	}
}
