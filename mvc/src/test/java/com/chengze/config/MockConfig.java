package com.chengze.config;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.chengze.service.MessageSQSService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URL;

import static org.mockito.ArgumentMatchers.anyString;

@Configuration
public class MockConfig {

    @Bean
    @Profile({"unit"})
    public AmazonS3 getAmazonS3bean(){
        AmazonS3 s3Client= Mockito.mock(AmazonS3.class);
//        GetQueueUrlResult fakeurl=
//                Mockito.mock(GetQueueUrlResult.class);
        return s3Client;///memory1
    }

    @Bean
    @Profile({"unit"})
    public AmazonSQS getAmazonSQSbean(){
        AmazonSQS sqSClient= Mockito.mock(AmazonSQS.class );
        GetQueueUrlResult fakeurl=
                Mockito.mock(GetQueueUrlResult.class);
//         new URL("http://IP:5555/chengze234");
//        String fakequeuename="chengze";
        Mockito.when(sqSClient.getQueueUrl(anyString())).thenReturn(fakeurl);
        return sqSClient;
    }
}
