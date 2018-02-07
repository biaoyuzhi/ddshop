package com.ddshop.web;

import com.ddshop.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class WeatherAction {
    //注意，此处为名称注入，非类型注入
    @Resource
    private DemoService weatherClient;

    @ResponseBody
    @RequestMapping("/weatherSearch")
    public String weatherSearch(){
        String weather = weatherClient.queryWeather("杭州");
        System.out.println(weather);
        return weather;
    }
}
