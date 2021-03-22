package com.course.httpClient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

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
        client.close();
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostWithCookies() throws IOException{
        String result;
        //拼接测试链接
        String testUrl = this.url + this.bundle.getString("test.post.withCookies");
        HttpPost post = new HttpPost(testUrl);
        //设置参数
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("name","bhchen3");
        jsonParam.put("age","20");
        //设置header 请求头信息
        post.setHeader("content-type","application/json");
        //post请求添加参数
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
        post.setEntity(entity);
        //创建httpclient实例并设置cookies
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(this.store).build();
        HttpResponse response = client.execute(post);
        int statusCode = response.getStatusLine().getStatusCode();
        //保留响应信息
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONObject jsonResult = new JSONObject(result);
        String param1Result = jsonResult.get("bhchen3").toString();
        String param2Result = jsonResult.get("status").toString();
        //断言
        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(param1Result,"success");
        Assert.assertEquals(param2Result,"1");
    }

}
