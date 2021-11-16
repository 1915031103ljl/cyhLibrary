package com.cyh.servlet;

import com.cyh.pjo.Books;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
}
