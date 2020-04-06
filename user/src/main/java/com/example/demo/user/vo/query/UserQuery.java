package com.example.demo.user.vo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQuery extends PagedQuery {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;
}
