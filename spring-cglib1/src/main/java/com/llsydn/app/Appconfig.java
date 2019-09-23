package com.llsydn.app;

import com.llsydn.service.IndexService;
import com.llsydn.service.OrderService;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.llsydn")
@EnableAspectJAutoProxy
@ImportResource("classpath:spring.xml")
public class Appconfig {


	/**
	 * 当该方法加了static，在orderService()调用了indexService()方法，indexService会实例化两次
	 */
	@Bean
	public static IndexService indexService() {
		return new IndexService();
	}

	@Bean
	public OrderService orderService() {
		indexService();
		return new OrderService();
	}

}
