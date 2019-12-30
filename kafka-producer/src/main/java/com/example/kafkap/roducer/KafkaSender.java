package com.example.kafkap.roducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 *
 * @author Jarvis
 * @date 2018/8/3
 */
@Component
public class KafkaSender<T> {

    private Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, T> kafkaTemplate;


    /**
     * kafka 发送消息
     *
     * @param obj 消息对象
     */
    void send(T obj) {
        //发送消息
        ListenableFuture<SendResult<String, T>> future = kafkaTemplate.send("test", obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Produce: The message failed to be sent:" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, T> stringObjectSendResult) {
                //TODO 业务处理
                logger.info("Produce: The message was sent successfully:");
                logger.info("Produce: _+_+_+_+_+_+_+ result: " + stringObjectSendResult.toString());
            }
        });

        try {
            SendResult<String, T> result = future.get(3, TimeUnit.SECONDS);
            logger.info("send result is:" + result);
            logger.info("meta data: " + result.getRecordMetadata().toString());
            logger.info("record: " + result.getProducerRecord().toString());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            logger.error(e.getMessage());
        }
    }
}