package com.domain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloHandler implements InvocationHandler {
    private Object object;
    public HelloHandler(Object object){
        this.object=object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke "+method.getName());
        method.invoke(object,args);
        System.out.println("after invoke "+method.getName());
        return null;
    }
}
