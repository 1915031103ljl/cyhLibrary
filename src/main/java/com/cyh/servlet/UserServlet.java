package com.cyh.servlet;

import com.cyh.dao.impl.UserDao;
import com.cyh.pjo.Page;
import com.cyh.pjo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends BaseSerlet{
    private int page;
    private List<User> pageUserList;
    public void delUser(HttpServletRequest request, HttpServletResponse response){
        System.err.println("访问了DelSerlet");
        try {
            String id = request.getParameter("id");
            userDao.delUser(Integer.parseInt(id));
            request.getRequestDispatcher("/UserServlet?action=userPage&nowPage="+page).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    public void addUser(HttpServletRequest request, HttpServletResponse response){
        try {
            String user = request.getParameter("user");
            String id = request.getParameter("id");
            if (id==null){
                id= String.valueOf(100+userDao.getListUser().size());
            }
            String pass = request.getParameter("pass");
            System.err.println("访问了addUser");
            System.err.println("数据库结果:"+userDao.addUser(Integer.parseInt(id), user, pass));

            //判断是否为最后一列
            Double sumPageSize = Double.parseDouble(userDao.getSumUser() + "");
            int ceil = (int) Math.ceil(sumPageSize / 17);
            int[] allId = getArrListId();
            int idEnd = allId[pageUserList.size() - 1];
            if (idEnd > Integer.parseInt(id)){
                System.out.println(idEnd>Integer.parseInt(id));
                System.out.println(page);
            }else {
                page = ceil;
                System.out.println("蔡雨豪是猪"+ceil);
                System.out.println("我的心都傻了"+page);
            }
            if ("registeredUser".equals(request.getParameter("actionType"))){
                response.sendRedirect("viwe/html/Shopping.html");
            }else {
                request.getRequestDispatcher("/UserServlet?action=userPage&nowPage="+page).forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    public void modifyUser(HttpServletRequest request, HttpServletResponse response){
        try{
            System.out.println("访问了modifyUser");
            String user = request.getParameter("user");
            String id = request.getParameter("id");
            String pass = request.getParameter("pass");
            System.out.println(userDao.upDataUser(user, pass, Integer.parseInt(id)));
            request.getRequestDispatcher("/UserServlet?action=userPage&nowPage="+page).forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void userPage(HttpServletRequest request, HttpServletResponse response){
        try{
            page = Integer.parseInt(request.getParameter("nowPage"));
            int pageAttr = page==0?1:page;
            request.setAttribute("nowPage2",pageAttr);
            System.err.println("访问了userPage");
            Page<User> userPage = new Page<>();
            //设置当前页数
            userPage.setNowPage(page-1);
            if (userPage.getNowPage()<0){
                userPage.setNowPage(0);
            }
            //获取总条数
            Integer sumSize = Integer.parseInt(userDao.getSumUser()+"");
            request.setAttribute("sumSize",sumSize);

            //计算总页数

            //计算剩余页数

            //设置当前条的开始与结束
            Integer nowStrip = userPage.getNowPage() * 17;
            //获取数据
            pageUserList = userDao.getPageUser(nowStrip,17);
            //把数据上传到jsp
            request.setAttribute("pageUserList",pageUserList);
            request.getRequestDispatcher("viwe/html/jsp/UserManagement.jsp").forward(request,response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int[] getArrListId(){
        int i = 0;
        int[] ids = new int[pageUserList.size()];
        for (User user:pageUserList){
            int id = user.getId();
            ids[i] = id;
            i++;
        }
        return ids;
    }
}
