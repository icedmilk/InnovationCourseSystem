package smu.vccs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smu.vccs.model.DataBase;


public class SuggestAddCourse extends HttpServlet
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

		out.print("<tr><td><center><strong>(推荐开课： ");

		int a = DataBase.course_count("science");
		int b = DataBase.course_count("language");
		int c = DataBase.course_count("art");
		int d = DataBase.course_count("management");
		int e = DataBase.course_count("economy");

		int max = 0;

		max = e > (d > (c > (a > b ? a : b) ? c : (a > b ? a : b)) ? d
				: (c > (a > b ? a : b) ? c : (a > b ? a : b))) ? e
				: (d > (c > (a > b ? a : b) ? c : (a > b ? a : b)) ? d
						: (c > (a > b ? a : b) ? c : (a > b ? a : b)));
		if (max == a)
		{
			out.print("计算科学类");
		}
		else
			if (max == b)
			{
				out.print("语言类");
			}
			else
				if (max == c)
				{
					out.print("人文艺术类");
				}
				else
					if (max == d)
					{
						out.print("管理类");
					}
					else
						if (max == e)
						{
							out.print("经济类");
						}

		out.print(")</strong>尚缺课程如下：</center></td></tr><tr><td>计算科学类");
		out.print(DataBase.course_count("science"));
		out.print("&nbsp;人文艺术类" + DataBase.course_count("art") + "&nbsp; <br/>语言类"
				+ DataBase.course_count("language") + "&nbsp; 管理类"
				+ DataBase.course_count("management") + "&nbsp;经济类"
				+ DataBase.course_count("economy") + "</td></tr>");

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
