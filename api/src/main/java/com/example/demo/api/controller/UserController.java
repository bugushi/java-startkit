package com.example.demo.api.controller;

import com.example.demo.api.enums.StatusEnum;
import com.example.demo.api.exception.ApiException;
import com.example.demo.common.page.PagedResult;
import com.example.demo.user.bo.dto.RegisterDTO;
import com.example.demo.user.exception.UserException;
import com.example.demo.user.service.UserService;
import com.example.demo.api.vo.UserVO;
import com.example.demo.api.vo.query.RegisterQuery;
import com.example.demo.api.vo.query.UserQuery;
import com.example.demo.api.vo.result.Result;
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

@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

//    @ApiOperation("查询所有用户")
//    @GetMapping("/all")
//    public Result<PagedResult<UserVO>> getAll(UserQuery userQuery) {
////        try {
////            PagedResult pagedResult = userService.getAllUsers(userQuery);
////            return new Result(pagedResult);
////        } catch (Exception e) {
////            logger.error("UserController.getAllUsers error..", e);
////            throw new ApiException(StatusEnum.FAIL);
////        }
//        return new Result<>();
//    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@Valid RegisterQuery registerQuery) {
        try {
            RegisterDTO registerDTO = new RegisterDTO();
            registerDTO.setIdentityType(Byte.valueOf("1"));
            registerDTO.setIdentifier(registerQuery.getUserName());
            registerDTO.setCertificate(registerQuery.getPassword());
            boolean result = userService.register(registerDTO);
            if(result) {
                return new Result(StatusEnum.SUCCESS);
            } else {
                return new Result(StatusEnum.FAIL);
            }
        } catch (UserException e) {
            throw new ApiException(StatusEnum.FAIL, e.getMessage());
        }

    }
}
