package smu.vccs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smu.vccs.model.DataBase;

public class CourseNumberValidate extends HttpServlet
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
		String sql = "select count(*) from course where cno='" + request.getParameter("cno") + "' ";
		ResultSet rs = db.executeQuery(sql);
		try
		{
			rs.next();
			int count = rs.getInt(1);
			if(count==0)
				out.print("true");
			else
				out.print("false");
		}
		catch (SQLException e)
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
