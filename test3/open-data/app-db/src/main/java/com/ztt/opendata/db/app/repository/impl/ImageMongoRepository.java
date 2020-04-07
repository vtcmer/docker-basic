package com.ztt.opendata.db.app.repository.impl;

import com.ztt.opendata.db.app.entity.CameraEntity;
import com.ztt.opendata.db.app.model.Photo;
import com.ztt.opendata.db.app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository("imageMongoRepository")
public class ImageMongoRepository implements ImageRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Photo photo) {
        System.out.println("Save "+photo.getName()+" in Mongo");

        CameraEntity cameraEntity = new CameraEntity();
        cameraEntity.setId(photo.getName());
        cameraEntity.setDate(photo.getDate());
        cameraEntity.setLocation(new Point(photo.getLongitude().doubleValue(), photo.getLatitude().doubleValue()));
        this.mongoTemplate.save(cameraEntity);
    }
}
