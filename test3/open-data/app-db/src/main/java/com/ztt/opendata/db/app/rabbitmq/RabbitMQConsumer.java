package com.ztt.opendata.db.app.rabbitmq;

import com.ztt.opendata.db.app.model.Photo;
import com.ztt.opendata.db.app.service.ImageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @Autowired
    private ImageService imageService;

    @RabbitListener(queues = "${ztt.rabbitmq.queue}")
    public void recievedMessage(Photo photo) {
        System.out.println("Recieved Message From RabbitMQ: " + photo.getName());
        this.imageService.save(photo);
    }
}
