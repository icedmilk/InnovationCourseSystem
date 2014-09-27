package test;

import junit.framework.TestCase;

import model.DataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataBaseTest extends TestCase
{

	@Before
	public void testDataBase()
	{
		DataBase db = new DataBase();
		assertNotNull(db);
	}

	@Test
	public void testCourse_count()
	{
		assertEquals(2, DataBase.course_count("science"));
	}

	@Test
	public void testStatus()
	{
		assertEquals("预选阶段", new DataBase().status());
	}
	
	

	@Test(timeout = 100)
	@After
	public void testClose()
	{
		DataBase db = new DataBase();
		db.close();
	}

}
