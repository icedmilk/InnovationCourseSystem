<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/table.css">
	<script type="text/javascript">
	
	function validate_required(field,alerttxt)
	{
	with (field)
	  {
		  if (value==null||value=="")
		  {
			    alert(alerttxt);
			    return false;
		  }
		  else 
		  {
		  	return true;
		  }
	  }
	}
	
	function validate_form(thisform)
	{
	with (thisform)
	  {
		if (validate_required(realname,"亲不能做无名大侠啊!")==false)
		{realname.focus();return false;}
		if (validate_required(name,"亲，你的学号呢？")==false)
		{name.focus();return false;}
		if (validate_required(pass,"亲，你的密码呢？")==false)
		{pass.focus();return false;}
		if (validate_required(pwdConfirm,"不要急啊，亲~确认输入下密码嘛！")==false)
		{pwdConfirm.focus();return false;}
		
	  	var password2=document.getElementById("pass");
		var password=document.getElementById("pwdConfirm");
		if(password2.value!=password.value)
		{
			alert("两次密码输入不一致!");
			pass.select();
			pass.focus();
			return false;
		}
		else
		{
			alert("注册成功，喵~");
			return true;
		}
	  }
	  

		
	}
	</script>

  </head>
  
  <body>

<h1 align=center>用户注册</h1>
  
<!--   <form name = "register" action="register1.jsp" method="post" onSubmit="return validate_form(this)"> -->
  <table class="hovertable" align=center>
  
    <tr>
  <td align=center>
  姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="realname" width=100>
  </td>
  </tr>
  
  <tr>
  <td align=center>
	学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" name="name" width=100>
  </td>
  </tr>
  
  <tr>
  <td align=center>
  密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="pass" id="pass" width=100>
  </td>
  </tr>
  
  <tr>
  <td align=center>
  确认密码：<input type="password" name="pwdConfirm" id="pwdConfirm" width=100>
  </td>
  </tr>

  <tr>
  <td align=center>
  <button class=clickable type="submit" name="提交">提交</button>
  <button class=clickable type="reset" name="重置">重置</button>
  </td>
  </tr>
  <tr><td align=center>
   <a href="index.html">返回登陆</a>
  </td></tr>
  </table><!-- 
  </form> -->
   


  </body>
</html>
