package com.superheroes.training_superheroes.Services;

import com.superheroes.training_superheroes.config.SqsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.DeleteMessageResponse;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

@Service
public class SuperHeroConsumer {
    @Autowired
    SqsConfig sqsConfig;

    @Autowired
    SqsClient sqsClient;
    public String ConsumeSuperhero(){
       ReceiveMessageResponse response = sqsClient.receiveMessage(ReceiveMessageRequest.builder()
               .queueUrl(sqsConfig.getQueueUrl())
               .build());

        DeleteMessageResponse deletedMessage = sqsClient.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl(sqsConfig.getQueueUrl())
                .receiptHandle(response.messages().get(0).body())
                .build());

        System.out.println("deleted message response: "+ deletedMessage.toString());
        return response.messages().get(0).body();
    }

    public String getsuperhero(){
        return sqsClient.receiveMessage(ReceiveMessageRequest.builder()
                .queueUrl(sqsConfig.getQueueUrl())
                .build()).messages()
                .get(0).body();
    }
}
