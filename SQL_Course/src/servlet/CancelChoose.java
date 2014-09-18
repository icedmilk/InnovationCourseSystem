package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DataBase;


public class CancelChoose extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		DataBase db = new DataBase();

		String sql = "select * from sc";
		String delUser = request.getParameter("sno");
		String delId = request.getParameter("cno");
		sql = "delete from sc where sno = '" + delUser + "' and cno='" + delId
				+ "' ";

		try
		{
			db.execute(sql);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		//response.sendRedirect("view.jsp?user=" + delUser + "");

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
