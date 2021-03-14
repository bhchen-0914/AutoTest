package com.course.testng.groups;

import org.testng.annotations.Test;

/**
 * 类分组测试中的testGroup2类
 */
@Test(groups = "testGroup2")
public class GroupsOnClass2 {
    public void testCase1(){
        System.out.println("testGroup2组中的testCase1在GroupsOnClass2上运行");
    }

    public void testCase2(){
        System.out.println("testGroup2组中的testCase2在GroupsOnClass2上运行");
    }
}
