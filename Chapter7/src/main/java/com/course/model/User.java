package com.course.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private int age;
    private String sex;
    private int permission;
    private int isDelete;

    //重写toString方法，使返回数据为json格式字符串
    @Override
    public String toString() {
        return (
                "{id:"+id+","+
                "userName:"+userName+","+
                "password:"+password+","+
                "age:"+age+","+
                "sex:"+sex+","+
                "permission:"+permission+","+
                "isDelete:"+isDelete+"}");
    }
}
