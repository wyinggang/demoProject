package com.designmode.abstractFactoryMode;

public class SmallPlane extends Plane{
    @Override
    public void create() {
        System.out.println("创建小飞机");
    }
}
