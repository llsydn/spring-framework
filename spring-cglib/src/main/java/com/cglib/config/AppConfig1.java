package com.cglib.config;

import com.cglib.framework.CommonImportBeanDefinitionRegistrar;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/19 14:57
 */
@Configuration
@ComponentScan("com.cglib")
@Import(CommonImportBeanDefinitionRegistrar.class)
@MapperScan("com.cglib.dao")
public class AppConfig1 {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("123456");
		// 避免中文乱码要指定useUnicode和characterEncoding
		driverManagerDataSource.setUrl("jdbc:mysql://192.168.99.100:3306/test?useUnicode=true&characterEncoding=UTF8");
		return driverManagerDataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean;
	}


}
