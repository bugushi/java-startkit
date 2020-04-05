package com.example.demo.user.service;

import com.example.demo.user.dao.UserMapper;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.vo.UserVO;
import com.example.demo.user.vo.query.RegisterQuery;
import com.example.demo.user.vo.query.UserQuery;
import com.example.demo.user.vo.page.PagedResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 用户注册
     * @param registerQuery
     * @return
     */
    public boolean register(RegisterQuery registerQuery) {
        // 生成密码的随机盐
        String salt = UUID.randomUUID().toString();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(registerQuery.getUserName());
        // 密码加盐后在md5
        userEntity.setPassword(DigestUtils.md5DigestAsHex((registerQuery.getPassword() + salt).getBytes()));
        userEntity.setSalt(salt);
        return 1 == userMapper.insert(userEntity);
    }

    /**
     * 查询所有用户
     * @param userQuery
     * @return
     */
    public PagedResult<UserVO> getAllUsers(UserQuery userQuery) {
        List<UserEntity> userEntityList = userMapper.selectAll(userQuery);

        // bean转换
        List<UserVO> userVOList = userEntityList.stream().map(userEntity -> {
            UserVO tempUserVO = new UserVO();
            BeanUtils.copyProperties(userEntity, tempUserVO);
            return tempUserVO;
        }).collect(Collectors.toList());

        long count = userMapper.selectCount(userQuery);

        // 分页
        PagedResult<UserVO> pagedResult = new PagedResult<>();
        pagedResult.setPageNo(userQuery.getPageNo());
        pagedResult.setPageSize(userQuery.getPageSize());
        pagedResult.setResults(userVOList);
        pagedResult.setTotal(count);

        return pagedResult;
    }
}
