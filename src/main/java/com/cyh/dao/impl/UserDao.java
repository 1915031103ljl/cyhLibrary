package com.cyh.dao.impl;

import java.util.List;

import com.cyh.dao.BaseDao;
import com.cyh.pjo.User;

public class UserDao extends BaseDao{
	public User getUser(int id) {
		return queryOneData(User.class, "select * from `user_table` where `id` = ?", id);
	}
	public List<User> getListUser() {
		return queryForList(User.class, "select * from `user_table`");
	}
	public int delUser(int id){
		return queryUpData("DELETE FROM `user_table` WHERE id = ?",id);
	}
	public int addUser(int id,String name,String password){
	    return queryUpData("INSERT INTO `user_table` VALUES(?,?,?)",id,name,password);
	}
	public int upDataUser(String name,String password,int id){
		return queryUpData("UPDATE `user_table` SET `name`=?,`password`=? WHERE `id`=?",name,password,id);
	}
	public List<User> getPageUser(int start,int end) {
		return queryForList(User.class, "SELECT * FROM `user_table` LIMIT ?,?",start,end);
	}
	public Number getSumUser(){
		return (Number) queryForSingleValue("SELECT COUNT(*) FROM `user_table`");
	}

}
