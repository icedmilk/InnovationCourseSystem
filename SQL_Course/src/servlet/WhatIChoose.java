package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DataBase;
import bean.common;
import bean.course;

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

		//int i = 0;

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
		out.print("<th>操作</th>");

		out.print("</tr></thead>");
		try
		{

			rs = db.executeQuery(sql);

			while (rs.next())
			{
				common addnum = new common();
				course vcourse = new course();

				vcourse.setCourseId(rs.getString("cno"));
				addnum.setCourseid(vcourse.getCourseId());
				vcourse.setCourseName(rs.getString("cname"));
				vcourse.setLimited(rs.getInt("cmax"));
				vcourse.setCtype(rs.getString("ctype"));

				vcourse.setAsp(rs.getInt("cstatus"));

				int n = vcourse.getLimited() - addnum.count();

				out.print("<tr>");
				out.print("<td>" + vcourse.getCourseId() + "</td>");
				out.print("<td>" + vcourse.getCourseName() + "</td>");
				out.print("<td>" + vcourse.getCtype() + "</td>");
				out.print("<td>" + n + "</td>");
				out.print("<td>" + vcourse.getLimited() + "</td>");

				if (status == "预选阶段")
					out.print("<td>" + vcourse.getAsp() + "</td>");

				out.print("<td><a href=\"delete.jsp?id=<%=vcourse.getCourseId() %>&user=<%=sUsername %>\" onClick=\"delcfm()\">退课</a></td>");

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
