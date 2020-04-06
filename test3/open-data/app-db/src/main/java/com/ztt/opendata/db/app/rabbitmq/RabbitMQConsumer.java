package com.ztt.opendata.db.app.rabbitmq;

import com.ztt.opendata.db.app.model.Photo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${ztt.rabbitmq.queue}")
    public void recievedMessage(Photo photo) {
        System.out.println("Recieved Message From RabbitMQ: " + photo.getName());
    }
}
