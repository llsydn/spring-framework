package com.llsydn.config;

import com.llsydn.framework.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/1 13:57
 */
@ComponentScan({"com.llsydn"})
@Configuration
@Import(MyImportBeanDefinitionRegistrar.class)
public class Config1 {


}
