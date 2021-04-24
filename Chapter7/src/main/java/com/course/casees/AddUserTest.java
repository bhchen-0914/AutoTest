package com.course.casees;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue",description = "新增信息测试")
    public void addUserTest() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtils.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);

        //请求连接，获取结果，并添加数据
        String result = getResult(addUserCase);
        Thread.sleep(2000);
        //验证返回结果
        User user = session.selectOne("addUser",addUserCase);
        Assert.assertEquals(result,addUserCase.getExpected());
    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject params = new JSONObject();
        //设置post参数
        params.put("id",addUserCase.getUserId());
        params.put("userName",addUserCase.getUserName());
        params.put("password",addUserCase.getPassword());
        params.put("age",addUserCase.getAge());
        params.put("sex",addUserCase.getSex());
        params.put("permission",addUserCase.getPermission());
        params.put("isDelete",addUserCase.getIsDelete());
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        post.setEntity(entity);
        //设置头信息
        post.setHeader("content-type","application/json");
        //设置cookies
        System.out.println(TestConfig.store);
        TestConfig.client = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        //请求连接
        HttpResponse response = TestConfig.client.execute(post);
        return EntityUtils.toString(response.getEntity());
    }
}
