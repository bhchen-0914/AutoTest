package com.course.testng.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 *xml参数化测试
 * 适用于需要传参的测试场景
 */
public class ParameterTest {

    @Test
    @Parameters({"name","age"})
    public void paramTest1(String name,int age){
        System.out.println("name:" + name + ",age:" + age);
    }
}
