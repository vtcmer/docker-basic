package com.ztt.opendata.image.rabbitmq;

import com.ztt.opendata.image.commons.GifSequenceWriter;
import com.ztt.opendata.image.model.Photo;
import com.ztt.opendata.image.service.GeneratorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @Autowired
    private GeneratorService generatorService;

    @RabbitListener(queues = "${ztt.rabbitmq.queue}")
    public void recievedMessage(Photo photo) {
        System.out.println("Recieved Message From RabbitMQ IMAGE: " + photo.getName());
        this.generatorService.generate(photo);
    }

}
