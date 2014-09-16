<%@ page  language="java" import="java.util.*,java.sql.*,bean.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>消息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/table.css">

<%
	String Sno = request.getParameter("sno");
	String Info = "select sinfo from student where sno = "+Sno;
	ResultSet rsInfo = db.executeQuery(Info);
	rsInfo.next();
	String InfoSno = rsInfo.getString(1);

 %>

  </head>
  
  <body>
	<h1 align=center>收件箱</h1>
	<table class="hovertable" align=center>
<tr>
<td>
        <table class="hovertable" align=center>
        <tr>
          <td align=left height=20> 
		            欢迎你，<font color=blue><%=Sno%></font>！
          </td>
          <td align=right>
            <a href="choosecourse.jsp">返回</a>
            </td>
         </tr>
         </table>
   </td>
   </tr>
   <tr>
   <td> 
    <table class="hovertable" align=center>
    <tr>
    
    <%	if(InfoSno!=null)
    {
    	String[] splited = InfoSno.split("：");
     	InfoSno=splited[0]+"：</br></br><strong>"+splited[1]+"</strong>";
     	out.print(InfoSno);
     }
     else
      out.print("<center>您的收件箱中暂无任何消息</center>");
      db.close();
     %>
    
    </tr>
    </table>
    </td>
    </tr>
    </table>
    
            
  </body>
</html>
