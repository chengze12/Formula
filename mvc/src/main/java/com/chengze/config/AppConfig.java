package com.chengze.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
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

    @Value("${aws.region}")
    private String clientRegion;

    @Bean
    @Profile({"dev","test"})
    public AmazonS3 getAmazonS3bean(){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(clientRegion)
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();
        return s3Client;
    }

    @Bean
//    @Profile({"dev", "test"})
    public AmazonSQS getAmazonSQS(){
        AmazonSQS SQSclient = AmazonSQSClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        return SQSclient;
    }

}

