package com.example.demo.user.vo.page;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页查询返回数据，用于包装返回结果
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PagedResult<T> extends Paged {

    private List<T> results;

    private long total = 0;

    public PagedResult() {

    }

    public PagedResult(Paged paged) {
        this.setPageNo(paged.getPageNo());
        this.setPageSize(paged.getPageSize());
    }

    public boolean hasMore() {
        return total > getPageSize() * getPageNo();
    }
}
