package com.amplitude.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class AmplitudeListener implements MessageListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${amplitude-data.queue}")
    private String amplitudeConfigRequestQueue;

    @Override
    public void onMessage(Message message) {
        String msg1  = (String) rabbitTemplate.receiveAndConvert(amplitudeConfigRequestQueue);
        System.out.println(msg1);
    }
}
