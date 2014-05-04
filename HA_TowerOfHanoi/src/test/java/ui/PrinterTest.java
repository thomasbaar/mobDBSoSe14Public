package ui;

import controller.ControllerTestDataProvider;
import junit.framework.TestCase;

public class PrinterTest extends TestCase{

	
	ControllerTestDataProvider dataProvider;
	Printer printer;
	
	@Override
	public void setUp(){
		dataProvider = new ControllerTestDataProvider();
		dataProvider.createController(5);
		printer = dataProvider.getPrinter();
		
	}
	
	
	// test is output on console, but not formally checked here
	public void testPrintState_initialState(){
		printer.printState();
	}
	
}
