package com.example.demo.user.bo.dto;

import lombok.Data;

@Data
public class RegisterDTO {

    // 授权类型（1用户名 2手机号 3微信）
    private Byte identityType;

    // 授权标识符（用户名、手机号或第三方应用的唯一标识）
    private String identifier;

    // 授权凭证（密码、空、微信token）
    private String certificate;
}
