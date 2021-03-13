package com.course.testng;

import org.testng.annotations.*;

/**
基本注解
 */
public class BasicAnnotation {
    //最基本注解，用来把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
    }

    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
    }

    //在每个测试用例执行前运行
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("这是在测试方法之前运行的beforeMethod");
    }

    //在每个测试用例执行后运行
    @AfterMethod
    public void afterMethod(){
        System.out.println("这是测试方法值后运行的afterMethod");
    }

    //在本测试类运行之前运行
    @BeforeClass
    public void beforeClassDemo(){
        System.out.println("这是在BasicAnnotation测试类运行前执行的beforeClassDemo方法");
    }

    //在本测试类运行之后运行
    @AfterClass
    public void afterClassDemo(){
        System.out.println("这是在BasicAnnotation测试类运行后执行的AfterClassDemo方法");
    }

    //整个套件测试进行前运行，优先级高于类
    @BeforeSuite
    public void beforeSuitDemo(){

        System.out.println("测试套件前运行的beforeSuitDemo方法");
    }

    //整个套件测试完成后运行，优先级高于类
    @AfterSuite
    public void afterSuitDemo(){
        System.out.println("测试套件后运行的afterSuitDemo方法");
    }
}
