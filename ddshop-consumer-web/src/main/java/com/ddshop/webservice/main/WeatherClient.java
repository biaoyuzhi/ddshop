package com.ddshop.webservice.main;


import com.ddshop.service.DemoService;
import com.ddshop.service.DemoServiceService;

public class WeatherClient {
    public static void main(String[] agrs){
        DemoServiceService service = new DemoServiceService();
        DemoService port = service.getPort(DemoService.class);
        String s = port.queryWeather("杭州");
        System.out.println(s);
    }
}
