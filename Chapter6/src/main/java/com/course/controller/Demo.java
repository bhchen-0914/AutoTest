package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "v1")
@RequestMapping("v1")
public class Demo {
    // 首先获取一个执行sql语句的对象
    @Autowired //起动机加载，Demo类启动时，此对象即加载赋值
    private SqlSessionTemplate temple;

    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数", httpMethod = "GET")
    public int getUserCount(){
        return temple.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "新增信息", httpMethod = "POST")
    public int addUser(@RequestBody User user){

        return   temple.insert("addUser",user);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ApiOperation(value = "修改信息", httpMethod = "POST")
    public int updateUser(@RequestBody User user){

        return temple.update("updateUser",user);
    }

    @RequestMapping(value = "/delUser",method=RequestMethod.GET)
    @ApiOperation(value = "根据id删除数据", httpMethod = "GET")
    private int delUser(@RequestParam int id){

        return temple.delete("delUser", id);
    }

}
