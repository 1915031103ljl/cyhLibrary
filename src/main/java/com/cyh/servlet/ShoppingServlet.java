package com.cyh.servlet;

import com.cyh.pjo.User;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends BaseSerlet{
    public void  ajaxRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            User user = new User(120,"蔡雨豪二世","12345");
            String s = gson.toJson(user);
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
