package com.demo;

import org.apache.catalina.core.StandardContext;
import org.springframework.context.ApplicationEvent;

public class MyContextEvent extends ApplicationEvent {

    public MyContextEvent(StandardContext context) {
        super(context);
        System.out.println(context);
    }
}
