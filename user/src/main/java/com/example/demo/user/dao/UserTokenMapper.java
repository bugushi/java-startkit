package com.example.demo.user.dao;

import com.example.demo.user.entity.UserTokenEntity;
import java.util.List;

public interface UserTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTokenEntity record);

    UserTokenEntity selectByPrimaryKey(Integer id);

    List<UserTokenEntity> selectAll();

    int updateByPrimaryKey(UserTokenEntity record);
}