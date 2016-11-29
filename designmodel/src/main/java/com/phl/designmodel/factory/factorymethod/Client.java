package com.phl.designmodel.factory.factorymethod;

import com.phl.designmodel.factory.simplefacotry.Car;

/**
 * Created by panhongliang on 16/1/11.
 */
public class Client {
    public static void main(String[] args) {
        Driver driver=new BenzDriver();
        Car car=driver.driverCar();
        car.drive();
    }
}
