package com.course.testng.groups;

import org.testng.annotations.Test;

/**
 * 类分组测试中的testGroup1类
 */
@Test(groups = "testGroup1")
public class GroupsOnClass1 {
    public void testCase1(){
        System.out.println("testGroup1组中的testCase1在GroupsOnClass1上运行");
    }

    public void testCase2(){
        System.out.println("testGroup1组中的testCase2在GroupsOnClass1上运行");
    }
}
