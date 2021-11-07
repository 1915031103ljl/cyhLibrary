package com.cyh.servlet;

import com.cyh.dao.impl.UserDao;

import static org.junit.jupiter.api.Assertions.*;

class UserServletTest{

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        UserServlet userServlet = new UserServlet();
//        Number sumUser = new UserDao().getSumUser();
//        Integer sum = (Integer) sumUser;
//        System.out.println(sum);
        System.out.println(Integer.parseInt(userDao.getSumUser()+""));
    }

}