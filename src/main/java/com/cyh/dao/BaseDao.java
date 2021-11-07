package com.cyh.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //创建工具类
    private QueryRunner queryRunner = new QueryRunner();
    //执行数据操作并且返回受影响的行数
    public int queryUpData(String sqlQuery,Object... arg){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sqlQuery,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("数据查询失败！");
        }finally {
            JdbcUtils.setClose();
        }
        return -1;
    }

    //查询一条语句
    public <T> T queryOneData(Class<T> type, String sql, Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("数据查询失败！");
        }finally {
            JdbcUtils.setClose();
        }
        return null;
    }

    //查询一组数据
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();
        try {
            return queryRunner.query(con, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.setClose();
        }
        return null;
    }

    public Object queryForSingleValue(String sql, Object... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.setClose();
        }
        return null;
    }


}
