package algo;

import ui.Printer;
import domain.Place;
import domain.Ring;

class RecursiveMoveStrategy extends AbstractMoveStrategy {

	public RecursiveMoveStrategy(Printer printer) {
		super(printer);
	}

	@Override
	public void move(Place from, Place to, Place middle, int n) {
		if (n <= 0) {
			throw new IllegalArgumentException(
					"wrong parameter value for size=" + n);
		}

		if (n == 1) {
			Ring r = from.getTower().pop();
			if (r != null) {
				to.getTower().push(r);
			}else{
				//TODO: issue warning about unexpected situation
			}
			printer.printState();
			return;
		}

		move(from, middle, to, n - 1);
		move(from, to, middle, 1);
		move(middle, to, from, n - 1);
		
		
	}

}
