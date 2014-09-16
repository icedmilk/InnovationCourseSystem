<%@ page language="java" import="java.util.*,java.sql.*,java.io.*,bean.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String sql = "select * from course";
	ResultSet rs;
	request.setCharacterEncoding("utf-8");
	String courseid=request.getParameter("courseId");
	String coursename=request.getParameter("courseName");
	String courseType = request.getParameter("courseType");
	   
	int courselimit=new Integer(request.getParameter("limited")).intValue();

	course newCourse = new course();
	newCourse.setCourseId(courseid);
	newCourse.setCourseName(coursename);
	newCourse.setLimited(courselimit);
    newCourse.setCtype(courseType);
    
    request.setCharacterEncoding("utf-8");
	sql = "select * from course where cno="+newCourse.getCourseId();
	rs = db.executeQuery(sql);
	if(rs.next())
	{
		%>
		<script>
			alert("已经有过该课程编号的课程");
			window.location.href="addcourse.jsp";
		</script>
		<% 
	}
	sql = "insert into course set "
        + "cno='" + newCourse.getCourseId() + "', "
        + "cname='" + newCourse.getCourseName() + "', "
        + "ctype='" + newCourse.getCtype() + "', "
        + "cmax=" + newCourse.getLimited() ;

%>
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加课程信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
   <% 

    try {
			db.executeUpdate(sql); 
			
	} catch (Exception e) {
			e.printStackTrace();
	}
	%>
		<script>
			alert("添加成功~喵！");
			window.location.href="addcourse.jsp";
		</script>
		<% 

     %>
  </body>
</html>
