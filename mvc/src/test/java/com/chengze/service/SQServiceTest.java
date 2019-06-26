package com.chengze.service;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.chengze.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.ArgumentMatchers.any;

@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class SQServiceTest {
    @Autowired
    private MessageSQSService messageSQSService;

    @Autowired
    private AmazonSQS SQSclient;

    @Value("${jms.queue.name}")
    private String queue;

    private String queueUrl = "mockUrl";

    @Test
    public void sendMessageTest(){
        String messageBody = "Success!";
        int delaySec = 5;
        messageSQSService.sendMessage(messageBody, 5);
        Mockito.verify(SQSclient,Mockito.times(1)).sendMessage(any());
    }

}
