package hello.com.chengze.worker.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SQSMessageService {
//    @Autowired
    private AmazonSQS  sqsClient = AmazonSQSClientBuilder.standard()
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();
    @Value("${jms.queue.name}")
    private String queueName;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessage() {
        logger.debug("Receiving message from MyQueue.");
        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(this.getQueueUrl(queueName));
        final List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
        for (final Message message : messages) {
            System.out.println("Message");
            System.out.println("  MessageId:     " + message.getMessageId());
            System.out.println("  ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("  MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("  Body:          " + message.getBody());
            for (final Map.Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("Attribute");
                System.out.println("  Name:  " + entry.getKey());
                System.out.println("  Value: " + entry.getValue());
            }
            System.out.println("Deleting a message.\n");
            final String messageReceiptHandle = message.getReceiptHandle();
            sqsClient.deleteMessage(new DeleteMessageRequest(this.getQueueUrl(queueName), messageReceiptHandle));
        }
    }
    public String getQueueUrl( String queueName){
        String queueUrl = sqsClient.getQueueUrl(queueName).getQueueUrl();
        return queueUrl;
    }

}
