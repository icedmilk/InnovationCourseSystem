package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataBase;
import model.Star;


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
		String sno = request.getParameter("sno");
		String cno = request.getParameter("cno");
		sql = "delete from sc where sno = '" + sno + "' and cno='" + cno + "' ";
		Star star = new Star();
		
		String sql2 = "update student set star=star+" + star.getStar(sno, cno)
				+ " where sno='" + sno + "'";

		try
		{
			db.execute(sql);
			db.execute(sql2);
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
