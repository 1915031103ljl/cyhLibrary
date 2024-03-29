package com.cyh.pjo;

/**
 * @author 有天道
 * @apiNote 这个是处理用户登入的类
 */
public class User {
	private int id;
	private String name;
	private String password;

	public int getId() {
		return id;
	}

	public User() {
		super();
	}

	public User(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
