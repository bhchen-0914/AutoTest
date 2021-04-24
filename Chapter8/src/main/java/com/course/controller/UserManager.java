package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value = "v1")
@RequestMapping("/v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate template;

    /**
     * 登录接口
     * @param response 响应信息
     * @param user  用户参数
     * @return 是否登录成功
     */
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        int i=0;
        //查询数据库中是否有此用户，查询到返回查询条数1
        i = template.selectOne("login",user);
        if (i==1){
            Cookie cookie = new Cookie("login","true");
            response.addCookie(cookie);
            log.info("登录的用户是："+user.getUserName());
            log.info("cookies返回成功"+cookie.toString());
            return true;
        }
        return false;
    }

    /**
     * 添加用户接口
     * @param request 请求信息
     * @param user  用户信息
     * @return 是否添加成功
     */
    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request,@RequestBody User user){
        Boolean flag = verifyCookies(request);
        int result = 0;
        if (flag){
            result = template.insert("addUser",user);
        }
        if (result>0){
            log.info("添加用户数量:"+result);
            return true;
        }
        return false;
    }

    /**
     * 获取用户列表接口
     * @param request 请求信息
     * @param user  用户信息
     * @return  查询到的用户列表
     */
    @ApiOperation(value = "获取用户列表接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserList(HttpServletRequest request,@RequestBody User user){
        Boolean flag = verifyCookies(request);
        if (flag){
            List<User> users = template.selectList("getUserInfo",user);
            log.info("getUserInfo获取到用户数量为："+users.size());
            return users;
        }
        return null;
    }

    /**
     * 更新用户信息接口
     * @param request 请求信息
     * @param user 用户信息
     * @return 返回更新条目数
     */
    @ApiOperation(value = "更新用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean flag = verifyCookies(request);
        int result = 0;
        if (flag){
            result = template.update("updateUserInfo",user);
        }
        log.info("更新数据条目:"+request);
        return result;
    }

    /**
     * 验证cookies的方法
     * @param request 请求信息
     * @return 返回验证结果
     */
    private Boolean verifyCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            log.info("cookies信息为空");
            return false;
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                log.info("cookies校验通过");
                return true;
            }
        }
        return false;
    }

}
