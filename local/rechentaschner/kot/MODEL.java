package local.rechentaschner.kot;
/*
 * Die Klasse MODEL enthält alle Daten. Da sie die View (bzw.
 * allgemein die OBSERVER) über Updates informieren muss, erbt
 * sie von OBSERVABLE (mehr dazu im Skript).
 */
public class MODEL extends OBSERVABLE {
	private int zaehler;

	public MODEL() {
		zaehler = 0;
	}

	/*
	 * Wenn der Zähler verändert werden soll, passiert das hier. Anschließend
	 * werden durch den Aufruf der benachrichtigen-Methode in der Superklasse
	 * OBSERVABLE alle OBSERVER benachrichtigt, die sich in die entsprechende Liste
	 * eingetragen haben.
	 */
	public void inkrementieren(int n) {
		zaehler += n;
		benachrichtigen();

	}

	public void dekrementieren() {
		zaehler--;
		benachrichtigen();
	}

	/*
	 * Durch diese Methode kann sich die VIEW den aktuellen Zählerstand holen.
	 * ACHTUNG: Statt Object kann man auch int schreiben. Die Klasse Object
	 * ermöglicht aber die Rückgabe, aller möglichen Datentypen und ist dadurch
	 * in Anwendungen allgemein flexibler.
	 */
	public Object inhaltHolen() {
		return zaehler;
	}

	
}