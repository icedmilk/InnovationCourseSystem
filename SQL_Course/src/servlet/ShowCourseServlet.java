package servlet;

import java.io.IOException;
import java.io.PrintWriter;


import java.sql.ResultSet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CookieHandler;

import bean.DataBase;
import bean.common;


public class ShowCourseServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		DataBase db = new DataBase();
		String status = db.status();
		String sql = "select * from course";

		ResultSet rs;

		String sno = request.getParameter("sno");
//		user loginUser = null;
		
//		loginUser = (user) request.getSession().getAttribute("user");
		
//		if(loginUser == null)
//			return;
		
		try
		{
			rs = db.executeQuery(sql);
			out.print("现在是<div id=\"status\" style=\"font-color:red;\">"+status+"</div>阶段");
			out.print("<table class=\"display\" id=\"courseinfo\">");
			out.print("<thead>");
			out.print("<tr><th>课程编号</th><th>课程名称</th><th>课程类型</th><th>课容量</th><th>课余量</th><th>操作</th></tr>");
			out.print("</thead>");
			out.print("<tbody>");
			while (rs.next())
			{
				common addnum = new common();

				String id = rs.getString("cno");
				String type = rs.getString("ctype");
				addnum.setCourseid(id);
				String course_name = rs.getString("cname");
				int limit = rs.getInt("cmax");
				int n = limit - addnum.count();
				DataBase dc = new DataBase();
				boolean chosen = dc.havechosen(sno, id);
				
				out.print("<tr>");
				out.print("<td>" + id + "</td>");
				out.print("<td>" + course_name + "</td>");
				out.print("<td>" + type + "</td>");
				out.print("<td>" + limit + "</td>");
				out.print("<td>" + n + "</td>");
				if(chosen)
					out.print("<td><button>取消</button></td>");
				else
					out.print("<td><button>选课</button></td>");
				//out.print("<td><a class=\"choose\" id=\"choose\"onclick=\"aspiration('<%=status %>','<%=id %>','<%=userName %>','<%=limit %>','<%=limit-n %>')\">选课</a></td>");
				out.print("</tr>");
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		db.close();
		out.print("</tbody>");
		

		out.print("</table>");
		
		out.print(CookieHandler.getValue(request, "user"));
		
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
