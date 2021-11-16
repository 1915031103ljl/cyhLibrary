package com.cyh.servlet;

import com.cyh.opring.ServletTools;
import com.cyh.pjo.Books;
import com.cyh.pjo.User;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends BaseSerlet{
    public void  ajaxRequest(HttpServletRequest request, HttpServletResponse response){
        boolean judge;
        try {
            String name = request.getParameter("name");
            judge = !"admin".equals(name);
            String s = gson.toJson(judge);
            response.getWriter().write(s);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 热度榜的服务器代码
     * @param request
     * @param response
     */
    public void heatBarDisplay(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, Object> dataMap = new HashMap<>();
            List<String> list = new ArrayList<>();
            User user = new User(1, "cyh2002", "131415926");
            list.add("他将君临天下");
            list.add("但是他终于还是失败");
            dataMap.put("title","龙族");
            dataMap.put("list",list);
            dataMap.put("user",user);
            ServletTools.serverDataSending(dataMap,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理书籍架的数据
     * @param request
     * @param response
     */
    public void bookEntity(HttpServletRequest request, HttpServletResponse response){
        //获取数据库所有的数据
        List<Books> allBooks = bookDao.getAllBooks();
        ServletTools.<List<Books>>serverDataSending(allBooks, response);
    }
}
