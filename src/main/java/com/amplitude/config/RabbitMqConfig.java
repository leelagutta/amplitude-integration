package com.amplitude.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.util.ErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Slf4j
@EnableRabbit
public class RabbitMqConfig {

    @Getter
    @Value("${rabbitMQHost}")
    private String host;

    @Getter
    @Value("${rabbitMQUsername}")
    private String username;

    @Getter
    @Value("${rabbitMQPassword}")
    private String password;

    public RabbitMqConfig() {
//        log.info("RabbitMQConfig starting");
    }

    @Bean
    public ConnectionFactory rabbitConnectionFactory() throws URISyntaxException {
        CachingConnectionFactory cf = new CachingConnectionFactory();
        cf.setUri(new URI(String.format("amqp://%s:%s@%s", username, password, host)));
        return cf;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() throws URISyntaxException {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory());
        factory.setErrorHandler(new CustomErrorHandler());
        return factory;
    }


    @Scope("prototype")
    @Bean
    public DefaultMessageHandlerMethodFactory defaultHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        factory.setMessageConverter(converter);
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() throws URISyntaxException {
        return new RabbitAdmin(rabbitConnectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws URISyntaxException {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory());
        template.setMandatory(true);
        template.setChannelTransacted(true);
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        template.setMessageConverter(converter);
        return template;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Log4j
    @NoArgsConstructor
    public static class CustomErrorHandler implements ErrorHandler {

        @Override
        public void handleError(Throwable throwable) {
            if (throwable instanceof ListenerExecutionFailedException) {
                ListenerExecutionFailedException e = (ListenerExecutionFailedException) throwable;
//                log.error(e.getCause().getMessage());
            }
        }
    }
}
