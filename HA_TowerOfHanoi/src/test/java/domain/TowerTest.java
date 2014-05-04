package domain;

import junit.framework.TestCase;

public class TowerTest extends TestCase{
	
	// some data we want to use in the test
	private Ring r1;
	private Ring r2;
	private Ring r3;
	
	// object under test
	private Tower out;
	
	
	// invoked prior to the execution of every test-method 
	@Override
	public void setUp(){
		out = new Tower();
		r1 = new Ring(1);
		r2 = new Ring(2);
		r3 = new Ring(3);
	}

	 
	
	public void testPush_single() throws Exception{
		boolean result = out.push(r3);
		assertTrue(result);
	}
	
	public void testPush_rightorder() throws Exception{
		boolean result1 = out.push(r3);
		assertTrue(result1);
		boolean result2 = out.push(r2);
		assertTrue(result2);
	}
	
	public void testPush_sametwice() throws Exception{
		boolean result1 = out.push(r3);
		assertTrue(result1);
		boolean result2 = out.push(r3);
		assertFalse(result2);
	}
	
	
	public void testPush_wrongorder() throws Exception{
		boolean result1 = out.push(r1);
		assertTrue(result1);
		boolean result2 = out.push(r2);
		assertFalse(result2);
	}
	
	public void testPush_null() throws Exception{
		boolean result1 = out.push(r1);
		assertTrue(result1);
		try{
		out.push(null);
		fail();
		}catch (IllegalArgumentException e) {
		}catch (Exception e) {
			fail();
		}
	}
	
	
	
	public void testPop_Empty() throws Exception{
		assertNull(out.pop());
	}
	

	public void testPop_PushPop() throws Exception{
		out.push(r1);
		assertEquals(r1, out.pop());
	}
	

	public void testPop_PushPushPop() throws Exception{
		out.push(r3);
		out.push(r2);
		assertEquals(r2, out.pop());
	}
	
	
	public void testPop_PushPushPopPop() throws Exception{
		out.push(r3);
		out.push(r2);
		out.pop();
		assertEquals(r3, out.pop());
	}
	
	public void testPop_PushPushPopPopPop() throws Exception{
		out.push(r3);
		out.push(r2);
		out.pop();
		out.pop();
		assertNull(out.pop());
	}
	
	
	public void testGetRingsizeAtLevel_notPositiv() throws Exception{
		try{
			out.getRingsizeAtLevel(0);
			fail();
		}catch (IllegalArgumentException e) {}
		catch (Exception e) {fail();}
	}

	
	public void testGetRingsizeAtLevel_PushPush() throws Exception{
		out.push(r3);
		out.push(r2);
		assertEquals(3, out.getRingsizeAtLevel(1));
		assertEquals(2, out.getRingsizeAtLevel(2));
		assertEquals(0, out.getRingsizeAtLevel(3));
	}

	public void testGetStateEncoding_PushPush() throws Exception{
		out.push(r3);
		out.push(r2);
		assertEquals(12, out.getStateEncoding());
	}

}
