package com.example.demo.user.result;

import lombok.Data;

/**
 * 分页基础类，用于被bean继承
 * Created by bugu on 2020/3/29
 */
@Data
public class Paged {

    private int pageNo = 1;

    private int pageSize = 10;

    /**
     * MySQL查询的offset，默认=0
     */
    public int getOffset() {
        return pageNo > 0 ? (pageNo - 1) * pageSize : 0;
    }

    /**
     * MySQL查询的limit，默认=10
     */
    public int getLimit() {
        return pageSize > 0 ? pageSize : 10;
    }
}
