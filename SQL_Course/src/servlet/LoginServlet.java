package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataBase;



public class LoginServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		ResultSet rs = null;

		String sno = request.getParameter("sno_login");
		String password = request.getParameter("password_login");
		String sql = "select * from student where sno = '" + sno
				+ "' and spwd = '" + password + "'";

		DataBase db = new DataBase();


		if (sno.equals("admin") && password.equals("admin"))
		{
			out.print("true");
			return;
		}

		try
		{
			rs = db.executeQuery(sql);
			if (rs.next())
			{
				out.print("true");
			}
			else
			{
				out.print("false");
				response.setHeader("refresh", "url=index.html");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		out.flush();
		out.close();
	}

}
