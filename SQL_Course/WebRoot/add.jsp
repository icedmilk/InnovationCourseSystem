<%@ page language="java" import="java.util.*,java.sql.*,bean.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String sql = "select * from sc";
%>

<%	
	String id = request.getParameter("id");
	String username=request.getParameter("user");
	
	if(db.status()=="课程发布阶段")
	{%>
	<script>
		alert("现在是课程发布阶段，需要等到预选阶段才能选课！");
		window.location.href="choosecourse.jsp";   
	</script>
	<%}
	else if (db.status()=="预选阶段")
	{
		//是否填写过该志愿
		boolean flag = false;
		String sqlAsp = "select cstatus from sc where sno='"+username+"'";

		ResultSet rsSqlAsp;
		try
		{
			rsSqlAsp=db.executeQuery(sqlAsp);
			while (rsSqlAsp.next())
			{
			
				if(Integer.parseInt(request.getParameter("asp")) == rsSqlAsp.getInt(1))
				{
					flag = true;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
   
		}
		
		if (flag){
			%>
					<script>
					alert("你已经有过第"+<%=request.getParameter("asp")%>+"志愿的课程");
					window.location.href="choosecourse.jsp";
					</script>
					<% 
		}
		else{
		String sql1="select count(*) from sc where sno='"+username+"' and cno='"+id+"'";//选XX课的数量
		try
		{	
			ResultSet rs;
			rs = db.executeQuery(sql1);		
			
			if(rs.next())
			{   
				if(rs.getInt(1)==0)//若没选课
				{%>
					<script>
					alert("选课成功"); 
					</script>
				<% 
					sql = "insert into sc(sno,cno,cstatus) values('"+username+"','"+id+"','"+request.getParameter("asp")+"')"; 
					db.executeUpdate(sql); 
					
					response.setHeader("refresh","0;url=choosecourse.jsp ");

				}
				else
				{%>
					<script>
					alert("你已经选过此课程");
					window.location.href="choosecourse.jsp";
					</script>
					<% 

				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
   
		}
	}
	}
	else if(db.status()=="正选阶段")
	{
	int limit=new Integer(request.getParameter("limit")).intValue();
	int n=new Integer(request.getParameter("x")).intValue();
	
	if(n<limit)
	{

		String sql1="select count(*) from sc where sno='"+username+"' and cno='"+id+"'";//选XX课的数量
		try
		{	
			ResultSet rs;
			rs = db.executeQuery(sql1);		
			
			if(rs.next())
			{   
				if(rs.getInt(1)==0)//若没选课
				{%>
					<script language="javascript">
					alert("选课成功"); 
					</script>
				<% 
					sql = "insert into sc(sno,cno) values('"+username+"','"+id+"') "; 
					db.executeUpdate(sql); 
					
					response.setHeader("refresh","0;url=choosecourse.jsp ");

				}
				else
				{%>
					<script>
					alert("你已经选过此课程");
					window.location.href="choosecourse.jsp";
					</script>
					<% 

				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
   
		}
	}
	else
	{%>		
		<script language="javascript">
		alert("此课程人数已满！");
		window.location.href="choosecourse.jsp";   
		</script>
	<%
	}
	}
	%>
   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Add</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
   
  
  </body>
</html>
