package algo;

import java.util.HashSet;
import java.util.Set;

import ui.Printer;
import domain.Place;
import domain.Tower;


////The general form of a backtracking algorithm:
//	
//	// Allgemeines Schema f�r rekursives Backtracking
//    // Die Methode soll genau dann true zurückgeben, wenn eine Lösung gefunden wurde
//
//    public boolean sucheLoesung( /* Parameter, um einen Schritt/aktuelle Position
//zu merken */ ) {
//
//      // Zähle alle möglichen Schritte von der aktuellen Position systematisch auf
//      while ( /* Noch nicht fertig&&  ex. noch Teillösungsschritte */ ) {
//        // wähle den nächsten Schritt aus
//        if ( /* Schritt ist gültig */ ) {
//         // erweitere Lösung um diesen Schritt
//          if ( /* Lösung noch nicht gefunden  */ ) {
//            if ( sucheLoesung( /* neuer Schritt/neue Position */ ) ) {
//              return true;
//            } else {
//               // Mache Schritt wieder rückgängig (Backtracking)
//            }
//          } else {
//            return true;
//          }
//        }
//      }
//      return false;
//   }
//
//
//copyright Prof. Dr. Pape, HS Karlsruhe

class BacktrackingMoveStrategy extends AbstractMoveStrategy {

	Tower t1;
	Tower t2;
	Tower t3;

	// states that have been already visited
	Set<String> visitedStatesRepresentation = new HashSet<String>();
	String startStateRepresentation="";
	String finalStateRepresentation="";
	
	
	public BacktrackingMoveStrategy(Printer printer) {
		super(printer);
	}

	// Assumption: all rings on from, and to, middle are empty
	@Override
	public void move(Place from, Place to, Place middle, int noOfRings) {
		t1 = from.getTower();
		t2 = to.getTower();
		t3 = middle.getTower();
		
		startStateRepresentation = extractState(t1, t2, t3);
		finalStateRepresentation = extractState(t2, t1, t3);
		

	}
	
	// we encode the state of each place by a number and 
	// aggregate these results as a string, so that different states
	// can easily be detected.
	private String extractState(Tower t1, Tower t2, Tower t3){
		return new State(t1,t2,t3).getRepresentation();
	}

}


