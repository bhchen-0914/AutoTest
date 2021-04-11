package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * 数据库工具类
 */
public class DatabaseUtils {

    public static SqlSession getSqlSession() throws IOException {
        //获取配置的资源文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        // 使用类加载器加载出配置文件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        //sqlSession为能够执行配置文件中的sql语句对象
        SqlSession sqlSession = factory.openSession();
        return sqlSession;
    }
}
