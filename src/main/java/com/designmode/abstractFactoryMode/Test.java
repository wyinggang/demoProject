package com.designmode.abstractFactoryMode;

public class Test {

    public static void main(String[] args) {
        BigFactory bigFactory = new BigFactory();
        Car car = bigFactory.getCar();
        Plane plane = bigFactory.getPlane();
        car.create();
        plane.create();
    }

}
