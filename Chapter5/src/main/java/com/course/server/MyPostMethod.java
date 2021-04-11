package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * post请求接口方法
 */

@RestController
@Api("/")
@RequestMapping("/post")
public class MyPostMethod {

    //存放cookies信息
    private static List<Cookie> cookies = new ArrayList<>();
    /**
     * 场景：登陆成功获取到cookies，再访问其他接口获取列表
     */

    /**
     * 返回cookies信息的接口
     * @param response 响应体
     * @param username 用户名参数
     * @param password 密码参数
     * @return 返回网页提示信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口，可获取并返回cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value="username",required = true) String username,
                        @RequestParam(value="password",required = true) String password){

        //删除此接口返回的历史cookies
        Iterator<Cookie> iterator = cookies.iterator();
        while (iterator.hasNext()){
            Cookie cookie=iterator.next();
            if (cookie.getName().equals("login")){
                iterator.remove();
            }else if(cookie.getName().equals("responseCode") && cookie.getValue().equals("200")){
                iterator.remove();
            }
        }
        if (username.isEmpty()||password.isEmpty()){
            return "用户名或密码不能为空";
        }else if (username.equals("admin") && password.equals("123456")){
            cookies.add(new Cookie("login","success"));
            cookies.add(new Cookie("responseCode","200"));
            //为响应体添加要返回的cookies信息
            for (Cookie cookie:cookies){
                response.addCookie(cookie);
            }
            return "登录成功";
        }
        return "用户名或密码错误";
    }

    /**
     * 携带cookies信息的post请求接口
     * @param request 请求体
     * @param user 用户参数类
     * @return 返回提示信息或用户信息
     */
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request, @RequestBody User user){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "cookies信息为空";
        }
        if (cookies.length==2){
            for (int i=0;i<cookies.length;i++){
                if (cookies[i].getName().equals("login")){
                    if(cookies[i].getValue().equals("success")
                            && cookies[i+1].getName().equals("responseCode")
                            && cookies[i+1].getValue().equals("200")){
                        user.setName("吴彦祖");
                        user.setAge(22);
                        user.setSex("男");
                        return user.toString();
                    }else {return "错误的cookies信息:"+"\n"+cookies[0].getName()+":"+cookies[0].getValue()+"\n"
                            + cookies[1].getName()+":"+cookies[1].getValue();}
                }else if (cookies[i].getName().equals("responseCode")){
                    if (cookies[i].getValue().equals("200")
                            && cookies[i+1].getName().equals("login")
                            && cookies[i+1].getValue().equals("success")){
                        user.setName("吴彦祖");
                        user.setAge(22);
                        user.setSex("男");
                        return user.toString();
                    }else {return "错误的cookies信息:"+"\n"+cookies[0].getName()+":"+cookies[0].getValue()+"\n"
                            + cookies[1].getName()+":"+cookies[1].getValue();}
                }
            }
            return "错误的cookies信息:"+"\n"+cookies[0].getName()+":"+cookies[0].getValue()+"\n"
                    + cookies[1].getName()+":"+cookies[1].getValue();
        }
        return "cookies信息错误";
    }
}
