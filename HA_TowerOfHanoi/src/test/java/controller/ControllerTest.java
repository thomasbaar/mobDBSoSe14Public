package controller;

import junit.framework.TestCase;

public class ControllerTest extends TestCase{

	ControllerTestDataProvider dataProvider;
	Controller out;
	
	@Override
	public void setUp(){
		dataProvider = new ControllerTestDataProvider();
		dataProvider.createController(5);
		out = dataProvider.getController();
		
	}
	
	
	// test is output on console, but not formally checked here
	public void testMove(){
		out.start();
	}

	
}
