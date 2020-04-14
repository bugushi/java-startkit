package com.example.demo.common.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页基础类，用于被查询bean继承
 */
@Data
public class Paged {

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
