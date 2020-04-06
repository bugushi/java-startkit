package com.example.demo.user.vo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页基础类，用于被查询bean继承
 */
@Data
public class PagedQuery {

    @ApiModelProperty("当前页")
    private int pageNo = 1;

    @ApiModelProperty("分页条数")
    private int pageSize = 10;

    /**
     * MySQL查询的offset，默认=0
     */
    @ApiModelProperty(hidden = true)
    public int getOffset() {
        return pageNo > 0 ? (pageNo - 1) * pageSize : 0;
    }

    /**
     * MySQL查询的limit，默认=10
     */
    @ApiModelProperty(hidden = true)
    public int getLimit() {
        return pageSize > 0 ? pageSize : 10;
    }

}
