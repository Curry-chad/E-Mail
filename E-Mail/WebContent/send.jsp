<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<base href="<%=basePath%>">
<%
String userid=session.getAttribute("userid").toString();
String username=session.getAttribute("username").toString();
if(userid==null||userid.equals("")){
	response.sendRedirect("http://localhost:8080/springmvc");
}
String addresser=request.getParameter("addresser_id");
String title=request.getParameter("title");
String content=request.getParameter("content");
if(addresser==null){
	addresser="";
}
if(title==null){
	title="";
}
if(content==null){
	content="";
}
%>
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
<link href="css/upload.css" rel="stylesheet" type="text/css" />
<script src="js/script.js"></script>
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
    <li><a href="#">写信</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">写信</a></li> 
  	</ul>
    </div> 
    
  	<div id="tab1" class="tabson">
    
    <div class="formtext">Hi，<b><%=username%></b>，欢迎您使用写信功能！</div>
    <form action="upload?type=0" name="send" enctype="multipart/form-data" method="post" >
    	<ul class="forminfo">
    		<input name="userid" type="hidden" value="<%=userid %>" />
    		<li><label>收件人<b>*</b></label><input name="receiver" type="text" class="dfinput" value="<%=addresser %>" style="width:518px;"/></li>
    		<li><label>主题<b>*</b></label><input name="title" type="text" class="dfinput" value="<%=title %>"  style="width:518px;"/></li>
    		<li><label>添加附件<b></b></label><input type="file" name="file" onchange="fileSelected();"></li>
    		<li><textarea id="content7" value="<%=content %>" name="email_content" style="width:700px;height:250px;visibility:hidden;"></textarea></li>
    		<li><input name="send" type="submit" class="btn" value="发送"/>
    		<button onclick="save()" class="btn"/>存草稿</button></li>
    	</ul>
    </form>
    
    </div>
    </div> 
    
     
	</div> 
 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
      function save(){
    	  document.send.action="upload?type=1";
          document.send.submit();
      }
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    
    
    


</body>
</html>
