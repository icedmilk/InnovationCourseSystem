package smu.vccs.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieHandler
{
	public static String getValue(HttpServletRequest request, String key)
	{
		Cookie[] ck = request.getCookies();
		for (Cookie cookie : ck)
		{
			if (cookie.getName().equals(key))
				// out.print(URLDecoder.decode(cookie.getValue(), "utf-8"));
				return cookie.getValue();
		}
		return null;
	}
}
