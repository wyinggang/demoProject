package com.designmode.abstractFactoryMode;

public class BigFactory implements Factory{
    @Override
    public Car getCar() {
        return new BigCar();
    }

    @Override
    public Plane getPlane() {
        return new BigPlane();
    }
}
