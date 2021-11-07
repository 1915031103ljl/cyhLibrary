package com.cyh.servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisteredServlet")
public class RegisteredServlet extends BaseSerlet {
	public void registered(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write("注册成功!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
