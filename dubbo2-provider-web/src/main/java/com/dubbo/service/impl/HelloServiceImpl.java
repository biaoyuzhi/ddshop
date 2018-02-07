package com.dubbo.service.impl;

import com.dubbo.service.HelloService;

public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        System.out.println("come from "+name);
        return "Hello "+name;
    }
}
