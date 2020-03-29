package com.example.demo.user.result;

import lombok.Data;

import java.util.List;

/**
 * 分页查询返回数据，用于包装bean
 * Created by bugu on 2020/3/29
 */
@Data
public class PagedResult<T> extends Paged {

    private List<T> results;

    private long total = 0;

    public PagedResult(Paged paged) {
        this.setPageNo(paged.getPageNo());
        this.setPageSize(paged.getPageSize());
    }

    public boolean hasMore() {
        return total > getPageSize() * getPageNo();
    }
}
