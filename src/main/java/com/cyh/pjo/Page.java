package com.cyh.pjo;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    private final int PAGE_SIZE = 17;
    //获取数量
    private int pageSize = PAGE_SIZE;
    // 当前页码
    private int nowPage = 1;

    // 总页码 = 数据库里面的总数
    private int sumPage;

    // 剩余页码 =总页码 - 当前页码
    private int surplusPage;

    // 数据集
    private List<T> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getSurplusPage() {
        return surplusPage;
    }

    public void setSurplusPage(int surplusPage) {
        this.surplusPage = surplusPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
