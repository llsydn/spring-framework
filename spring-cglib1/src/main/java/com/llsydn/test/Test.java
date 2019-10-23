package com.llsydn.test;

import com.llsydn.app.Appconfig;
import com.llsydn.dao.OrderDao;
import com.llsydn.llsydn.IndexDao;
import com.llsydn.merge.PcBd;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		//System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:/cglib");
		System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
		//System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(Appconfig.class);
		//ac.addBeanFactoryPostProcessor(null);

		//ac.setAllowCircularReferences(false); //设置关闭循环依赖

		ac.refresh();

		OrderDao orderDao = ac.getBean(OrderDao.class);
		orderDao.query();

		PcBd parentBd = (PcBd) ac.getBean("parentBd");
		parentBd.test();


		PcBd childBd = (PcBd) ac.getBean("childBd");
		childBd.test();

		//打印的是FbMethod对象
		System.out.println(ac.getBean("famethod"));


		System.out.println(ac.getBean("LlsBean"));

//		Enhancer enhancer = new Enhancer();
//		//增强父类，地球人都知道cglib是基于继承来的
//		enhancer.setSuperclass(LubanAppcofig.class);
//		//不继承Factory接口
//		enhancer.setUseFactory(false);
//		enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
//		// BeanFactoryAwareGeneratorStrategy是一个生成策略
//		// 主要为生成的CGLIB类中添加成员变量$$beanFactory
//		// 同时基于接口EnhancedConfiguration的父接口BeanFactoryAware中的setBeanFactory方法，
//		// 设置此变量的值为当前Context中的beanFactory,这样一来我们这个cglib代理的对象就有了beanFactory
//		//有了factory就能获得对象，而不用去通过方法获得对象了，因为通过方法获得对象不能控制器过程
//		//该BeanFactory的作用是在this调用时拦截该调用，并直接在beanFactory中获得目标bean
//		//enhancer.setStrategy(new ConfigurationClassEnhancer.BeanFactoryAwareGeneratorStrategy(classLoader));
//		//过滤方法，不能每次都去new
//		enhancer.setCallback(new LubanCallback());
//		LubanAppcofig chil = (LubanAppcofig) enhancer.create();
//		chil.testProxy();
	}
}
