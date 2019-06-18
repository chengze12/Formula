package com.chengze.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageSQSService {
    private AmazonSQS sqs;

    private String queueUrl;
    public MessageSQSService(@Autowired AmazonSQS sqs, @Value("${jms.queue.name}")String sqsName){
        this.sqs=sqs;
        this.queueUrl=getQueueUrl(sqsName);
    }

    public String getQueueUrl(String queueName){
        GetQueueUrlResult request = sqs.getQueueUrl(queueName);
        String queueurl =request.getQueueUrl();
        return queueurl;
    }

   // private String queueUrl="https://sqs.us-east-1.amazonaws.com/873314709919/Formula_queue_dev";



    public void sendMessage(String message){
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }
}
