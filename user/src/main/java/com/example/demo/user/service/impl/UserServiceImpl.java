package com.example.demo.user.service.impl;

import com.example.demo.common.page.PagedResult;
import com.example.demo.user.bo.dto.RegisterDTO;
import com.example.demo.user.bo.dto.UserDTO;
import com.example.demo.user.dao.UserAuthMapper;
import com.example.demo.user.dao.UserMapper;
import com.example.demo.user.entity.UserAuthEntity;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.enums.ErrorCode;
import com.example.demo.user.exception.UserException;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.UUID;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserAuthMapper userAuthMapper;

    @Override
    @Transactional(readOnly = true, timeout = 1)
    public boolean register(RegisterDTO registerDTO) {
        // 验证用户是否存在
        boolean isUserAuthExist = checkUserAuthExist(registerDTO.getIdentityType(), registerDTO.getIdentifier());
        if(isUserAuthExist) {
            throw new UserException(ErrorCode.USER_ALREADY_EXIST);
        }

        // 事务：新增用户、新增用户授权信息
        UserEntity userEntity = new UserEntity();
        userEntity.setNick(registerDTO.getIdentifier());
        userMapper.insert(userEntity);



        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.setUserId(userEntity.getId());
        userAuthEntity.setIdentityType(registerDTO.getIdentityType());
        userAuthEntity.setIdentifier(registerDTO.getIdentifier());
        String certificate = registerDTO.getCertificate();
        // 如果使用用户名密码方式注册
        if(registerDTO.getIdentityType() == 1) {
            String salt = UUID.randomUUID().toString();
            certificate = DigestUtils.md5DigestAsHex((registerDTO.getCertificate() + salt).getBytes());
        }
        userAuthEntity.setCertificate(certificate);
        userAuthMapper.insert(userAuthEntity);

        return true;
    }

    @Override
    public boolean login(RegisterDTO registerDTO) {
        return false;
    }

    @Override
    public boolean refreshToken(String userId, String token) {
        return false;
    }

    @Override
    public PagedResult<UserEntity> getAllUsers(UserDTO userDTO, int pageNo, int pageSize) {
        return null;
    }

    /**
     * 查询用户是否已存在
     * @param identityType
     * @param identifier
     * @return
     */
    private boolean checkUserAuthExist(Byte identityType, String identifier) {
        UserAuthEntity userAuthEntity = userAuthMapper.selectByAuthInfo(identityType, identifier);
        return userAuthEntity != null;
    }
}
