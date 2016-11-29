package com.phl.designmodel.factory.factorymethod;

import com.phl.designmodel.factory.simplefacotry.Car;

/**
 * Created by panhongliang on 16/1/11.
 * 抽象工厂方法
 * 具体的实现由子类实现
 * 当增加新的产品是不需要修改此类
 *
 */
public interface Driver {
    public Car driverCar();
}
