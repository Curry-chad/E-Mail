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
 DBConnection db=new DBConnection();
 Connection conn=db.getConnection();
 String userid=session.getAttribute("userid").toString();
 String friend_id=request.getParameter("friend_id");
 String sql="select username from user where userid='"+friend_id+"'";
 PreparedStatement ptmt=conn.prepareStatement(sql);
 ResultSet rs=ptmt.executeQuery();
 String name="";
 while(rs.next()){
	 name=rs.getString("username");
 }
 if(!name.equals("")){
	 sql="insert into friend(userid,friend_id,friend_name)value(?,?,?)";
	 ptmt=conn.prepareStatement(sql);
	 ptmt.setString(1,userid);
	 ptmt.setString(2,friend_id);
	 ptmt.setString(3, name);
	 ptmt.executeUpdate();
 }
 response.sendRedirect("http://localhost:8080/springmvc/user/friend.do");
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