package com.llsydn.app;

import com.llsydn.dao.ConfigDao1;
import com.llsydn.dao.ConfigDao2;
import com.llsydn.extend.MyImportSelector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan({"com.llsydn"})
@Configuration
@MapperScan
@Import(MyImportSelector.class)
public class AppConfig {

	@Bean
	public ConfigDao1 configDao1(){
		return new ConfigDao1();
	}

	@Bean
	public ConfigDao2 configDao2(){
		configDao1();
		return new ConfigDao2();
	}

}
