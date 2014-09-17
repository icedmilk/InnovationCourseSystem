package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DataBase;

public class DeleteCourse extends HttpServlet
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

		String sql = "select * from sc";
		DataBase db = new DataBase();

		request.setCharacterEncoding("utf-8");
		String delId = request.getParameter("id");
		String sql2 = "delete from sc where cno = '" + delId + "'";
		sql = "delete from course where cno='" + delId + "' ";
		try
		{
			db.execute(sql2);
			db.execute(sql);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		response.sendRedirect("situation.jsp");

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
