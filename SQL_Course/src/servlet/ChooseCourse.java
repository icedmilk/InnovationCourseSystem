package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DataBase;

public class ChooseCourse extends HttpServlet
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
		String sql = "select * from sc";
		String id = request.getParameter("cno");
		String username = request.getParameter("sno");


		if (db.status() == "预选阶段")
		{

				try
				{
					sql = "insert into sc(sno,cno,cstatus) values('"
							+ username
							+ "','"
							+ id
							+ "','"
							+ request.getParameter("star") + "')";
					db.executeUpdate(sql);


				}
				catch (Exception e)
				{
					e.printStackTrace();

				}
		}
		else
			if (db.status() == "正选阶段")
			{

					try
					{
						sql = "insert into sc(sno,cno) values('"
								+ username + "','" + id + "') ";
						db.executeUpdate(sql);

						out.print("success");

					}
					catch (Exception e)
					{
						e.printStackTrace();

					}
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
