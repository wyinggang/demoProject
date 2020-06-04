package com.domain;

import net.sf.cglib.proxy.Enhancer;
import com.service.HelloInterface;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProcyMain {
    public static void main(String[] args) {
        //JDK动态代理
        Hello hello = new Hello();
        InvocationHandler helloHandler = new HelloHandler(hello);
        HelloInterface proxyHello= (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), helloHandler);
        proxyHello.sayHello();
        //cglib动态代理
        Enhancer enhancer = new Enhancer();
        CglibIntercepter cglibProxy = new CglibIntercepter();
        //CglibHelloClass cglibHelloClass = (CglibHelloClass) cglibProxy.newProxyInstance(CglibHelloClass.class);
        enhancer.setSuperclass(CglibHelloClass.class);
        enhancer.setCallback(cglibProxy);
        CglibHelloClass cglibHelloClass= (CglibHelloClass) enhancer.create();
        cglibHelloClass.sayHello();
    }
}
