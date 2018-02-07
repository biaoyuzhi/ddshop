package com.dubbo.web;

import com.dubbo.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class HelloAction {
    @Resource
    private HelloService demoService;
    @ResponseBody
    @RequestMapping("/hello")
    public String helloAction(){
        String sayHello = demoService.sayHello("qqqqq");
        return sayHello;
    }
}
