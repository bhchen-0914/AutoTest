package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * DataProvider参数化
 * 通过方法与数组传参
 */
public class testDataProvider {
    /**
     *为测试用例提供参数
     * @return 返回包含参数的二位数组
     */
    @DataProvider(name = "data")
    public Object[][] providerData(){
        Object[][] objectArray;
        objectArray = new Object[][]{
                {"Jack",10},
                {"Tom",18},
                {"Mary",22}};
        return objectArray;
    }

    //通过dataProvider的name属性完成数据关联
    @Test(dataProvider = "data")
    public void dataProviderTest1(String name,int age){
        String caseName = "dataProviderTest1";
        System.out.println(caseName + " name:" + name + ",age:" + age);
    }

    @Test(dataProvider = "data")
    public void dataProviderTest2(String name,int age){
        String caseName = "dataProviderTest2";
        System.out.println(caseName + " name:" + name + ",age:" + age);
    }

    /**
     *通过 @DataProvider注解的name属性关联参数
     * @param method 通过method的方法名，指定传入参数的内容
     * @return 返回参数内容
     */
    @DataProvider(name = "type")
    public Object[][] providerDataByName(Method method){
        Object[][] result = null;
        if (method.getName().equals("stuTest")){
            result = new Object[][]{
                    {"Tom",10},
                    {"Lily",12}
            };
        }else if (method.getName().equals("petTest")){
            result = new Object[][]{
                    {"coffee","cat"},
                    {"miki","mouse"}
            };
        }
        return result;
    }

    //选择传入stu的数据
    @Test(dataProvider = "type")
    public void stuTest(String name, int age){
        String caseName = "stuTest ";
        System.out.println(caseName + " name:" + name + ",age:" + age);
    }

    //选择传入pet的数据
    @Test(dataProvider = "type")
    public void petTest(String name, String type){
        String caseName = "petTest ";
        System.out.println(caseName + " name:" + name + ",type:" + type);
    }

}
