package com.phl.asset;

/**
 * Created by panhongliang on 16/3/31.
 * 如果要开启断言检查，则需要用开关-enableassertions或-ea来开启。
 * assert关键字语法很简单，有两种用法：

 1、assert <boolean表达式>
 如果<boolean表达式>为true，则程序继续执行。
 如果为false，则程序抛出AssertionError，并终止执行。

 2、assert <boolean表达式> : <错误信息表达式>
 如果<boolean表达式>为true，则程序继续执行。
 如果为false，则程序抛出java.lang.AssertionError，并输入<错误信息表达式>。
 */
public class AssetTest {
    public static void main(String[] args) {
        int a=1;
        assert  a>2;
    }
}
