<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String sql = "select * from sc";
	ResultSet rs;
%>
<%	
	String delUser = request.getParameter("user");
	String delId=request.getParameter("id");
	sql = "delete from sc where sno = '" + delUser + "' and cno='"+delId+"' "; 
	
	
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
		db.execute(sql);
	}
	
	catch (Exception e) {
			e.printStackTrace();
	}
	
	response.sendRedirect("view.jsp?user="+delUser+"");
%>
   
</body>
</html>
