package com.phl.designmodel.factory.simplefacotry;

/**
 * Created by panhongliang on 16/1/11.
 */
public class Client {
    public static void main(String[] args) {
        Car car=Driver.driverCar("Bmw");
        car.drive();
    }
}
