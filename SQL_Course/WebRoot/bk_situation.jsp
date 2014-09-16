<%@ page language="java" import="java.util.*,java.sql.*,bean.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String sql = "select * from course";
	ResultSet rs;
	/*sql="select course.*,IF(elective.course_id, count(*), 0) as amount "
                         + "from course left join elective "
                         + "on course.course_id = elective.course_id "
                         + "group by course.course_id";*/
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课程发布情况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/table.css">
	
	
	<script language="javascript">
	    function delcfm() {
	        if (!confirm("确定要取消该课程吗？")) {
	            window.event.returnValue = false;
	        }
	    }
	</script>

</head>

<body>

<h1 align=center>课程发布情况</h1>
<table class="hovertable" align=center>
	<tr>
		<td>
<table class="hovertable" align=center>
	<tr>
		<td align=left height=20>
			欢迎您，<font color=blue><%=realName%></font>！
		</td>
		
		<td align=right>
			<a href="viewSC.jsp?user=<%=realName %>">选课情况</a>
		</td>
		
		<td align=right>
			<a href="addcourse.jsp?user=<%=realName %>">添加课程</a>
		</td>
		
		<td align=right>
			<a href="ChangeStatus.jsp?user=<%=realName %>">设定课程阶段</a>
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
    <th>课程编号</th>
    <th>课程名称</th>
	<th>课程类型</th>
    <th>限制人数</th>
    <th>已选人数</th>
    <th>操作</th>
    </tr>
    <%	
    try 
    {
		rs = db.executeQuery(sql);
		int i=0;
		while(rs.next()) 
		{
			course vcourse=new course();
			common addnum=new common();
			

			String id = rs.getString("cno");
			addnum.setCourseid(id);
			
			String course_name = rs.getString("cname");
			String courseType = rs.getString("ctype");
			int limit=rs.getInt("cmax");
			
			int n = addnum.count();
	%>
<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"> 
<td><%=id%></td> 
<td><%=course_name%></td>
<td><%=courseType%></td>
<td><%=limit%></td> 
<td><%=n %></td>
<td><a href="delCourse.jsp?id=<%=id%>" onClick="delcfm()">取消课程</a></td>
</tr>
<%
	}
} catch (Exception e) {
	e.printStackTrace();
}db.close();

%>
</table>
</td>
</tr></table>
  </body>
</html>
