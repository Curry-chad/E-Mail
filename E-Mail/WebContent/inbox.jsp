<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.bean.Email,java.util.*"%>
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
List<Email> in_emails=new ArrayList();
in_emails=(ArrayList<Email>)request.getAttribute("in_emails");
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
    <li><a href="#">收件箱</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab2">收件箱</a></li> 
  	</ul>
    </div> 
    </ul>
    
    </div> 
    
    
  	<div id="tab2" class="tabson">
    
    <form action="user/sinbox.do" name="search" method="post">
    <ul class="seachform">
    <li><label>发件人</label><input name="addressee_id" type="text" class="scinput" /></li>
    <li><label>主题</label><input name="stitle" type="text" class="scinput" /></li>
    <li><label>是否已读</label>  
    <div class="vocation">
    <select class="select3" name="readed">
    <option value="0">未读</option>
    <option value="1">已读</option>
    </select>
    </div>
    </li>
    <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="查询"/></li>
    </ul>
    </form>
    
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>标题</th>
        <th>发件人</th>
        <th>时间</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
        <%
        int i=1;
        for(Email e:in_emails){
        	out.print("<tr><td><input name='' type='checkbox'/></td>");
        	out.print("<td>"+i+"</td>");
        	if(e.getReaded()==0){
        		out.print("<td><font color='red'>"+e.getTile()+"</font></td>");
        	}else{
        		out.print("<td>"+e.getTile()+"</td>");
        	}
        	out.print("<td>"+e.getAddressee_id()+"</td>");
        	out.print("<td>"+e.getTime()+"</td>");
        	out.print("<td><a href='email_info.jsp?id="+e.getId()+"&type=in' class='tablelink'>查看</a><a href='delete.jsp?id="+e.getId()+"' class='tablelink'> 删除</a></td>");
        	out.print("</tr>");
        	i++;
        }
        %>
        
        </tr> 
        </tbody>
    </table>
    
   
  
    
    </div>  
       
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

