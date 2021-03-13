package com.course.testng.suite;

import org.testng.annotations.Test;

/**
 * 登录测试类
 */
public class LoginTest {

    @Test
    public void loginTaoBao(){
        System.out.println("淘宝登录成功");
    }

    @Test
    public void loginZhiFuBao(){
        System.out.println("支付宝支付成功");
    }
}
