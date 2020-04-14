package com.example.demo.user.dao;

import com.example.demo.user.entity.RoleEntity;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleEntity record);

    RoleEntity selectByPrimaryKey(Integer id);

    List<RoleEntity> selectAll();

    int updateByPrimaryKey(RoleEntity record);
}