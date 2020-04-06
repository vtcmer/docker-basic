package com.ztt.opendata.extractor.event.impl;

import com.ztt.opendata.extractor.event.IEvent;
import com.ztt.opendata.extractor.model.Photo;

public class PhotoEvent implements IEvent<Photo> {

    Photo photo;

    public PhotoEvent(Photo photo){
        this.photo = photo;
    }

    @Override
    public Photo getEvent() {
        return this.photo;
    }
}
