package com.example.demo.user.vo;

import com.example.demo.user.vo.result.Paged;
import lombok.Data;

@Data
public class UserVO extends Paged {
    private Integer id;

    private String userName;

    private String password;

    private String salt;
}
