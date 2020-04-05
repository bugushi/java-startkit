package com.example.demo.user.dao;

import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.vo.query.UserQuery;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    List<UserEntity> selectAll(UserQuery userQuery);

    int updateByPrimaryKey(UserEntity record);

    long selectCount(UserQuery userQuery);
}