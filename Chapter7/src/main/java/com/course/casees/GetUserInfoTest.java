package com.course.casees;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtils;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取id为1的用户信息")
    public void getUserInfo() throws IOException {
        SqlSession session = DatabaseUtils.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        //发送请求
        JSONArray result = getResult(getUserInfoCase);
        JSONArray resultJson = new JSONArray(result.getString(0));
        //获取数据库查询结果
        User user = session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        JSONArray exceptJson = new JSONArray(userList);
        Assert.assertEquals(resultJson.toString(),exceptJson.toString());
        System.out.println(resultJson.toString());
    }

    private JSONArray getResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",getUserInfoCase.getUserId());
        post.setEntity(new StringEntity(param.toString(),"utf-8"));
        post.setHeader("content-type","application/json");
        TestConfig.client = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        HttpResponse response = TestConfig.client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
//        List数据类型转换
        List resultList = Arrays.asList(result);
        return new JSONArray(resultList);
    }
}
