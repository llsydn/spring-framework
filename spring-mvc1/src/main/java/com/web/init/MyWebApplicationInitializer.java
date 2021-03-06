package com.web.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletCxt) {
		// spring ioc
		AnnotationConfigWebApplicationContext ac =
				new AnnotationConfigWebApplicationContext();
		ac.register(Appconfig.class);
		ac.refresh();

		// spring mvc
		DispatcherServlet servlet = new DispatcherServlet(ac);
		ServletRegistration.Dynamic registration =
				servletCxt.addServlet("app", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/app/*");

		System.out.println("----执行MyWebApplicationInitializer----");

	}
}
