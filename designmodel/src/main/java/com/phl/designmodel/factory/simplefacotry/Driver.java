package com.phl.designmodel.factory.simplefacotry;


/**
 * Created by panhongliang on 16/1/11.
 * 工作类角色
 * 工厂方法：工厂提供的工厂方法 提供了所有的实现（干了所有事）
 * 当有新的类需要工厂方法提供时，需要修改工作方法代码。违背了开闭原则
 */
public class Driver {
    public static  Car driverCar(String car){
        if("Benz".equals(car)){
            return new Benz();
        }
        if("Bmw".equals(car)){
            return new Bmw();
        }
        throw new  UnsupportedOperationException();
    }
}
