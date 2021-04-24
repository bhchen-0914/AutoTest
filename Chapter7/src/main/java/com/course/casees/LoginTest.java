package com.course.casees;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * 登录类测试
 */
public class LoginTest {

    @BeforeTest(groups = "loginTrue", description = "测试前准备工作，获取HttpClient")
    public void beforeTest(){
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
    }

    @Test(groups = "loginTrue",description = "登录成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtils.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //发送请求
        String result = getResult(loginCase);
        Assert.assertEquals(loginCase.getExpected(),result);
        System.out.println("Excepted:"+loginCase.getExpected());

    }

    @Test(groups = "loginFalse",description = "登录失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtils.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //发送请求
        String result = getResult(loginCase);
        Assert.assertEquals(loginCase.getExpected(),result);
        System.out.println("Excepted:"+loginCase.getExpected());
    }

    /**
     *调用接口获取结果
     * @param loginCase 登录用例
     * @return 返回接口结果
     */
    private String getResult(LoginCase loginCase) throws IOException {
        TestConfig.client = HttpClients.createDefault();
        TestConfig.context = HttpClientContext.create();
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        // 设置请求头
        post.setHeader("content-type","application/json");
        JSONObject param = new JSONObject();
        //设置参数
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //返回结果
        TestConfig.client = HttpClients.createDefault();
        HttpResponse response = TestConfig.client.execute(post,TestConfig.context);
        String result = EntityUtils.toString(response.getEntity());
        //返回cookies
        boolean flag = result.equals("true");
        if (flag){
            TestConfig.store = TestConfig.context.getCookieStore();
            System.out.println(TestConfig.store);
        }
        return result;
    }
}

