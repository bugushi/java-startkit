package com.example.demo.api.vo.query;

import com.example.demo.common.page.Paged;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQuery extends Paged {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;
}
