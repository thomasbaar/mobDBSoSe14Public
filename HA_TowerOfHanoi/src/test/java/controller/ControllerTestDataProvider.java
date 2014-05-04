package controller;
import algo.MoveStrategyKind;
import ui.Printer;



/**
 * Helper class to initialize a <code>Controller</code> and to access (usually invisible) data
 * from that object. 
 * @author baar
 *
 */
public class ControllerTestDataProvider {

	private Controller controller;
	
	public  void createController(int noOfRings){
		controller = new Controller();
		controller.setUp(noOfRings);
		controller.setAlgorithm(MoveStrategyKind.recursive);
	}
	
	public Printer getPrinter(){
		if (controller==null){
			throw new IllegalStateException("controller not set");
		}
		return controller.printer;
	}
	
	public Controller getController(){
		if (controller==null){
			throw new IllegalStateException("controller not set");
		}
		return controller;
	}
	
}
