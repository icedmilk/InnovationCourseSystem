package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DataBase;

public class AddCourse extends HttpServlet
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
		String sql = "select * from course";

		request.setCharacterEncoding("utf-8");
		String courseid = request.getParameter("courseId");
		String coursename = request.getParameter("courseName");
		String courseType = request.getParameter("courseType");

		int courselimit = new Integer(request.getParameter("limited"))
				.intValue();

		request.setCharacterEncoding("utf-8");

		try
		{


			sql = "insert into course set " + "cno='" + courseid + "', "
					+ "cname='" + coursename + "', " + "ctype='" + courseType
					+ "', " + "cmax=" + courselimit;

			db.executeUpdate(sql);
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
