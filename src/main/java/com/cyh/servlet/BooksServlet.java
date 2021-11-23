package com.cyh.servlet;

import com.cyh.dao.PageList;
import com.cyh.dao.impl.BooksDaoImpl;
import com.cyh.opring.ServeTools;
import com.cyh.pjo.Books;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/BooksServlet")
public class BooksServlet extends BaseSerlet{
    public void zsgc(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Books> allBooks = bookDao.getAllBooks();
            String s = gson.toJson(allBooks);
            PrintWriter writer = response.getWriter();
            writer.println(s);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void del(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        bookDao.delBook(id);
        System.out.println(id);
    }
    public void add(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        bookDao.addBook(new Books(id,name,author,category,category,null,null));
    }
    public void xiugai(HttpServletRequest request, HttpServletResponse response){
        System.out.println("访问了这里");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        bookDao.upOneBook(new Books(id,name,author,category,category,null,null));
    }
    public void getOneBooks(HttpServletRequest request, HttpServletResponse response){
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Books oneBook = bookDao.getOneBook(id);
            String s = gson.toJson(oneBook);
            PrintWriter writer = response.getWriter();
            writer.println(s);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void pageList(HttpServletRequest request, HttpServletResponse response){
        //传过来的页数
        int yema = Integer.parseInt(request.getParameter("yema"));
        Map<String, Object> map = ServeTools.listPageTo(bookDao, yema, 4);
        String s = gson.toJson(map);
        try {
            response.getWriter().println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
