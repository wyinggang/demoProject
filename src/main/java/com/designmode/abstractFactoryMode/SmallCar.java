package com.designmode.abstractFactoryMode;

public class SmallCar extends Car{
    @Override
    public void create() {
        System.out.println("创建小车");
    }
}
