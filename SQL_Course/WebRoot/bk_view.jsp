<%@ page language="java" import="java.util.*,java.sql.*,bean.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String status = db.status();
	String sql = "select * from sc";
	ResultSet rs;
	int i=0;

	String sUsername=request.getParameter("user");
	sql="select distinct course.*,sc.cstatus from course, sc "
            + "where course.cno = sc.cno "
            + "and sc.sno='" + sUsername + "' "
            + "order by cno";
            //out.print(sql);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>已选课程</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<script language="javascript">
	    function delcfm() {
	        if (!confirm("确定要退课吗？")) {
	            window.event.returnValue = false;
	        }
	    }
	</script>
  </head>
  
  <body>

<h1 align=center>已选课程</h1>
<table class="hovertable" align=center>
<tr><td>
  <table class="hovertable" align=center>
        <tr>
          <td align=left height=20>
            欢迎你，<font color=blue><%=sUsername%></font>！
          </td>
          <td align=right>
            <a href="choosecourse.jsp?user=<%=sUsername %>">继续选课</a>
            &nbsp;| &nbsp;
            <a href="logout.jsp">退出登录</a>
            &nbsp;
          </td>
        </tr>
      </table>
</td></tr>
<tr><td>   
     <table class="hovertable" align=center>
    
    <tr>
    <th>课程编号</th>
    <th>课程名称</th>
    <th>课程类型</th>
    <th>课余量</th>
    <th>课容量</th>
    <%
    	if(db.status()=="预选阶段")
    		out.print("<th>志愿等级</th>");
     %>
    <th>操作</th>

    </tr>
 <%   try {
	
			rs = db.executeQuery(sql);

			while(rs.next()) 
			{
				common addnum=new common();
				course vcourse=new course();

				vcourse.setCourseId(rs.getString("cno"));
				addnum.setCourseid(vcourse.getCourseId());
				vcourse.setCourseName(rs.getString("cname"));
				vcourse.setLimited(rs.getInt("cmax"));
				vcourse.setCtype(rs.getString("ctype"));

				vcourse.setAsp(rs.getInt("cstatus"));

				int n = vcourse.getLimited() - addnum.count();
				
		       %>
		       <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
		       <td><%=vcourse.getCourseId() %></td>
		       <td><%=vcourse.getCourseName() %></td>
		       <td><%=vcourse.getCtype() %></td>
		       <td><%=n %></td>
		       <td><%=vcourse.getLimited() %></td>
		       
		       <%
		       if(status=="预选阶段")
					out.print("<td>"+vcourse.getAsp()+"</td>");
		        %>
		       
		       <td><a href="delete.jsp?id=<%=vcourse.getCourseId() %>&user=<%=sUsername %>" onClick="delcfm()">退课</a></td>
		       
		       </tr>
		       <%			
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    
     %>
     
     </table>
     </td></tr></table>
  </body>
</html>
