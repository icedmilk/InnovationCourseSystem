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



public class RegisterServlet extends HttpServlet
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
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		DataBase db = new DataBase();

		request.setCharacterEncoding("UTF-8");
		String sno = request.getParameter("sno");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sql = "select count(*) from student where sno='" + sno + "' ";
		ResultSet rs = db.executeQuery(sql);

		try
		{
			if (rs.next())
			{
				if (rs.getInt(1) == 0)
				{
					if (password != null)
					{
						sql = "insert into student(sno,spwd,sname,star) values('"
								+ sno
								+ "','"
								+ password
								+ "','"
								+ name
								+ "','"
								+ 24 + "')";

						db.executeUpdate(sql);
					}
					out.print("true");
				}
				else
				{
					out.print("false");
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		out.flush();
		out.close();
	}

}
