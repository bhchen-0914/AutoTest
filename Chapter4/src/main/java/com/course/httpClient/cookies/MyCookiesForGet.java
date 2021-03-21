package com.course.httpClient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> origin/master
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 配置优化方法
 */
public class MyCookiesForGet {
    private ResourceBundle bundle; //用于读取配置文件
    private CookieStore store;
    HttpGet get;
    String url;
    String uri;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA); //配置显示中文
        url = bundle.getString("test.url");
        uri = bundle.getString("getCookies.uri");
        get = new HttpGet(this.url+this.uri);
    }

    /**
     * 获取网页内容
     * @throws IOException
     */
    @Test
    public void testGetResult() throws IOException {
        String result;
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

    /**
     * 获取cookies信息
     */
    @Test
    public void testGetCookies() throws IOException {
        //实例化一个HttpClientContext对象，用于保留请求信息
        HttpClientContext context = HttpClientContext.create();
        CloseableHttpClient client = HttpClients.createDefault();
        //传入context实例，原本作用为：多个请求序列传入相同context实例，可保证请求之间的信息状态的自动传播
        //这里用于获取本次的cookies信息
        client.execute(get,context);
        this.store = context.getCookieStore();
        //返回一个元素为Cookie对象的List列表
        List<Cookie> cookieList = store.getCookies();
        //使用增强for循环遍历cookies信息
        for (Cookie cookie:cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name:"+name + " cookie value:"+value);
        }

    }

    /**
     * 使用获取到的cookies信息建立访问请求
     */
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        String result;
        String testGetWithCookiesUri = this.url + this.bundle.getString("test.get.withCookies");
        HttpGet newGet = new HttpGet(testGetWithCookiesUri);

        //设置cookies信息
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(this.store).build();
        HttpResponse response = client.execute(newGet);

        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200){
            result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        } else {
            System.out.println("错误登录状态 "+ statusCode);
        }

    }

}
