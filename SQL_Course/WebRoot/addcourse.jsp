<%@ page language="java" import="java.util.*,java.sql.*,model.*"
	pageEncoding="utf-8"%>
<jsp:useBean id="db" class="model.DataBase" scope="page" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%

	String userName = "";
	String realName = "";

	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>添加课程</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/table.css">

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/courseValidate.js" charset="utf-8"></script>


<!-- <script type="text/javascript">
	function isInteger( str ){  
		var regu = /^[-]{0,1}[0-9]{1,}$/; 
		return regu.test(str); 
	} 
	
	function validate_required(field,alerttxt)
	{
		with (field)
		  {
			  if (value==null||value=="")
			    {
				    alert(alerttxt);
				    return false;
			    }
			    
			  else 
			  {
			  	return true;
			  }
		  }
	}
	
	function validate_form(thisform)
	{
	with (thisform)
	  {
		if (validate_required(courseId,"课程编号不能为空！")==false)
		{
			courseId.focus();
			
			return false;
		}
		var cid=document.getElementById("cid");
		if(!isInteger(cid.value))
		{
			alert("课程号必须为数字串!");
			cid.select();
			cid.focus();
			return false;
		}
		
		if (validate_required(courseName,"课程名不能为空！")==false)
		{
			courseName.focus();
			return false;
		}
		if (validate_required(courseType,"课程类型不能为空！")==false)
		{
			courseType.focus();
			return false;
		}
		if (validate_required(limited,"课容量不能为空！")==false)
		{
			limited.focus();
			return false;
		}
		var lim=document.getElementById("lim");
		if(!isInteger(lim.value))
		{
			alert("课容量必须为数字!");
			lim.select();
			lim.focus();
			return false;
		}
		else
		{
			//alert("添加成功，喵~");
			return true;
		}
	  }
	  

		
	}
	</script> -->



</head>

<body>

	<h1 align=center>添加课程</h1>

	<table class="hovertable" align=center>
		<tr>
			<td>

				<table class="hovertable" align=center>
					<tr>
						<td align=left height=20>欢迎你，<font color=blue><%=realName%></font>！
						</td>

						<td align=right><a href="situation.jsp">课程情况一览</a>&nbsp;|
							&nbsp; <a href="logout.jsp">退出登录</a>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td>
				<form action="addcourse1.jsp" method="post"
					onSubmit="validate_form(this)">
					<table class="hovertable" align=center>

						<tr>
							<td>

								<center>
									<strong>(推荐开课： <%
										int a = db.course_count("science");
										int b = db.course_count("language");
										int c = db.course_count("art");
										int d = db.course_count("management");
										int e = db.course_count("economy");

										int max = 0;
										String maxtag = "";
										max = e > (d > (c > (a > b ? a : b) ? c : (a > b ? a : b)) ? d
												: (c > (a > b ? a : b) ? c : (a > b ? a : b))) ? e
												: (d > (c > (a > b ? a : b) ? c : (a > b ? a : b)) ? d
														: (c > (a > b ? a : b) ? c : (a > b ? a : b)));
										if (max == a) {
											out.print("计算科学类");
										} else if (max == b) {
											out.print("语言类");
										} else if (max == c) {
											out.print("人文艺术类");
										} else if (max == d) {
											out.print("管理类");
										} else if (max == e) {
											out.print("经济类");
										}
									%>)</strong>尚缺课程如下：
								</center>
							</td>

						</tr>
						<tr>
							<td>计算科学类<%=db.course_count("science")%>&nbsp; 语言类<%=db.course_count("language")%>&nbsp;

								人文艺术类<%=db.course_count("art")%>&nbsp; 管理类<%=db.course_count("management")%>&nbsp;

								经济类<%=db.course_count("economy")%></td>
						</tr>


						<tr>
							<td align=center valign=top>
								<table class="hovertable" align=center>

									<tr>
										<td align="center">课程编号</td>
										<td align="left"><input name="courseId" id="cid"
											type="text" size="30" maxlength="6"> <span
											id="cidValidate" style="color:red;"></span>
										</td>

									</tr>

									<tr>
										<td align="center">课程名称</td>
										<td align="left"><input name="courseName" type="text"
											id="cname" size="30" maxlength="15"><span
											id="cnameValidate" style="color:red;"></span>
										</td>
									</tr>

									<tr>
										<td align="center">课程类型</td>
										<td align="left"><input name="courseType" type="text"
											id="ctype" size="30" maxlength="10"><span
											id="ctypeValidate" style="color:red;"></span>
										</td>
									</tr>

									<tr>
										<td align="center">限制人数</td>
										<td align="left"><input name="limited" id="lim"
											type="text" size="30" maxlength="3">
										</td>
									</tr>

								</table>
							</td>
						</tr>
						<tr>
							<td height=10></td>
						</tr>
						<tr>
							<td height=30 align=center>
								<button class="clickable" type="submit">提交</button>
								<img src="img/loading.gif" width="70px" height="70px" style="display:none">
								<button class="clickable" type="reset">取消</button>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>
