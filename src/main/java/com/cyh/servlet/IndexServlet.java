 package com.cyh.servlet;

import com.cyh.dao.impl.UserDao;
import com.cyh.pjo.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IndexServlet")
public class IndexServlet extends BaseSerlet {

	public void userLogin(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("访问了userLogin");
		//设置中文
		//获取用户名和密码
		String pass = request.getParameter("pass");
		String user = request.getParameter("user");
		//获取数据库里面的集合
		List<User> listUser = userDao.getListUser();
		try {
			//验证用户登入
			if(user.equals(userDao.getUser(999).getName())&&pass.equals(userDao.getUser(999).getPassword())) {
				//请求重定向
				//response.sendRedirect("http://localhost:8080/caiyuhao2/viwe/html/login.html");
				//请求转发
				try {
					request.getRequestDispatcher("/UserServlet?action=userPage&nowPage=1").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}else if(ergodicJudgement(listUser,user,pass)){
				System.out.println("登入成功");
				response.sendRedirect("http://localhost:8080/caiyuhao2/viwe/html/Shopping.html");
			} else {
				System.out.println(ergodicJudgement(listUser,user,pass));
				System.out.println(listUser);
				response.sendRedirect("http://localhost:8080/caiyuhao2/index.html");
				//response.getWriter().append("登入失败");
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public static boolean ergodicJudgement(List<User> list,String use , String password){
		for (User user : list){
			if (user.getName().equals(use)&&password.equals(user.getPassword())){
				return true;
			}
		}
	    return false;
	}


}
