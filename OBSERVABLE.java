
//Dieser Import stellt eine vorprogrammierte Liste zur Verfügung.
import java.util.*;

public class OBSERVABLE {
	// In dieser Liste werden alle OBSERVER gespeichert. Was das für eine Liste ist
	// musst du nicht verstehen.
	// Man fügt etwas hinzu mit: liste.add(Objekt)
	// Man löscht etwas mit liste.remove(Objekt)

	private List<OBSERVER> liste = new LinkedList<OBSERVER>();

	public void hinzufuegen(OBSERVER o) {
		liste.add(o);
	}

	public void loeschen(OBSERVER o) {
		liste.remove(o);
	}

	/*
	 * Wenn die benachrichtigen-Methode aufgerufen wird, werden alle OBSERVER in der
	 * Liste informiert, dass es ein Updare gibt, d.h. man iteriert durch die liste
	 * mit einer for-Schleife und ruft auf jedem Objekt die update() Funktion auf
	 * Ein Objekt an Stell i bekommt man über: liste.get(i)
	 */
	public void benachrichtigen() {
		liste.forEach(a ->{
			a.update();
		});
	}

}
