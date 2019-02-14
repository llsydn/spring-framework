/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.util.function.Supplier;

import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Standalone application context, accepting annotated classes as input - in particular
 * {@link Configuration @Configuration}-annotated classes, but also plain
 * {@link org.springframework.stereotype.Component @Component} types and JSR-330 compliant
 * classes using {@code javax.inject} annotations. Allows for registering classes one by
 * one using {@link #register(Class...)} as well as for classpath scanning using
 * {@link #scan(String...)}.
 *
 * <p>In case of multiple {@code @Configuration} classes, @{@link Bean} methods defined in
 * later classes will override those defined in earlier classes. This can be leveraged to
 * deliberately override certain bean definitions via an extra {@code @Configuration}
 * class.
 *
 * <p>See @{@link Configuration}'s javadoc for usage examples.
 *
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 3.0
 * @see #register
 * @see #scan
 * @see AnnotatedBeanDefinitionReader
 * @see ClassPathBeanDefinitionScanner
 * @see org.springframework.context.support.GenericXmlApplicationContext
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

	/**
	 * 这个类顾名思义是一个reader，一个读取器
	 * 读取什么呢？AnnotatedBeanDefinitionReader意思是读取一个被加了注解的bean
	 * 这个类在构造方法中实例化的
	 * 作用：读取到一个加了注解的类，并把他转为BeanDefinition
	 */
	private final AnnotatedBeanDefinitionReader reader;

	/**
	 * 顾名思义，是一个扫描器，扫描所有加了注解的bean
	 * 这个类在构造方法中实例化的
	 */
	private final ClassPathBeanDefinitionScanner scanner;


	/**
	 * 创建一个需要填充的新的AnnotationConfigApplicationContext，初始化一个bean的读取和扫描器
	 * 通过{@link #register}调用，然后手动{@linkplain #refresh refresh}。
	 */
	public AnnotationConfigApplicationContext() {
		/**
		 * 父类的构造方法
		 * 创建一个读取注解的Bean定义读取器，扫描器
		 * 什么是bean定义？BeanDefinition
		 */
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 * 使用给定的DefaultListableBeanFactory创建一个新的AnnotationConfigApplicationContext。
	 * 用于此上下文的DefaultListableBeanFactory实例
	 */
	public AnnotationConfigApplicationContext(DefaultListableBeanFactory beanFactory) {
		super(beanFactory);
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 * 创建一个新的AnnotationConfigApplicationContext，派生bean定义
	 * 从给定的带注释的类中自动刷新上下文。
	 * @param annotatedClasses 一个或多个带注释的类，
	 * 例如:{@link Configuration @Configuration}类
	 */
	public AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
		this();
		register(annotatedClasses);
		refresh();
	}

	/**
	 * 创建一个新的AnnotationConfigApplicationContext，扫描bean定义
	 * 在给定的包中并自动刷新上下文。
	 * @param basePackages 包来检查带注释的类
	 */
	public AnnotationConfigApplicationContext(String... basePackages) {
		this();
		scan(basePackages);
		refresh();
	}


	/**
	 * 将给定的自定义{@code 环境}传播到底层
	 * {@link AnnotatedBeanDefinitionReader} 和 {@link ClassPathBeanDefinitionScanner}.
	 */
	@Override
	public void setEnvironment(ConfigurableEnvironment environment) {
		super.setEnvironment(environment);
		this.reader.setEnvironment(environment);
		this.scanner.setEnvironment(environment);
	}

	/**
	 * 提供一个自定义的{@link BeanNameGenerator}，用于与{@link AnnotatedBeanDefinitionReader}一起使用}
	 * 和/或{@link ClassPathBeanDefinitionScanner}(如果有)。
	 * <p>默认是{@link org.springframework.context.annotation.AnnotationBeanNameGenerator}。
	 * <p>此方法的任何调用都必须在调用{@link # register(Class…)}之前进行
	 * 和/或{ @link #扫描(字符串…)}。
	 * @see AnnotatedBeanDefinitionReader#setBeanNameGenerator
	 * @see ClassPathBeanDefinitionScanner#setBeanNameGenerator
	 */
	public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
		this.reader.setBeanNameGenerator(beanNameGenerator);
		this.scanner.setBeanNameGenerator(beanNameGenerator);
		getBeanFactory().registerSingleton(
				AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR, beanNameGenerator);
	}

	/**
	 * 将{@link ScopeMetadataResolver}设置为用于检测到的bean类。
	 * <p>默认是{@link AnnotationScopeMetadataResolver}
	 * <p>此方法的任何调用都必须在调用{@link # register(Class…)}之前进行
	 * 和/或{ @link #扫描(字符串…)}
	 */
	public void setScopeMetadataResolver(ScopeMetadataResolver scopeMetadataResolver) {
		this.reader.setScopeMetadataResolver(scopeMetadataResolver);
		this.scanner.setScopeMetadataResolver(scopeMetadataResolver);
	}


	//---------------------------------------------------------------------
	// 实现AnnotationConfigRegistry接口的方法
	//---------------------------------------------------------------------

	/**
	 * 注册一个或多个要处理的注释类。
	 * <p>注意，必须为上下文调用{@link # refresh()}
	 * 以完全处理新类。
	 * @param annotatedClasses 一个或多个带注释的类，
	 * 例如: {@link Configuration @Configuration}类
	 * @see #scan(String...)
	 * @see #refresh()
	 */
	public void register(Class<?>... annotatedClasses) {
		Assert.notEmpty(annotatedClasses, "At least one annotated class must be specified");
		this.reader.register(annotatedClasses);
	}

	/**
	 * 在指定的基本包中执行扫描。
	 * <p>注意，必须为上下文调用{@link #refresh()}
	 * 以完全处理新类。
	 * @param basePackages 包来检查带注释的类
	 * @see #register(Class...)
	 * @see #refresh()
	 */
	public void scan(String... basePackages) {
		Assert.notEmpty(basePackages, "At least one base package must be specified");
		this.scanner.scan(basePackages);
	}


	//---------------------------------------------------------------------
	// 注册单个bean的方便方法
	//---------------------------------------------------------------------

	/**
	 * 从给定的bean类注册一个bean，从中派生其元数据
	 * 类声明的注释，并可选地提供显式构造函数
	 * 在自动布线过程中需要考虑的参数。
	 * <p> bean名称将根据带注释的组件规则生成。
	 * @param注释了bean的类
	 * 参数值被输入到Spring的参数中
	 * 构造函数解析算法，解决所有参数或只是
	 * 具体的，其余的要通过常规的自动布线来解决
	 * (可以是{@code null}或空})
	 * @since 5.0
	 */
	public <T> void registerBean(Class<T> annotatedClass, Object... constructorArguments) {
		registerBean(null, annotatedClass, constructorArguments);
	}

	/**
	 * 从给定的bean类注册一个bean，从中派生其元数据
	 * 类声明的注释，并可选地提供显式构造函数
	 * 在自动布线过程中需要考虑的参数。
	 * bean的名称(可以是{@code null})
	 * @param注释了bean的类
	 * 参数值被输入到Spring的参数中
	 * 构造函数解析算法，解决所有参数或只是
	 * 具体的，其余的要通过常规的自动布线来解决
	 * (可以是{@code null}或空})
	 *
	 */
	public <T> void registerBean(@Nullable String beanName, Class<T> annotatedClass, Object... constructorArguments) {
		this.reader.doRegisterBean(annotatedClass, null, beanName, null,
				bd -> {
					for (Object arg : constructorArguments) {
						bd.getConstructorArgumentValues().addGenericArgumentValue(arg);
					}
				});
	}

	@Override
	public <T> void registerBean(@Nullable String beanName, Class<T> beanClass, @Nullable Supplier<T> supplier,
			BeanDefinitionCustomizer... customizers) {

		this.reader.doRegisterBean(beanClass, supplier, beanName, null, customizers);
	}

}
