package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DataBase;

public class WhatIChoose extends HttpServlet
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
		String sql = "select * from sc";
		ResultSet rs;

		// int i = 0;

		String sUsername = request.getParameter("user");
		sql = "select distinct course.*,sc.cstatus from course, sc "
				+ "where course.cno = sc.cno " + "and sc.sno='" + sUsername
				+ "' " + "order by cno";
		// out.print(sql);

		out.print("<table class=\"display\">");
		out.print("<thead><tr>");
		out.print("<th>课程编号</th>");
		out.print("<th>课程名称</th>");
		out.print("<th>课程类型</th>");
		out.print("<th>课余量</th>");
		out.print("<th>课容量</th>");
		if (db.status() == "预选阶段")
			out.print("<th>志愿等级</th>");

		out.print("</tr></thead>");
		try
		{

			rs = db.executeQuery(sql);

			while (rs.next())
			{

				int limited = rs.getInt("cmax");

				int n = limited - db.getCount(rs.getString("cno"));

				out.print("<tr>");
				out.print("<td>" + rs.getString("cno") + "</td>");
				out.print("<td>" + rs.getString("cname") + "</td>");
				out.print("<td>" + rs.getString("ctype") + "</td>");
				out.print("<td>" + n + "</td>");
				out.print("<td>" + limited + "</td>");

				if (status == "预选阶段")
					out.print("<td>" + rs.getInt("cstatus") + "</td>");

				out.print("</tr>");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

}
