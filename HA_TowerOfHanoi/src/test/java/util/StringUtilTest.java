package util;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase{

	
	public void testMultiplyString_negative() throws Exception{
		assertEquals(true, StringUtil.multiplyString(-3, "bla").isEmpty());
	}
	
	public void testMultiplyString_null() throws Exception{
		assertEquals(true, StringUtil.multiplyString(0, "bla").isEmpty());
	}
	
	
	public void testMultiplyString_one() throws Exception{
		assertEquals(true, "bla".equals(StringUtil.multiplyString(1, "bla")));
	}
	
	
	public void testMultiplyString_five() throws Exception{
		assertEquals(true, "blablablablabla".equals(StringUtil.multiplyString(5, "bla")));
	}
	
	public void testMultiplyString_five_char() throws Exception{
		assertEquals(true, "*****".equals(StringUtil.multiplyString(5, "*")));
	}
	
	public void testMultiplyString_five_space() throws Exception{
		assertEquals(true, "     ".equals(StringUtil.multiplyString(5, " ")));
	}
	
	
}
