package com.cyh.servlet;

import com.cyh.dao.impl.UserDao;
import com.cyh.pjo.User;

import java.util.List;

import static com.cyh.servlet.IndexServlet.ergodicJudgement;
import static org.junit.jupiter.api.Assertions.*;

class IndexServletTest {
    public static void main(String[] args) {
        IndexServlet l = new IndexServlet();
        UserDao userDao = new UserDao();
        List<User> listUser = userDao.getListUser();
        System.out.println(ergodicJudgement(listUser, "奥特曼", "2009"));
    }

}