package com.example.demo.user.controller;

import com.example.demo.user.enums.ApplicationStatus;
import com.example.demo.user.exception.ApplicationException;
import com.example.demo.user.service.UserService;
import com.example.demo.user.vo.UserVO;
import com.example.demo.user.vo.query.RegisterQuery;
import com.example.demo.user.vo.query.UserQuery;
import com.example.demo.user.vo.result.PagedResult;
import com.example.demo.user.vo.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/all")
    public Result<PagedResult<UserVO>> getAll(UserQuery userQuery) {
        try {
            PagedResult pagedResult = userService.getAllUsers(userQuery);
            return new Result(pagedResult);
        } catch (Exception e) {
            logger.error("UserController.getAllUsers error..", e);
            throw new ApplicationException(ApplicationStatus.FAIL);
        }

    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@Valid RegisterQuery registerQuery) {
        boolean result = userService.register(registerQuery);
        if(result) {
            return new Result(ApplicationStatus.SUCCESS);
        } else {
            return new Result(ApplicationStatus.FAIL);
        }
    }
}
