package com.designmode.abstractFactoryMode;

public class SmallFactory implements Factory{
    @Override
    public Car getCar() {
        return new SmallCar();
    }

    @Override
    public Plane getPlane() {
        return new SmallPlane();
    }
}
