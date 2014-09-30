package smu.vccs.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class TestAll extends TestSuite
{
	public static Test suite()
	{
		TestSuite test = new TestSuite("tt");
		test.addTestSuite(ModelTest.class);
		test.addTestSuite(DataBaseTest.class);
		return test;
	}
	public static void main(String[] args)
	{
		TestRunner.run(suite());
	}
	

}
