package com.course.testng;

import org.testng.annotations.Test;

/**
 * 依赖测试
 * 适用于一条用例需要前置步骤成功的情况下进行
 */
public class DependTest {

    @Test
    public void stepOne(){
        System.out.println("step1 is running");
    }

    //抛出一个异常，此方法不会成功执行
    @Test()
    public void stepTwo(){
        System.out.println("step2 is running");
        throw new RuntimeException();
    }

    //依赖测试，需要stepOne方法成功运行
    @Test(dependsOnMethods = {"stepOne"})
    public void testCase1(){
        System.out.println("testCase1 is running");
    }

    //当前值步骤执行失败，此用例将会被忽略
    @Test(dependsOnMethods = {"stepOne","stepTwo"})
    public void testCase2(){
        System.out.println("testCase2 is running");
    }
}
