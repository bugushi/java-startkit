package com.example.demo.user.dao;

import com.example.demo.user.entity.UserEntity;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    List<UserEntity> selectAll();

    int updateByPrimaryKey(UserEntity record);
}