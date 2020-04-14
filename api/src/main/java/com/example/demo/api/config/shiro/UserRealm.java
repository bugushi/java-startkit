package com.example.demo.api.config.shiro;

import com.example.demo.user.service.AuthService;
import com.example.demo.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {

    @Value("${jwt.token.expire-time}")
    private long expireTime;

    @Value("${jwt.token.secret}")
    private String secret;

    @Autowired
    UserService userService;

    /**
     * 验证用户身份
     * @param authenticationToken  用户身份信息 token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        Integer userId = JWTUtils.getUserIdFromToken(token);
        List<String> roles = JWTUtils.getRolesFromToken(token);

        if (userId == null || !JWTUtils.verify(token, secret, userId, roles)) {
            throw new AuthenticationException("token认证失败！");
        }

        return new SimpleAuthenticationInfo(token, token, "UserRealm");
    }

    /**
     * 验证角色
     * @param principals
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        List<String> roles = JWTUtils.getRolesFromToken(principals.toString());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<>(roles);
        info.setRoles(roleSet);

        return info;
    }
}
