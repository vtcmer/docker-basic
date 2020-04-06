package com.ztt.opendata.db.app.service.impl;

import com.ztt.opendata.db.app.model.Photo;
import com.ztt.opendata.db.app.repository.ImageRepository;
import com.ztt.opendata.db.app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageMongoRepository;

    @Autowired
    private ImageRepository imageDiskRepository;

    @Override
    public void save(Photo photo) {
        this.imageMongoRepository.save(photo);
        this.imageDiskRepository.save(photo);
    }
}
