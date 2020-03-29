package com.ztt.docker.app.appdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping(value = "/test")
    public String test() throws UnknownHostException {
        final String serverAddress = InetAddress.getLocalHost().getHostAddress();
        return "The service is UP FROM: "+serverAddress;
    }

}
