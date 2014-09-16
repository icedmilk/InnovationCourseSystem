<%@ page language="java" import="java.util.*,java.sql.*,bean.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String sql = "select * from student";	
	ResultSet rs;
	user login=new user();
	
	String user;
	String pass;
	String role;
	
	user = (String) request.getParameter("username");
	pass = (String) request.getParameter("password");
	role=(String)request.getParameter("role");
	 
	if(null == user || user.equals("") || null == pass || pass.equals("")) 
	{
		%>
<!-- 		<script language="javascript">
			alert("错误的用户名或密码");
			window.location.href="index.html";
		</script> -->
		<% 
	}
	if ("tea".equals(role))
	{
		if(user.equals("admin") && pass.equals("admin"))
		{
		
			user admin = new user();
			admin.setUsername("admin");
			admin.setRealname("admin");
			session.setAttribute("user",admin);
			response.sendRedirect("situation.jsp");
		}
		else
		{
		
			%>
<!-- 			<script language="javascript">
			alert("错误的用户名或密码");
			window.location.href="index.html";
			</script> -->
			<% 
		}
	}
	
	sql = "select * from student where sno = '" 
			+ user + "' and spwd = '" + pass +"'";
%>
<%
	try 
	{

		rs = db.executeQuery(sql);
		if(rs.next()) 
		{
			user loginUser = new user();
			loginUser.setUsername(rs.getString("Sno"));
			loginUser.setPassword(rs.getString("Spwd"));
			loginUser.setRealname(rs.getString("Sname"));

			if(role.equals("stu"))
			{
				session.setAttribute("user",loginUser);
				loginUser = (user) session.getAttribute("user");
				response.sendRedirect("choosecourse.jsp");
			}

		}
		else 
		{
			//out.print("用户名密码错误，5秒后将回到主页");
			%>
<!-- 			<script language="javascript">
			alert("错误的用户名或密码");
			window.location.href="index.html";
			</script> -->
			<%
			response.setHeader("refresh","url=index.html");
		}
	} catch (Exception e) 
	{
		e.printStackTrace();
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <body>

  </body>
</html>
