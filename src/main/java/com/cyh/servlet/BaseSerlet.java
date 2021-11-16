package com.cyh.servlet;

import com.cyh.dao.impl.BooksDaoImpl;
import com.cyh.dao.impl.UserDao;
import com.cyh.opring.ServletTools;
import com.cyh.pjo.Books;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BaseSerlet")
public abstract class BaseSerlet extends HttpServlet {
	public UserDao userDao;
	public BooksDaoImpl bookDao;
	public static Gson gson;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userDao = new UserDao();
		gson = new Gson();
		bookDao = new BooksDaoImpl();
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		try {
            Method method = this.getClass().getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
