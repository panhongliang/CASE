package com.phl.designmodel.factory.factorymethod;

import com.phl.designmodel.factory.simplefacotry.Benz;
import com.phl.designmodel.factory.simplefacotry.Car;

/**
 * Created by panhongliang on 16/1/11.
 */
public class BenzDriver implements Driver {
    public Car driverCar() {
        return new Benz();
    }
}
