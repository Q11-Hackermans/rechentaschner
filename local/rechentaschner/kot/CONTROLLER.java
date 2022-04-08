package local.rechentaschner.kot;
/*
 * Das erste importierte Paket ist wichtig, da der Controller ohne das event-Paket 
 * nicht wüsste, was ein ActionListener ist. Das zweite Paket braucht man nur, wenn
 * man Nutzereingaben einlesen möchte (siehe in Methode actionPerformed).
 */

import java.awt.event.*;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

/*
 * Die Klasse implementiert das Interface ActionListener, da sie dadurch auf Aktionen
 * in der View reagieren kann. Auf welche Aktionen wie reagiert wird, wird in der 
 * Methode actionPerformed festgelegt. 
 * Tipp: Wenn ihr eine Mausinteraktion haben wollt, könnt ihr dafür die Methode 
 * mouseClicked(MouseEvent e) verwenden. Ein ActionListener erkennt sie auch 
 * (da MouseListener von ActionListener erbt).
 */

public class CONTROLLER implements ActionListener {
	private VIEW view;
	private MODEL model;

	/*
	 * Der Controller erstellt ein Model und eine View und übergibt dabei das Model
	 * an die View. Anschließend fügt er die View in die Observer-Liste des Models
	 * ein. Zuletzt registriert er sich bei den beiden Buttons der View (dort werden
	 * passende Methoden zur Verfügung gestellt).
	 */

	public CONTROLLER() {
		model = new MODEL();
		view = new VIEW(model);
		// Füge dem model die view als Observer über die entsprechende Methode des
		// models hinzu
		model.hinzufuegen(view);

		// Man muss die ActionListener anmelden.
		view.addPlusListener(this);
		view.addMinusListener(this);
		view.addFunListener(this);
		view.addEnterListener(this);
	}

	/*
	 * In dieser Methode wird festgelegt, wie auf bestimmte Aktionen reagiert werden
	 * soll. Über die Methode getActionCommand() kann abgefragt werden, welcher
	 * Knopf gedrückt wurde. Es wird dann die passende Methode im Model aufgerufen.
	 * ACHTUNG: In dieser Variante der Methode ist auch schon das Einlesen von
	 * Werten enthalten. Wer den ursprünglichen Code ohne diese Funktion haben
	 * möchte, musst im ersten if-Teil nur die Zeile model.inkrementieren()
	 * schreiben und die entsprechende Methode im Model anpassen.
	 * 
	 * Wird in der View ein Button gedrückt, bei dem ein ActionListener hinterlegt
	 * ist, so wird diese Methode aufgerufen.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		// Falls der gedrückte Button der + Button ist.
		if (e.getActionCommand().equals("+")) {
			try {
				/*
				 * Es wird ein Eingabefenster (JOptionPane) mit der entsprechenden Nachricht
				 * angezeigt. Der Inhalt wird auf einen Integer gecastet, falls möglich. Ist es
				 * nicht möglich, weil der Benutzer z.B. einen Text eingegeben hat, kommt der
				 * catchBlock zur Aktion: Er greift diesen Fehler ab un zeigt die Fehlermeldung
				 * an.
				 * 
				 * Funktioniert es, so wird die zahl um den eingegeben Wert erhöht
				 */
				int n = Integer.parseInt(JOptionPane.showInputDialog("Welcher Wert soll eingefügt werden?"));

				// Erhöhe unseren Wert um n. Verwende eine passende Methode im model
				model.inkrementieren(n);
			} catch (NumberFormatException ne) {
				JOptionPane.showMessageDialog(null, "Bitte eine natürliche Zahl eingeben");
			}

		}
		if (e.getActionCommand().equals("-")) {
			// Verringere den Wert um eins und nutze dazu eine passende Methode im model.
			model.dekrementieren();
		}

		if (e.getActionCommand().equals("🔄")) {

			Random rand = new Random();

			model.inkrementieren(rand.nextInt(500) - 250);
			view.switchLabels();
			System.out.println("switch");
		}

		if (e.getActionCommand().equals("⮒")) {
			
			String equation = view.getTextFieldContent();
			ScriptEngineManager mgr = new ScriptEngineManager();
		    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		    String res = "";
			try {
				res = (String) engine.eval(equation);
			} catch (ScriptException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.inkrementieren(Integer.valueOf(res));
		}

	}

}
