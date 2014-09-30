package smu.vccs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSessionCount extends HttpServlet
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
		
		int count = smu.vccs.web.listener.SessionCounter.getActiveSessions();
		
		out.print(count);
		//System.out.println("count:" + count);
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
