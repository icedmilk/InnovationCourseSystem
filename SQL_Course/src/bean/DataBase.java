package bean;

import java.sql.*;

public class DataBase
{
	private String driverStr = "com.mysql.jdbc.Driver";
	// private String
	// connStr="jdbc:mysql://localhost:3306/lc284?useUnicode=true&characterEncoding=utf-8";
	// private String dbusername="lc284";
	// private String dbpassword="lc284";
	private String connStr = "jdbc:mysql://localhost:3306/choose_course?useUnicode=true&characterEncoding=utf-8";
	private String dbusername = "root";
	// public static String dbpassword = "";
	public static String dbpassword = "root";
	private Connection conn = null;
	private Statement stmt = null;

	public DataBase()
	{
		try
		{
			Class.forName(driverStr);
			conn = DriverManager.getConnection(connStr, dbusername, dbpassword);
			stmt = conn.createStatement();
		}
		catch (Exception ex)
		{
			System.out.println("Can not connect to the db" + ex);
		}
	}

	public int course_count(String type)
	{
		ResultSet rs;
		ResultSet rs2;
		try
		{

			rs2 = stmt.executeQuery("select " + type + " from status");
			rs2.next();
			int a = Integer.parseInt(rs2.getString(1));
			rs = stmt.executeQuery("select count(*) from course where ctype='"
					+ type + "'");
			rs.next();

			return a - rs.getInt(1);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

	public String status()
	{
		String sql = "select period from status";
		ResultSet rs;
		try
		{
			rs = stmt.executeQuery(sql);
			String period = "";
			if (rs.next())
			{
				period = rs.getString(1);
			}
			int a = Integer.parseInt(period);
			if (a == 0)
				period = "0";
			else
				if (a == 1)
					period = "1";
				else
					period = "2";
			return period;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public int executeUpdate(String s)
	{
		int result = 0;
		try
		{
			result = stmt.executeUpdate(s);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		return result;
	}

	public ResultSet executeQuery(String s)
	{
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(s);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		return rs;
	}

	public void execute(String s)
	{
		try
		{
			stmt.execute(s);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

	public void close()
	{
		try
		{
			stmt.close();
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public boolean havechosen(String sno, String cno)
	{
		String sql = "select count(*) from sc where cno='" + cno
				+ "' and sno='" + sno + "'";
		ResultSet rs;
		try
		{
			rs = stmt.executeQuery(sql);
			rs.next();

			if (rs.getInt(1) == 1)
				return true;
			else
				return false;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;

	}

	public static void main(String[] args) throws SQLException
	{
		DataBase db = new DataBase();
		ResultSet rs = db.executeQuery("select * from status");
		rs.next();
		System.out.print(rs.getInt(2));

	}
}
