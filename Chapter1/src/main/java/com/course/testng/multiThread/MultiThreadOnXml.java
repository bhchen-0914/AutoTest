package com.course.testng.multiThread;

import org.testng.annotations.Test;

/**
 * 通过xml文件实现多线程
 */
public class MultiThreadOnXml {

    @Test
    public void test1(){
        System.out.println("this is test1");
        System.out.printf("Test1 thread Id:%s%n",Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.println("this is test2");
        System.out.printf("Test2 thread Id:%s%n",Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.println("this is test3");
        System.out.printf("Test3 thread Id:%s%n",Thread.currentThread().getId());
    }

    @Test
    public void test4(){
        System.out.println("this is test4");
        System.out.printf("Test4 thread Id:%s%n",Thread.currentThread().getId());
    }
}
