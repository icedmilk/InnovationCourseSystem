package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class common {

	public String courseid;
	int amount;
	
	public String getCourseid() 
	{
		return courseid;
	}

	public void setCourseid(String courseid) 
	{
		this.courseid = courseid;
	}
	
	public int getAmount() 
	{
		return amount;
	}

	public void setAmount(int amount) 
	{
		this.amount = amount;
	}
	
	public int  count() {
		
	    String driverName = "com.mysql.jdbc.Driver";
		String strConn = "jdbc:mysql://localhost:3306/choose_course?useUnicode=true&characterEncoding=utf-8";
		String dbUserName = "root";
		String dbPassword = DataBase.dbpassword;
		
//		String dbPassword = "root";
		String sql="select * from sc";

		Connection con;
		Statement stat;
		ResultSet rs;

		sql="select count(distinct sno)  from sc where cno='"+courseid+"' and cstatus>=0";

		try 
		{
			Class.forName(driverName);
			con = DriverManager.getConnection(strConn, dbUserName, dbPassword);
			stat = con.createStatement();

			rs = stat.executeQuery(sql);

			while(rs.next())
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
