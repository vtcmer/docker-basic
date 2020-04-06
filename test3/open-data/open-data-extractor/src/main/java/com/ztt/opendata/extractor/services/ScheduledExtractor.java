package com.ztt.opendata.extractor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledExtractor {

    @Autowired
    DataExtractor dataExtractor;

    @Scheduled(fixedDelayString = "${extractor.scheduled.time}")
    void executeTask(){
        System.out.println("Executing task at "+ new Date());
        this.dataExtractor.execute();
    }
}
