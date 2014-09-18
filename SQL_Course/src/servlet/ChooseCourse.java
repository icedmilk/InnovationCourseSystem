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
//				// 是否填写过该志愿
//				boolean flag = false;
//				String sqlAsp = "select cstatus from sc where sno='" + username
//						+ "'";
//
//				ResultSet rsSqlAsp;
//				try
//				{
//					rsSqlAsp = db.executeQuery(sqlAsp);
//					while (rsSqlAsp.next())
//					{
//
//						if (Integer.parseInt(request.getParameter("star")) == rsSqlAsp
//								.getInt(1))
//						{
//							flag = true;
//						}
//					}
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//
//				}
//
//				if (flag)
//				{
//					// %>
//					// <script>
//					// alert("你已经有过第"+<%=request.getParameter("asp")%>+"志愿的课程");
//					// window.location.href="choosecourse.jsp";
//					// </script>
//					// <%
//				}
//				else
//				{

				try
				{
					sql = "insert into sc(sno,cno,cstatus) values('"
							+ username
							+ "','"
							+ id
							+ "','"
							+ request.getParameter("star") + "')";
					db.executeUpdate(sql);

//								response.setHeader("refresh",
//										"0;url=choosecourse.jsp ");

				}
				catch (Exception e)
				{
					e.printStackTrace();

				}
			//}
		}
		else
			if (db.status() == "正选阶段")
			{
//					int limit = new Integer(request.getParameter("limit"))
//							.intValue();
//					int n = new Integer(request.getParameter("x")).intValue();

//					if (n < limit)
//					{

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
//					}
//					else
//					{
//						// %>
//						// <script language="javascript">
//						// alert("此课程人数已满！");
//						// window.location.href="choosecourse.jsp";
//						// </script>
//						// <%
//					}
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
