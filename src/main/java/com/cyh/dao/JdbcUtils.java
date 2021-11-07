package com.cyh.dao;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> con = new ThreadLocal<Connection>();
    static {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("Jdbc.properties");
            properties.load(resourceAsStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection conn = con.get();
        if (conn == null){
            try {
                conn = dataSource.getConnection();
                con.set(conn);
                conn.setAutoCommit(false);
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    public static void setClose() {
        Connection connection = con.get();
        if (connection != null){
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        con.remove();
    }
}
