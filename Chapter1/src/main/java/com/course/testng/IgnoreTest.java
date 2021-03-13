package com.course.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 忽略测试
 */
public class IgnoreTest {

    @Test
    public void ignore1(){
        System.out.println("执行ignore1！");
    }

    //当enable属性为false时，用例将会忽略,此属性默认为true
    @Test(enabled = false)
    public void ignore2(){
        System.out.println("执行ignore2");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("这是在测试方法之前运行的beforeMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("这是测试方法值后运行的afterMethod");
    }
}
