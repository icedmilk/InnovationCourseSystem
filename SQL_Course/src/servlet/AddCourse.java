package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataBase;

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
		String courseid = request.getParameter("cno");
		String coursename = request.getParameter("cname");
		String courseType = request.getParameter("ctype");

		int courselimit = new Integer(Integer.parseInt(request
				.getParameter("limit")));

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
