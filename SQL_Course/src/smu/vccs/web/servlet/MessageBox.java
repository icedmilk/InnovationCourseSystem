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



public class MessageBox extends HttpServlet
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
		String Sno = request.getParameter("user");
		String Info = "select sinfo from student where sno = " + Sno;
		ResultSet rsInfo = db.executeQuery(Info);
		try
		{
			rsInfo.next();
			String InfoSno;

			InfoSno = rsInfo.getString(1);
			if (rsInfo.getString(1) != null)
			{
				String[] splited = InfoSno.split("：");
				InfoSno = splited[0] + "：</br></br><strong>" + splited[1]
						+ "</strong>";
				out.print(InfoSno);
			}
			else
				out.print("<center>您的收件箱中暂无任何消息</center>");
			db.close();
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
