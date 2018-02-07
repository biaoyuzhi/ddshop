package com.ddshop.service;

public class DemoServiceImpl implements DemoService{
    @Override
    public String queryWeather(String cityName) {
        System.out.println("from client..."+cityName);
        String weather = "晴";
        return weather;
    }
}
