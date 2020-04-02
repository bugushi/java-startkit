package com.example.demo.user.dao;

import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    List<UserEntity> selectAll(UserVO userVO);

    int updateByPrimaryKey(UserEntity record);
}