package smu.vccs.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseHandler
{
	// 是否选过此课
	public boolean havechosen(String sno, String cno)
	{
		DataBase db = new DataBase();
		String sql = "select count(*) from sc where cno='" + cno
				+ "' and sno='" + sno + "'";
		ResultSet rs;
		try
		{
			rs = db.executeQuery(sql);
			rs.next();

			if (rs.getInt(1) == 1)
				return true;
			else
				return false;
		}
		catch (SQLException e)
		{

			e.printStackTrace();

		}
		return false;

	}

	// 多少人选了某门课
	public int getCount(String cno)
	{
		DataBase db = new DataBase();
		String sql = "select count(distinct sno)  from sc where cno='" + cno
				+ "' and cstatus>=0";

		int amount = 0;

		try
		{

			ResultSet rs = db.executeQuery(sql);

			while (rs.next())
			{
				amount = rs.getInt(1);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return amount;
	}


}
