<%@ page language="java" import="java.util.*,java.sql.*,bean.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String status=db.status();
	String sql = "select sc.sno,sname,cname,sc.cno,sc.cstatus from sc,student,course where sc.sno = student.sno and course.cno = sc.cno";
	ResultSet rs;

%>
<% 
	user loginUser = null;
	String userName = "";
	String realName = "";
	loginUser = (user) session.getAttribute("user");
	
	if(null == loginUser) {
		response.sendRedirect("index.html");
	}
	else {
		userName = loginUser.getUsername();
		realName = loginUser.getRealname();
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/table.css">
<title>Insert title here</title>
</head>
<body>



<h1 align=center>选课情况一览</h1>
<table class="hovertable" align=center>
	<tr>
		<td>
<table class="hovertable" align=center>
	<tr>
		<td align=left height=20>
			欢迎您，<font color=blue><%=realName%></font>！
		</td>
		
		<td align=right>
			<a href="situation.jsp?user=<%=realName %>">返回</a>
		</td>
		
		<td align=right>
			<a href="addcourse.jsp?user=<%=realName %>">添加课程</a>
		</td>
		
		<td align=right>
			<a href="logout.jsp">退出登录</a>
			&nbsp;
		</td>
		
	</tr>
</table>
</td>
</tr>
	<tr>
		<td>
    <p>
    <table class="hovertable" align=center >
    
    <tr>
    <th>学号</th>
    <th>姓名</th>
    <th>课程编号</th>
    <th>课程名</th>
    <%
    	if (status=="预选阶段")
    	{
    		out.print("<th>志愿级别</th>");
    	}
     %>
    </tr>
    <%	
    try 
    {
		rs = db.executeQuery(sql);
		int i=0;
		while(rs.next()) 
		{
			String sno = rs.getString("sno");
			String cno = rs.getString("cno");
			String sname = rs.getString("sname");
			String cname = rs.getString("cname");
			String cstatus = rs.getString("cstatus");
	%>
<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"> 
<td><%=sno%></td> 
<td><center><%=sname%></center></td>
<td><%=cno%></td> 
<td><center><%=cname%></center></td>
<%
	if(status == "预选阶段")
	{
		out.print("<td><center>"+cstatus+"</center></td>");
		
	}
 %>
</tr>
<%
	}
} catch (Exception e) {
	e.printStackTrace();
}
  
   %>
</table>
</td>
</tr></table>



</body>
</html>