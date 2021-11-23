package com.cyh.opring;

import com.cyh.dao.PageList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServeTools {
    public static <T> Map<String,Object> listPageTo(PageList<T> pageList, int pageNumber, int numberOfPages, T... t){
        Map<String, Object> map = new HashMap<>();
        List<T> ts = pageList.listPage((pageNumber - 1)*4,numberOfPages);
        int i = pageList.sumPage()/numberOfPages+(pageList.sumPage()%numberOfPages>0?1:0);
        map.put("list",ts);
        map.put("sumPage",i);
        return map;
    }
}
