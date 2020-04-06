package com.ztt.opendata.extractor.event;

public interface IPublisher<T> {

    void sendData(IEvent<T> event);

}
