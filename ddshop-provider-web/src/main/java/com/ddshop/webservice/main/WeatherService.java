package com.ddshop.webservice.main;

import com.ddshop.service.DemoServiceImpl;

import javax.xml.ws.Endpoint;

public class WeatherService {
    public static void main(String[] args){
        Endpoint.publish("http://127.0.0.1:1111/weather",new DemoServiceImpl());
    }
}
