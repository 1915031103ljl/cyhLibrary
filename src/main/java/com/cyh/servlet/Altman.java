package com.cyh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyh.dao.impl.UserDao;
import com.cyh.pjo.User;

@WebServlet("/Altman")
public class Altman extends BaseSerlet {

	public void setData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
	    List<User> listUser = userDao.getListUser();
		request.setAttribute("users", listUser);
		request.getRequestDispatcher("viwe/html/AltmanShoping.jsp").forward(request, response);
//		response.sendRedirect("http://localhost:8080/caiyuhao/viwe/html/AltmanShoping.jsp");
	}
}
