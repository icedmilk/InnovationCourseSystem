package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Star
{
	public int getStar(String sno, String cno)
	{
		DataBase db = new DataBase();
		String sql = "select cstatus from sc where sno='"
				+ sno + "' and cno='" + cno + "'";
		ResultSet rs = db.executeQuery(sql);
		
		try
		{
			rs.next();
			return Integer.parseInt(rs.getString("cstatus"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;

	}
}
