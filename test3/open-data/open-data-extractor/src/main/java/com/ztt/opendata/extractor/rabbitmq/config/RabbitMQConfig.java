package com.ztt.opendata.extractor.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class RabbitMQConfig {



    @Value("${ztt.rabbitmq.exchange}")
    String exchange;

    @Value("${ztt.rabbitmq.routingkey}")
    String routingkey;

    @Value("${ztt.rabbitmq.app-db.queue}")
    String queueNameAppDb;

    @Value("${ztt.rabbitmq.app-image.queue}")
    String queueNameAppImage;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue queueAppDb() {
        return new Queue(queueNameAppDb, true);
    }

    @Bean
    Queue queueAppImage() {
        return new Queue(queueNameAppImage, true);
    }

    @Bean
    Binding bindingAppDb(Queue queueAppDb, DirectExchange exchange) {
        return BindingBuilder.bind(queueAppDb).to(exchange).with(routingkey);
    }

    @Bean
    Binding bindingAppImage(Queue queueAppImage, DirectExchange exchange) {
        return BindingBuilder.bind(queueAppImage).to(exchange).with(routingkey);
    }



    /*
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
     */

    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }



}
