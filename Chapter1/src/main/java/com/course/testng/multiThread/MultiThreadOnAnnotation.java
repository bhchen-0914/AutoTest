package com.course.testng.multiThread;

import org.testng.annotations.Test;

/**
 * 注解的方式实现多线程测试
 */
public class MultiThreadOnAnnotation {

    /**
     * invocationCount用来指用来设置用例被重复调用的次数，默认参数为1
     * threadPoolSize用来指定线程池，这里线程池设置为3
     */
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void test(){
        System.out.println("this is MultiThread");
        System.out.printf("Test thread Id: %s%n",Thread.currentThread().getId());
    }
}
