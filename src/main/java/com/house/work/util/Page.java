package com.house.work.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  分页帮助类
 */
public class Page<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_PAGE_SIZE = 20;
    /**
     * 每页数量
     */
    private Integer pageSize = 20;
    /**
     * 当前页
     */
    private Integer pageIndex;
    /**
     * 总页数
     */
    private Integer pageTotal;
    /**
     * 总数据量
     */
    private Integer totalCount;
    /**
     * 分页开始点
     */
    private Integer start;
    /**
     * 数据列表
     */
    private List<T> data = new ArrayList<T>();


    public Page() {
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageTotal() {
        return this.pageTotal;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        if (this.pageIndex != null && this.pageSize != null) {
            start = pageIndex * pageSize;
        }
    }

    public Integer getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        this.caculate();
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


    private void caculate() {
        if (this.pageSize != 0) {
            this.pageTotal = this.totalCount / this.pageSize + (this.totalCount % this.pageSize > 0 ? 1 : 0);
        }

        if (this.pageTotal < 1) {
            this.pageTotal = 1;
        }

    }
}


