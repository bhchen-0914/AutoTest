package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 工具类方法与类成员设计为静态，因为配置文件内容只用加载一次
 * 配置文件工具类，用于返回接口url
 */
public class ConfigFile {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    /**
     * 获取接口路径
     * @param interfaceName 传入枚举类中的接口名
     * @return 返回接口url
     */
    public static String getUrl(InterfaceName interfaceName){
        String hostAddress = bundle.getString("test.url");
        String uri = "";
        switch (interfaceName){
            case GETUSERLIST:
                uri = bundle.getString("getUserList.uri");
                break;
            case LOGIN:
                uri = bundle.getString("login.uri");
                break;
            case UPDATEUSERINFO:
                uri = bundle.getString("updateUserInfo.uri");
                break;
            case GETUSERINFO:
                uri = bundle.getString("getUserInfo.uri");
                break;
            case ADDUSERINFO:
                uri = bundle.getString("addUser.uri");
                break;
        }
        //最终测试地址
        return hostAddress + uri;
    }

}

