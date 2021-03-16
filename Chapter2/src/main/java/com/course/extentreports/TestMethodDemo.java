package com.course.extentreports;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestMethodDemo {

    @Test
    public void testCase1(){
        Assert.assertEquals(99,100);
    }

    @Test
    public void testCase2(){
        Assert.assertEquals(100,100);
    }

    //可打印日志
    @Test
    public void testCase3() {
        Reporter.log("自定义的日志");
        throw new RuntimeException("自定义的异常");
    }

    @Test
    public void testCase4(){
        Assert.assertEquals("hello","hello");
    }
}
