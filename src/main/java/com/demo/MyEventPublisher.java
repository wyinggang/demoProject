package com.demo;

import org.apache.catalina.core.StandardContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublisher  {

    @Autowired
    ApplicationContext applicationContext;

    public void  publish(){
        applicationContext.publishEvent(new MyContextEvent(new StandardContext()));
    }

}
