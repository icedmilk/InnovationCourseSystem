<%@ page  language="java" import="java.util.*,java.sql.*,bean.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String status = db.status();
	String sql = "select * from course";

	ResultSet rs;
	
	user loginUser = null;
	
	String userName = "";
	String realName="";
	
	loginUser = (user) session.getAttribute("user");
	
	if(null == loginUser) 
	{
		response.sendRedirect("index.html");
	}
	else
	{
		userName = loginUser.getUsername();
		realName=loginUser.getRealname();
	}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选课</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/table.css">


  <script>
	
  	function aspiration(status,id,userName,limit,n)
  	{
  		//如果判断为预选阶段，则进行相应的志愿填写	
  		if(status=="预选阶段")
  		{
  			var asp =prompt("请输入你的志愿等级(1为最高，9为最低)","1");
  			var type="^[1-9]$";
  			var re = new RegExp(type);
  			//如果符合正则匹配，则跳转到相应页面
  			if(asp.match(re)!=null)
  			{
  				var a="add.jsp?id="+id+"&user="+userName+"&limit="+limit+"&x="+n+"&asp="+asp;
  				window.location.href=a;
  			}
  			else
  			{
  				alert("必须输入1-9之间的数字");
  			}
  		}
  		//不是预选阶段则直接进行页面跳转
  		else
  		{
	  		var a="add.jsp?id="+id+"&user="+userName+"&limit="+limit+"&x="+n;
	  		
  			window.location.href=a;
  		}
  	}
  </script>
  </head>
  
  <body>

<h1 align=center>可选课程一览</h1>
<table class="hovertable" align=center>
<tr>
<td>
        <table class="hovertable" align=center>
        <tr>
          <td align=left height=20> 
            欢迎你，<font color=blue><%=realName%></font>！
          </td>
          <td align=right>
            <a href="view.jsp?user=<%=userName %>">已选课程列表</a>
            </td>
            <td>
            <a href="ChooseInfo.jsp?sno=<%=userName %>">消息</a>

          </td>
            <td>
            <a href="logout.jsp">退出登录</a>

          </td>
        </tr>
      </table>
   </td>
   </tr>
   <tr>
   <td> 
    <table class="hovertable" align=center>
    <tr><center>现在是 <strong id="status"><%=db.status() %></strong></center></br></tr>
    <tr>
    <th>课程编号</th>
    <th>课程名称</th>
    <th>课程类型</th>
    <th>课余量</th>
    <th>课容量</th>
    <th>操作</th>
    </tr>

    <%
		try 
		{
			rs = db.executeQuery(sql);
			while(rs.next()) 
			{
				course vcourse=new course();
				common addnum=new common();
				String id = rs.getString("cno");
				String type = rs.getString("ctype");
				addnum.setCourseid(id);
				String course_name = rs.getString("cname");
				int limit=rs.getInt("cmax");
 				int n = limit - addnum.count();
				
				%>
				<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"> 
				<td><%=id%></td> 
				<td><%=course_name%></td>
				<td><%=type%></td>
				<td><%=n %></td>
				<td><%=limit%></td> 
				 
				<td><a class="choose" id="choose"onclick="aspiration('<%=status %>','<%=id %>',
				'<%=userName %>','<%=limit %>','<%=limit-n %>')">选课</a></td>
				</tr>
				<%
			}
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		db.close();
     %>
    </table>
    </td>
    </tr>
    </table>
  </body>
</html>
