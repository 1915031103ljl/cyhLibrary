package com.cyh.dao;

import java.util.List;
import java.util.Map;

public interface PageList<T> {
    int sumPage();
    List<T> listPage(int start,int end);
}