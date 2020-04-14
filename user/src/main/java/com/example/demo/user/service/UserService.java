package com.example.demo.user.service;

import com.example.demo.common.page.PagedResult;
import com.example.demo.user.bo.dto.RegisterDTO;
import com.example.demo.user.bo.dto.UserDTO;
import com.example.demo.user.entity.UserEntity;


public interface UserService {
    /**
     * 注册用户
     * @param registerDTO
     * @return
     */
    boolean register(RegisterDTO registerDTO);

    /**
     * 登录
     * @param registerDTO
     * @return
     */
    boolean login(RegisterDTO registerDTO);

    /**
     * 更新token
     * @param userId
     * @param token
     * @return
     */
    boolean refreshToken(String userId, String token);

    /**
     * 查询所有用户
     * @param userDTO
     * @param pageNo
     * @param pageSize
     * @return
     */
    PagedResult<UserEntity> getAllUsers(UserDTO userDTO, int pageNo, int pageSize);
}
