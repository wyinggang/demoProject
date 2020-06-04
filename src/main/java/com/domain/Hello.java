package com.domain;

import com.service.HelloInterface;

public class Hello implements HelloInterface {

    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
