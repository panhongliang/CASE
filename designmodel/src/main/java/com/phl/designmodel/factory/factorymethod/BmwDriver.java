package com.phl.designmodel.factory.factorymethod;

import com.phl.designmodel.factory.simplefacotry.Bmw;
import com.phl.designmodel.factory.simplefacotry.Car;

/**
 * Created by panhongliang on 16/1/11.
 */
public class BmwDriver implements Driver {
    public Car driverCar() {
        return new Bmw();
    }
}
