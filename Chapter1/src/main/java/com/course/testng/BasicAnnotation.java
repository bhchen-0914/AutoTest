package com.course.testng;

import org.testng.annotations.Test;

/*
基本注解
 */
public class BasicAnnotation {
    //最基本注解，用来把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
    }
}
