package com.course.httpClient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;


public class MyHttpClient {

    /**
     * 第一个使用httpclient框架返回网页信息的实例
     * @throws IOException
     */
    @Test
    public void testCase1() throws IOException {
        //用来存放结果
        String result;
        //使用get方法
        HttpGet get = new HttpGet("http://www.baidu.com");
        //实例化一个http客户端对象
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse  response = httpClient.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }
}