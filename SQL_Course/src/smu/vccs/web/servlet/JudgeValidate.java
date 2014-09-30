package smu.vccs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JudgeValidate extends HttpServlet
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

		HttpSession session = request.getSession();
		String val = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
		val.toUpperCase();
		if(val.equals(request.getParameter("randomCode").toUpperCase()))
		{
			out.print("true");
		}
		else
			out.print("false");
		
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
