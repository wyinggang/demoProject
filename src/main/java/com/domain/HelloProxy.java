package com.domain;

import com.service.HelloInterface;

public class HelloProxy implements HelloInterface {
    private Hello hello = new Hello();
    @Override
    public void sayHello() {
        System.out.println("before invoke sayHello !");
        hello.sayHello();
        System.out.println("after invoke sayHello !");
    }
}
