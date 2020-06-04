package com.designmode.abstractFactoryMode;

public class BigPlane extends Plane{
    @Override
    public void create() {
        System.out.println("创建大飞机");
    }
}
