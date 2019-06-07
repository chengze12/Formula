package com.chengze.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = "com.chengze",
        excludeFilters= @ComponentScan.Filter(type = FilterType.REGEX,pattern="com.chengze.api.*"))
public class AppConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Bean(name= "shareProperties")
    public PropertiesFactoryBean getShareProperties(){
        logger.debug("I am in the share properties");
        PropertiesFactoryBean bean =new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("META-INF/share-runtime.properties"));
        return bean;
    }

}

