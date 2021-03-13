package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * 组测试中的方法组测试
 * 可使方法用例以组的形式分类执行
 */
public class GroupsOnMethod {

    // group属性设置分组
    @Test(groups = "server")
    public void serverTestCase1(){
        System.out.println("这是服务端组的测试方法1");
    }

    @Test(groups = "server")
    public void serverTestCase2(){
        System.out.println("这是服务端组的测试方法2");
    }

    @Test(groups = "client")
    public void clientTestCase1(){
        System.out.println("这是客户端组的测试方法1");
    }

    @Test(groups = "client")
    public void clientTestCase2(){
        System.out.println("这是客户端组的测试方法2");
    }

    //该组中方法运行前执行
    @BeforeGroups("server")
    public void beforeGroupsOnServer(){
        System.out.println("服务端组方法运行前所运行的方法beforeGroupsOnServe");
    }

    //该组中方法运行后执行
    @AfterGroups("server")
    public void afterGroupsOnServer(){
        System.out.println("服务端组方法运行后所运行的方法afterGroupsOnServe");
    }

    @BeforeGroups("client")
    public void beforeGroupsOnClient(){
        System.out.println("服务端组方法运行前所运行的方法beforeGroupsOnClient");
    }

    @AfterGroups("client")
    public void afterGroupsOnClient(){
        System.out.println("服务端组方法运行后所运行的方法afterGroupsOnClient");
    }
}
