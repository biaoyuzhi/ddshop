package com.dubbo.main;

import com.dubbo.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloMain {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dubbo-consumer.xml");
        context.start();
        HelloService bean = (HelloService) context.getBean("demoService");
        System.out.println(bean.sayHello("wzh"));
    }
}
