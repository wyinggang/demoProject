package com.designmode.abstractFactoryMode;

public class BigCar extends Car{
    @Override
    public void create() {
        System.out.println("创建大车");
    }
}
