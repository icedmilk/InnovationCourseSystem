package smu.vccs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smu.vccs.model.DataBase;



public class OverallSituation extends HttpServlet
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
		String sql = "select sc.sno,sname,cname,sc.cno,sc.cstatus from sc,student,course where sc.sno = student.sno and course.cno = sc.cno";
		ResultSet rs;

//		user loginUser = null;
//		String userName = "";
//		String realName = "";
//		// loginUser = (user) session.getAttribute("user");
//
//		if (null == loginUser)
//		{
//			response.sendRedirect("index.html");
//		}
//		else
//		{
//			userName = loginUser.getUsername();
//			realName = loginUser.getRealname();
//		}

		out.print("<table class=\"display\" id=\"overallsituation\" style=\"display:none;\">");

		out.print("<thead><tr>");
		out.print("<th>学号</th>");
		out.print("<th>姓名</th>");
		out.print("<th>课程编号</th>");
		out.print("<th>课程名</th>");

		if (status == "预选阶段")
		{
			out.print("<th>志愿级别</th>");
		}
		out.print("</tr></thead>");

		try
		{
			rs = db.executeQuery(sql);
			//int i = 0;
			while (rs.next())
			{
				String sno = rs.getString("sno");
				String cno = rs.getString("cno");
				String sname = rs.getString("sname");
				String cname = rs.getString("cname");
				String cstatus = rs.getString("cstatus");

				out.print("<tr>");
				out.print("<td>" + sno + "</td>");
				out.print("<td>" + sname + "</td>");
				out.print("<td>" + cno + "</td>");
				out.print("<td>" + cname + "</td>");

				if (status == "预选阶段")
				{
					out.print("<td><center>" + cstatus + "</center></td>");

				}

				out.print("</tr>");

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		out.print("</table>");

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
