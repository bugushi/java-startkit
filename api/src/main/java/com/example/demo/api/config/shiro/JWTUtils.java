package com.example.demo.api.config.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.List;

/**
 * jwt 工具类
 */
public class JWTUtils {

    /**
     * 生成token
     * @param userId
     * @return
     */
    public static String createToken(Integer userId, List<String> roles, Long expireTime, String secret) {
        Date date = new Date(System.currentTimeMillis() + expireTime);
        Algorithm algorithm = Algorithm.HMAC256(secret);

        // 在token中添加userId和roles信息
        return JWT.create()
                .withClaim("id", userId)
                .withClaim("roles", roles)
                //到期时间
                .withExpiresAt(date)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     * @param token
     * @param userId
     * @return
     */
    public static boolean verify(String token, String secret, Integer userId, List<String> roles) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        //在token中附带了username信息
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("id", userId)
                .withClaim("roles", roles.toString())
                .build();
        //验证 token
        verifier.verify(token);
        return true;
    }

    /**
     * 从token中获取username
     * @param token
     * @return
     */
    public static Integer getUserIdFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 从token中获取roles
     * @param token
     * @return
     */
    public static List<String> getRolesFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("roles").asList(String.class);
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
