package com.course.testng.suite;

import org.testng.annotations.*;

/**
 * 测试套件的配置类
 */
public class SuiteConfig {
    @BeforeSuite
    public void beforeSuiteTest(){
        System.out.println("准备开始套件测试");
    }

    @AfterSuite
    public void AfterSuiteTest(){
        System.out.println("套件测试结束");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("用用例执行前的BeforeTest方法");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("用例执行后的AfterTest方法");
    }
}
