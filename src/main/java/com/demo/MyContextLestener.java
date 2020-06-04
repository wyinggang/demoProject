package com.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyContextLestener implements ApplicationListener<MyContextEvent> {
    @Override
    public void onApplicationEvent(MyContextEvent myContextEvent) {
        System.out.println("listener this MyContextEvent....");
    }
}
