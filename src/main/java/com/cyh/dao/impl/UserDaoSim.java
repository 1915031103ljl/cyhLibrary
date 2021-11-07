package com.cyh.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.cyh.dao.ASimpleDataBaseQuery;
import com.cyh.pjo.User;

public class UserDaoSim implements ASimpleDataBaseQuery {

	@Override
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/xiaoshuo?serverTimezone=UTC", "root",
					"root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUser() {
		Connection connection = getConnection();
		try {
			if (connection != null) {
				PreparedStatement statement = connection.prepareStatement("");
			} else {
				System.err.println("请连接数据库");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<User> getUsers() {
		// TODO 自动生成的方法存根
		return null;
	}

}
