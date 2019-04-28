<%@page import="org.apache.catalina.connector.Response"%>
<%@page import="com.util.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.bean.Email,java.util.*,java.sql.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<base href="<%=basePath%>">
 <%
 int id=Integer.parseInt(request.getParameter("id"));
 DBConnection db=new DBConnection();
 Connection conn=db.getConnection();
 String sql="delete from email where id='"+id+"'";
 PreparedStatement ptmt=conn.prepareStatement(sql);
 ptmt.executeUpdate();
 response.sendRedirect("http://localhost:8080/springmvc/user/outbox.do");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>