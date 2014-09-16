<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String sql = "select * from student";

	ResultSet rs;
	
	String use=request.getParameter("name");
	String pass=request.getParameter("pass");
	String realname=request.getParameter("realname");
	String role=request.getParameter("role");
	String sql1="select count(*) from student where sno='"+use+"' ";
try{
	rs = db.executeQuery(sql1);		
   
   if(rs.next())
   {
	   if(rs.getInt(1)==0)
	   {
			sql="insert into student(sno,spwd,sname) values('"+use+"','"+pass+"','"+realname+"')";

			db.executeUpdate(sql); 
			response.sendRedirect("index.html");
	   }
   else
   {
    %>
<!--     <script language="javascript">
	    alert("该学生已被注册");
	    window.location.href="index.html";
    </script> -->
    <% 
     
   }
   }
   }catch(Exception e)
   {
      e.printStackTrace();
      
   }

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
   
  </body>
</html>
