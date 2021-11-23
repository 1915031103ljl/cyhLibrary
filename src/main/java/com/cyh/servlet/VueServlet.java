package com.cyh.servlet;
import com.cyh.pjo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/vueservlet")
public class VueServlet extends BaseSerlet{
    public void getUser(HttpServletRequest request, HttpServletResponse response){
        try {
            System.out.println("访问了这里");
            List<User> listUser = userDao.getListUser();
            response.getWriter().println(gson.toJson(listUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void delUser(HttpServletRequest request, HttpServletResponse response){
        System.out.println("访问了删除方法");
        int id =Integer.parseInt(request.getParameter("id"));
        userDao.delUser(id);
    }
    public void getOneUser(HttpServletRequest request, HttpServletResponse response){
        System.out.println("访问了getOneUser方法");
        int id =Integer.parseInt(request.getParameter("id"));
        try {
            response.getWriter().println(gson.toJson(userDao.getUser(id)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void modification(HttpServletRequest request, HttpServletResponse response){
        System.out.println("访问了modification方法");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int id =Integer.parseInt(request.getParameter("id"));
        userDao.upDataUser(name,password,id);
    }
}
