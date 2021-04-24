package com.course.casees;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.utils.DatabaseUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserListTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取性别为女的用户信息")
    public void getUserList() throws IOException {
        SqlSession session = DatabaseUtils.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase","女");
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
    }
}
