package com.ztt.opendata.extractor.rabbitmq.impl;

import com.ztt.opendata.extractor.rabbitmq.RabbitPublisher;
import com.ztt.opendata.extractor.model.Photo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitPhotoPublisher extends RabbitPublisher<Photo> {

    @Value("${ztt.rabbitmq.exchange}")
    private String exchange;

    @Value("${ztt.rabbitmq.routingkey}")
    private String routingkey;


    @Override
    protected String getExchange() {
        return exchange;
    }

    @Override
    protected String getRoutingKey() {
        return routingkey;
    }
}
