package com.example.demo.user.dao;

import com.example.demo.user.entity.UserAuthEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuthEntity record);

    UserAuthEntity selectByPrimaryKey(Integer id);

    List<UserAuthEntity> selectAll();

    int updateByPrimaryKey(UserAuthEntity record);

    UserAuthEntity selectByAuthInfo(@Param("identityType") Byte identityType, @Param("identifier") String identifier);
}