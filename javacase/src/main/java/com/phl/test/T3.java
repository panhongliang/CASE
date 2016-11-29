package com.phl.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;

/**
 * Created by panhongliang on 15/8/25.
 */
public class T3 implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof  T0){
            T0 to=(T0)bean;
            to.key1="postProcessBeforeInitialization";
            System.out.printf("\npostProcessBeforeInitialization方法");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof  T0){
            T0 to=(T0)bean;
            to.key1="postProcessAfterInitialization";
            System.out.printf("\npostProcessAfterInitialization方法");
        }
        return bean;
    }


    public Class<? extends Annotation> annotationType() {
        return null;
    }
    /**
     * 自定义类的实例化过程
     */
}
