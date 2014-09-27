<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="model.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String sql = "select * from sc";

	ResultSet rs;
%>
<%	
	request.setCharacterEncoding("utf-8");
	String delId=request.getParameter("id");
	String sql2 = "delete from sc where cno = '"+delId+"'";
	sql = "delete from course where cno='"+delId+"' "; 
	
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>删除课程</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
	<%
		try 
		{
			db.execute(sql2);
			db.execute(sql);
		}
		
		catch (Exception e) {
				e.printStackTrace();
		}
		
		response.sendRedirect("situation.jsp");
	%>
  </body>
  
  
</html>
