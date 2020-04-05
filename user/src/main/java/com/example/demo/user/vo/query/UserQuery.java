package com.example.demo.user.vo.query;

import com.example.demo.user.vo.page.Paged;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQuery extends Paged {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;
}
