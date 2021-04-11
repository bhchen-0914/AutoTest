package com.course.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController  //可被ComponentScan扫描的类
@Api("/")
public class MyGetMethod {

    /**
     * 要求服务端返回cookies信息
     * @return
     */
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "获取cookies接口，可返回cookies信息", httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServletRequest 装请求信息的类
        //HttpServletResponse 装响应信息的类
        Cookie cookie1 = new Cookie("login","false");
        Cookie cookie2 = new Cookie("status","success");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        return "获取cookies成功";
    }

    /**
     * 要求客户端携带cookies信息访问
     * @param request 请求信息
     * @return
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带cookies信息才能访问的接口", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies))
            return "访问失败,没有接收到cookies请求";
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login") && cookie.getValue().equals("success")){
                return "这是一个需要携带cookies信息才能访问的get请求";
            }
        }
        return "cookies 信息不正确";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url:key=value&key=value
     * 模拟获取购物车商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数访问的接口", httpMethod = "GET")
    public Map getList(@RequestParam String username,
                       @RequestParam String password){
        Map<String,Double> shopList = new HashMap<>();
        Map<String,String> failCode = new HashMap<>() ;
        if (username.equals("admin")&& password.equals("123456")){
            shopList.put("iphone12",5288.88);
            shopList.put("PS5",4899.9);
            shopList.put("茅台",2888.48);
            return shopList;
        }
        failCode.put("error","-1");
        failCode.put("message","用户名或密码错误");
        return failCode;
    }

    /**
     * 第二种携带参数访问get请求
     * url:ip:port/get/with/param
     */
    @RequestMapping("/get/with/param/{username}/{password}")
    @ApiOperation(value = "需要携带参数访问的接口(另一种写法)", httpMethod = "GET")
    public Map myGetList(@PathVariable String username,
                         @PathVariable String password){
        Map<String,Double> shopList = new HashMap<>();
        Map<String,String> failCode = new HashMap<>() ;
        if (username.equals("admin")&& password.equals("123456")){
            shopList.put("iphone12",5288.88);
            shopList.put("PS5",4899.9);
            shopList.put("茅台",2888.48);
            return shopList;
        }
        failCode.put("error","-1");
        failCode.put("message","用户名或密码错误");
        return failCode;
    }

}
