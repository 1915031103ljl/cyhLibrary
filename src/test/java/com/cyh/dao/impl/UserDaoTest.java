package com.cyh.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        System.out.println(userDao.addUser(7, "龙什么", "蕾姆世界第一可爱"));
//        System.out.println(userDao.getUser(1));
        System.out.println(userDao.upDataUser("yang","1234",1));
    }

}