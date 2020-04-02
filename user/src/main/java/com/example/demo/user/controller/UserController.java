package com.example.demo.user.controller;

import com.example.demo.user.dao.UserMapper;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/users")
    public List<UserEntity> getAll(UserVO userVO) {
        return userMapper.selectAll(userVO);
    }

    @GetMapping("/addUser")
    public List<UserEntity> addUser(UserEntity userEntity) {
        userMapper.insert(userEntity);
        return userMapper.selectAll(null);
    }
}
