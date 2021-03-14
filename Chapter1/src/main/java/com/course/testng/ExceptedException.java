package com.course.testng;

import org.testng.annotations.Test;

/**
 * 异常测试
 * 当一条用例的预期结果为一个异常的时候
 * 使用异常测试
 */

public class ExceptedException {

    //失败的测试
    @Test(expectedExceptions = ArithmeticException.class)
    public void runArithmeticException1(){
        System.out.println("这是失败的异常测试");
    }

    //抛出一个除数不能是0的异常
    @Test(expectedExceptions = ArithmeticException.class)
    public void runArithmeticException2(){
        System.out.println("这是成功的异常测试");
        throw new ArithmeticException();
    }
}
