package smu.vccs.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener
{
	private static int activeSessions = 0;

	public static int getActiveSessions()
	{
		return activeSessions;
	}

	@Override
	public void sessionCreated(HttpSessionEvent se)
	{
		System.out.println("建立Session");
		activeSessions++;

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se)
	{
		System.out.println("销毁Session");
		activeSessions--;
	}

}
