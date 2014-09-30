package smu.vccs.test;

import smu.vccs.model.CourseHandler;
import smu.vccs.model.Star;
import junit.framework.TestCase;

public class ModelTest extends TestCase
{
	public void testGetStar()
	{
		Star star = new Star();
		int stars = star.getStar("201110314047", "5");
		assertEquals(2, stars);

	}

	@org.junit.Test
	public void testAssertEquals()
	{
		org.junit.Assert.assertEquals("error Text", "text", "text");
		
		CourseHandler ch = new CourseHandler();
		org.junit.Assert.assertTrue(ch.havechosen("201110314047", "5"));
		org.junit.Assert.assertFalse(ch.havechosen("201110314047", "123"));
		
		org.junit.Assert.assertEquals(1, ch.getCount("5"));
		org.junit.Assert.assertEquals(0, ch.getCount("2"));
		
	}
}
