package com.phl.reference.passive;

/**
 * 非主动引用:通过数组来引用，不会触发类的初始化
 *
 * 这里没有输出，说明没有触发类com.phl.reference.passive.SuperClass的初始化 ;
 * 但是这段代码触发另外一个类名为 [com.phl.reference.passive.SuperClass 的初始化，
 * 对于用户来说，这并不是一个合法的类名称，这是由虚拟机自动生成的。
 */
public class NotInitialization2 {
    public static void main(String[] args) throws ClassNotFoundException {
       SuperClass [] scs=new SuperClass[10];
        System.out.println( System.getProperty("java.class.path"));
    }
}
