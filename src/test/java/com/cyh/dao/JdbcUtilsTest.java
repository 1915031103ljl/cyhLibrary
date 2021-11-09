package com.cyh.dao;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUtilsTest {
    public static void main(String[] args) {
        System.out.println(JdbcUtils.getConnection());
        System.out.println(44.0/17.0);
        double ceil = Math.ceil(44.0/17.0);
        System.out.println(ceil);
        int i = 44;
        double v = Double.parseDouble(44 + "");
        System.out.println(v);
        int v1 = (int)Math.ceil(v/17.0);
        System.out.println(v1);
    }

}