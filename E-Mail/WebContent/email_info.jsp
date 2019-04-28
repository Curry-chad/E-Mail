<%@page import="com.util.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.bean.Email,java.util.*,java.sql.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<base href="<%=basePath%>">
<%
String userid=session.getAttribute("userid").toString();
String username=session.getAttribute("username").toString();
if(userid!=null&&!userid.equals("")){
}
int id=Integer.parseInt(request.getParameter("id"));
String type=request.getParameter("type");
DBConnection db=new DBConnection();
Connection conn=db.getConnection();
String sql="select * from email where id='"+id+"'";
PreparedStatement ptmt=conn.prepareStatement(sql);
ResultSet rs=ptmt.executeQuery();
String addressee_id="";
String addresser_id="";
String filepath="";
String filename="";
String title="";
String time="";
String content="";
while(rs.next()){
	title=rs.getString("title");
	addressee_id=rs.getString("addressee_id");
	addresser_id=rs.getString("addresser_id");
	time=rs.getString("time");
	content=rs.getString("content");
	filepath=rs.getString("filepath");
	filename=rs.getString("filename");
}
if(type.equals("in")){
	sql="update email set readed=1 where id='"+id+"'";
	ptmt=conn.prepareStatement(sql);
	ptmt.executeUpdate();
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
</head>

<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">邮件详细信息</a></li>
    </ul>
    </div>
    <div class="email_head">
    	<div class="content"> 
    		<span id="title"></span>
    		<p style="height: 10px;"></p>
    		<table>
    			<tr><td><span class="describe">发件人：</span></td><td><span id="sender"><%=addressee_id %></span></td></tr>
    			<tr><td><span class="describe">时&nbsp;&nbsp;&nbsp;间：</span></td><td><span id="time"><%=time %></span></td></tr>
    			<tr><td><span class="describe">收件人：</span></td><td><span id="reciver"><%=addresser_id %></span></td></tr>
    			<tr><td><span class="describe">附&nbsp;&nbsp;&nbsp;件：</span></td><td><span id="file"><a href="downloadFile?id=<%=id%>"><%=filename %></a></span></td></tr>
    		</table>
    	</div>
    </div>
    <p></p>
    <div class="content">
    	<%=content %>
    </div>

 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    
    
    
    
    
    </div>

</body>
</html>


