<%@page import="com.cyh.pjo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.cyh.pjo.User,com.cyh.servlet.Altman,com.cyh.dao.impl.UserDao,org.apache.commons.dbutils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<User> list = (List<User>)request.getAttribute("users"); %>
<table>
		<tr>
			<th>用户id</th>
			<th>用户名</th>
			<th>用户密码</th>
			<th>增加</th>
			<th>删除</th>
		</tr>
<%for(User user : list){%>
		<tr>
			<td><%=user.getId()%></td>
			<td><%=user.getName()%></td>
			<td><%=user.getPassword()%></td>
			<td></td>
			<td></td>
		</tr>
<%}%>

</table>

</body>
</html>