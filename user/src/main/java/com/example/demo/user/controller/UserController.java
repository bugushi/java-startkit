package com.example.demo.user.controller;

import com.example.demo.user.service.UserService;
import com.example.demo.user.vo.UserVO;
import com.example.demo.user.vo.query.RegisterQuery;
import com.example.demo.user.vo.query.UserQuery;
import com.example.demo.user.vo.page.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/all")
    public PagedResult<UserVO> getAll(UserQuery userQuery) {
        return userService.getAllUsers(userQuery);
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public String register(@Valid RegisterQuery registerQuery) {
        boolean result = userService.register(registerQuery);
        return result ? "注册成功" : "注册失败";
    }
}
