<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>用户信息添加4</h1>
<form action="user/add4.do" method="post">
	编号：<input  type="text" name="personId" /><br/>
编号：<input  type="text" name="userName" /><br/>
	<input type="submit" value="提交"/>
</form>
${personId }----${personName }<br/>
${userName }
</body>
</html>