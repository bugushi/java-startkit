package com.example.demo.user.dao;

import com.example.demo.user.entity.UserRoleEntity;
import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleEntity record);

    UserRoleEntity selectByPrimaryKey(Integer id);

    List<UserRoleEntity> selectAll();

    int updateByPrimaryKey(UserRoleEntity record);
}