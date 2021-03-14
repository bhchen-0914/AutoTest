package com.course.testng;

import org.testng.annotations.Test;

/**
 * 超时测试
 */
public class TimeOutTest {

    //期望在3000 ms内得到结果
    @Test(timeOut = 3000)
    public void testSuccess() throws InterruptedException {
        System.out.println("测试成功");
        Thread.sleep(2000);
    }

    @Test(timeOut = 3000)
    public void testFailed() throws InterruptedException {
        System.out.println("测试失败");
        Thread.sleep(3500);
    }
}
