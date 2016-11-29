package com.phl.reference.passive;

/**
 * 常量在编译阶段会存入调用类NotInitialization3的常量池中，本质上没有直接引用定义常量的类ConstantClass，因此不会被触发类的初始化
 * 其实就是在编译阶段ConstantClass.HELLOWORD已经被替换了，替换的值放在NotInitialzaion3的常量池中
 */
public class NotInitialization3 {
    public static void main(String[] args) {
        System.out.println(ConstantClass.HELLOWORD);
        Integer.valueOf(10);
    }
}
