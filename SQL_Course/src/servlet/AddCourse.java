package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DataBase;
import bean.course;

public class AddCourse extends HttpServlet
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
		String sql = "select * from course";
		ResultSet rs;
		request.setCharacterEncoding("utf-8");
		String courseid = request.getParameter("courseId");
		String coursename = request.getParameter("courseName");
		String courseType = request.getParameter("courseType");

		int courselimit = new Integer(request.getParameter("limited"))
				.intValue();

		course newCourse = new course();
		newCourse.setCourseId(courseid);
		newCourse.setCourseName(coursename);
		newCourse.setLimited(courselimit);
		newCourse.setCtype(courseType);

		request.setCharacterEncoding("utf-8");
		sql = "select * from course where cno=" + newCourse.getCourseId();
		rs = db.executeQuery(sql);
		try
		{
			if (rs.next())
			{
				// %>
				// <script>
				// alert("已经有过该课程编号的课程");
				// window.location.href="addcourse.jsp";
				// </script>
				// <%
			}

			sql = "insert into course set " + "cno='" + newCourse.getCourseId()
					+ "', " + "cname='" + newCourse.getCourseName() + "', "
					+ "ctype='" + newCourse.getCtype() + "', " + "cmax="
					+ newCourse.getLimited();

			db.executeUpdate(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// %>
		// <script>
		// alert("添加成功~喵！");
		// window.location.href="addcourse.jsp";
		// </script>
		// <%

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

}
