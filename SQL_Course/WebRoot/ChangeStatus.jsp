<%@ page language="java" import="java.util.*,java.sql.*,bean.*" pageEncoding="utf-8"%>
<jsp:useBean id="db" class="bean.DataBase" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>阶段更改</title>
<link rel="stylesheet" type="text/css" href="css/table.css">
<script src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){

    var column=$('#tr1');
	
	$('input[value=yuxuan]').click(function(){
		column.css('display', 'block');
	});
	
	$('input[value=zhengxuan]').click(function(){
		column.css('display', 'none');
	});
	
	$('input[value=fabu]').click(function(){
		column.css('display', 'none');
	});
	
	
	$('input[type=submit]').click(function(){
		var checkStatus = $('input:radio[value="zhengxuan"]:checked').val();
		if(checkStatus != null)
		{
			return confirm("正选阶段将进行选课志愿算法的分配，不能回退，确定继续吗？");
		}
	});
	
	$('input[value=yuxuan]').click();
});



</script>

</head>

<body>
<h1 align=center>设定课程阶段</h1>

<form id="form" name="form1" action="DoChange.jsp?sno=<%=request.getParameter("user") %>" method="post">
<table class="hovertable" align=center>
	<tr>
		<td>
			<table class="hovertable" align=center>
				<tr>
				<td align=right>
						欢迎您，<font color=blue><%=request.getParameter("user")%></font>！
				</td>
				<td align=right>
						<a href="situation.jsp">返回</a>
				</td>
				
				</tr>
			</table>
	
		</td>
	</tr>
	
	<tr>
		<td>
	    <center>现在是 <strong><%=db.status() %></strong><br /><br />(课程发布阶段 → 预选阶段 → 正选阶段)</center>
	    </td>

	</tr>
	
<tr>
<td>
<input type="radio" name="status" value="fabu" />课程发布阶段
<input type="radio" name="status" value="yuxuan" checked/>预选阶段
<input type="radio" name="status" value="zhengxuan" />正选阶段
</td>
</tr>

<tr>
    <td id="tr1" style="display:none;">
		<table>
		<tr>
		    <td>计算科学类：(门)</td>
		    <td><input type="text" name="science" size="10"></td>
		</tr>
		<tr>
		    <td width=100>语言类：(门)</td>
		    <td><input type="text" name="language" size="10"></td>
		</tr>
		<tr>
		    <td>人文艺术类：(门)</td>
		    <td><input type="text" name="art" size="10"></td>
		</tr>
		<tr>
		    <td>管理类：(门)</td>
		    <td><input type="text" name="management" size="10"></td>
		</tr>
		<tr>
		    <td>经济类：(门)</td>
		    <td><input type="text" name="economy" size="10"></td>
		</tr>
		</table>

	</td>
</tr>

	<tr><td>
	<center><input type="submit" value="提交">
	<input type="reset" value="重置">
	</center>
	</td>
	</tr>

</table>
</form>	




</body>
</html>