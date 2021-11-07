package com.cyh.dao;

import java.sql.Connection;
import java.util.List;

import com.cyh.pjo.User;

public interface ASimpleDataBaseQuery {
	Connection getConnection();
	User getUser();
	List<User> getUsers();
}
