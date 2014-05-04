package domain;

import junit.framework.TestCase;

public class RingTest extends TestCase{

	
	public void testConstructor_Positive() throws Exception{
		Ring out = new Ring(3);
		assertNotNull(out);
	}
	
	public void testConstructor_Null() throws Exception{
		try{ 
		Ring out = new Ring(0);
		fail("Exception expected");
		}catch (Exception e) {
		}
	}
	 
	public void testGetSize() throws Exception{
		Ring out = new Ring(3);
		assertEquals(3, out.getSize());
	}

	public void testIsSmallerThan_smaller() throws Exception{
		Ring out_small = new Ring(3);
		Ring out_big = new Ring(5);
		
		assertTrue(out_small.isSmallerThan(out_big));
	}


	public void testIsSmallerThan_equal() throws Exception{
		Ring out1 = new Ring(3);
		Ring out1prime = new Ring(3);
		
		assertFalse(out1.isSmallerThan(out1prime));
	}

}
