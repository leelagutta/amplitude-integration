package com.amplitude.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqBeans implements ApplicationListener<ApplicationPreparedEvent> {

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Value("${amplitude-data.exchange}")
    private String amplitudeConfigExchange;

    @Value("${amplitude-data.queue}")
    private String amplitudeConfigRequestQueue;

    @Bean
    public Queue requestQueue() {
        return new Queue(amplitudeConfigRequestQueue, false);
    }

    @Bean
    public FanoutExchange requestExchange() {
        return new FanoutExchange(amplitudeConfigExchange);
    }

    @Bean
    public Binding requestBinding() {
        return BindingBuilder.bind(requestQueue()).to(requestExchange());
    }

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event){
        rabbitAdmin.declareQueue(requestQueue());
        rabbitAdmin.declareExchange(requestExchange());
        rabbitAdmin.declareBinding(requestBinding());
    }
}
