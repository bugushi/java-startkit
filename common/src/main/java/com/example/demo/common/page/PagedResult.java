package com.example.demo.common.page;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页查询返回数据，用于包装返回结果
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PagedResult<T> {

    private int pageNo;

    private int pageSize;

    private List<T> rows;

    private long total = 0;

    public PagedResult() {

    }

    public boolean hasMore() {
        return total > getPageSize() * getPageNo();
    }
}