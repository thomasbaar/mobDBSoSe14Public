package algo;

import ui.Printer;

public class MoveStrategyFactory {
	
	static public MoveStrategy create(MoveStrategyKind kind, Printer printer){
		AbstractMoveStrategy result;
		switch (kind) {
		case recursive:
			result = new RecursiveMoveStrategy(printer);
			break;

		case backtracking:
			result = new BacktrackingMoveStrategy(printer);
			break;

		default:
			throw new IllegalStateException("Unknown MoveStrategyKind " + kind);
			
		}
		
		return result;
	}

}
