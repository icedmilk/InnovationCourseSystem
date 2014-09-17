package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DataBase;

public class CoreAlgorithm extends HttpServlet
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

		DataBase db = new DataBase();

		String sql = "update status set period=";
		String sql2 = null;
		ResultSet rs;
		String status = request.getParameter("status");
		if (status.equals("fabu"))
		{
			sql += "'0'";
			sql += ",science='" + request.getParameter("science")
					+ "',language='" + request.getParameter("language")
					+ "',art='" + request.getParameter("art")
					+ "',management='" + request.getParameter("management")
					+ "',economy='" + request.getParameter("economy") + "'";
			sql2 = new String("update student set sinfo=null");
		}
		else
			if (status.equals("yuxuan"))
			{
				sql += "'1'";
				sql2 = new String("update student set sinfo=null");

				out.print(sql);
			}

			else
				if (status.equals("zhengxuan"))
				{
					/* 预存Cno */

					// 查询一共有多少门课
					String sqlcno = "select distinct cno from sc";
					int countCno = 0;
					String[] Cno = new String[100];
					ResultSet rsCno = db.executeQuery(sqlcno);

					// 参与算法的课程记入Cno数组
					try
					{
						while (rsCno.next())
						{
							Cno[countCno++] = rsCno.getString(1);
						}

						for (int k = 0; k < countCno; k++)
						{
							/*********************************** 执行选课算法 **********************************/

							String[] sqls = new String[20];// 保存查询每种志愿的sql语句
							int[] sqlNo = new int[20];// 第[数组下标]志愿的人数，0号存储最大志愿等级

							// 查询课容量
							ResultSet rsCmax = db
									.executeQuery("select cmax from course where cno = "
											+ Cno[k]);
							rsCmax.next();
							int cmax = rsCmax.getInt(1);// 记录课容量

							// 查询最大志愿等级
							sqls[0] = "select max(cstatus) from sc where cno = "
									+ Cno[k];
							ResultSet rsmax = db.executeQuery(sqls[0]);
							rsmax.next();
							sqlNo[0] = rsmax.getInt(1);// 最大志愿等级存入0号位

							for (int i = 1; i <= sqlNo[0]; i++)
							{
								// 查询第1-N志愿的人数，存入sqlNo数组
								sqls[i] = "select count(cstatus) from sc where cno = "
										+ Cno[k] + " and cstatus = " + i;
								ResultSet rs1 = db.executeQuery(sqls[i]);
								rs1.next();
								sqlNo[i] = rs1.getInt(1);
							}

							for (int i = 1; i <= sqlNo[0]; i++)
							{
								if (sqlNo[i] < cmax)
								{
									// 若志愿等级为i的课容量还够
									cmax -= sqlNo[i];// 剩余量
									// 此时更新为选取状态
									String sqlchoose = "update sc set cstatus = 0 where cstatus = "
											+ i + " and cno= " + Cno[k];
									db.executeUpdate(sqlchoose);

								}
								else
									if (sqlNo[i] > cmax)
									{
										// 若第i志愿等级的课容量不够了，志愿冲突的人里随机选择
										String sqltemp = "select  * from sc where cstatus = "
												+ i
												+ " order by rand() limit "
												+ cmax;

										ResultSet rstemp = db
												.executeQuery(sqltemp);
										String[] temp = new String[100];

										int count = 0;
										while (rstemp.next())
										{
											temp[count++] = rstemp.getString(1);// 记录被选中的学生学号
										}
										// out.print(count);

										// 更新为选中状态
										for (int j = 0; j < count; j++)
										{
											String sqlchoose = "update sc set cstatus = 0 where sno = "
													+ temp[j]
													+ " and cno = "
													+ Cno[k];
											db.executeUpdate(sqlchoose);
										}

										// 搜索剩下没选中的学生
										String setNull = "select sno from sc where cstatus != 0 and cno = "
												+ Cno[k];
										ResultSet rstemp2 = db
												.executeQuery(setNull);

										String[] temp2 = new String[100];
										int count2 = 0;
										while (rstemp2.next())
										{
											temp2[count2++] = rstemp2
													.getString(1);
										}

										// 更新状态为-1.代表为没选中
										for (int j = 0; j < count2; j++)
										{
											String sqlunchoose = "update sc set cstatus = -1 where sno = "
													+ temp2[j]
													+ " and cno = "
													+ Cno[k];
											db.executeUpdate(sqlunchoose);
										}

										break;
									}
							}
						}
						/*********************************** 执行选课算法 **********************************/
						sql += "'2'";
						String SnoHave = "select distinct sno from sc";
						ResultSet rsSno = db.executeQuery(SnoHave);
						String[] SnoSave = new String[100];
						int countSno = 0;
						while (rsSno.next())
						{
							SnoSave[countSno++] = rsSno.getString(1);
						}

						String[] sinfo = new String[100];
						for (int m = 0; m < countSno; m++)
						{
							String Traverse = "select course.cname,sc.cstatus from course,sc where course.cno = sc.cno and sno = "
									+ SnoSave[m];
							ResultSet SInfo = db.executeQuery(Traverse);
							sinfo[m] = "同学，您在预选阶段的选课情况如下：\n";
							while (SInfo.next())
							{

								sinfo[m] += SInfo.getString(1);
								sinfo[m] += "\t";

								if (SInfo.getString("cstatus").equals("0"))
									sinfo[m] += "成功选中\n";
								else
									sinfo[m] += "不幸落选\n";
								out.print(sinfo[m]);

							}
						}
						for (int m = 0; m < countSno; m++)
						{
							String updateSql = "update student set sinfo='"
									+ sinfo[m] + "' where sno=" + SnoSave[m];
							out.println(updateSql);
							db.executeUpdate(updateSql);
						}
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

		// sql+=" where science = 5";
		System.out.print(sql);
		db.executeUpdate(sql);
		if (sql2 != null)
			db.executeUpdate(sql2);
		response.sendRedirect("ChangeStatus.jsp?user="
				+ request.getParameter("sno"));

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
