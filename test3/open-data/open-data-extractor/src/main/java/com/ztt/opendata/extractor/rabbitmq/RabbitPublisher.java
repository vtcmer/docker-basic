package com.ztt.opendata.extractor.rabbitmq;

import com.ztt.opendata.extractor.event.IEvent;
import com.ztt.opendata.extractor.event.IPublisher;
import com.ztt.opendata.extractor.model.Photo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RabbitPublisher<T> implements IPublisher<T> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    abstract protected String getExchange();
    abstract protected String getRoutingKey();

    @Override
    public void sendData(IEvent<T> event) {
        rabbitTemplate.convertAndSend(getExchange(), getRoutingKey(), event.getEvent());
    }

}
