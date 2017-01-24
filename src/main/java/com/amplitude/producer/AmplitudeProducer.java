package com.amplitude.producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AmplitudeProducer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public AmplitudeProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String m) {
        rabbitTemplate.convertAndSend(m);
    }
}
