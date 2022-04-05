package local.rechentaschner.kot;
/*
 * Die hier importieren Pakete sind wichtig, um die grafische Darstellung
 * zu ermöglichen. Das event-Paket wird benötigt, damit man Methoden schreiben
 * kann, mit denen sich der Controller an den Buttons "anmelden" kann.
 * 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * Die Klasse VIEW erbt von JFrame (einer Art Bilderrahmen, um Inhalte anzeigen
 * zu können) und implementiert das Interface OBSERVER, um vom Model benachrichtigt
 * werden zu können, wenn ein Update ansteht. Mehr dazu im Skript.
 */
public class VIEW extends JFrame implements OBSERVER {
	private int zahl = 0;
	private MODEL model;
	private JLabel label;
	private boolean funMode = false;

	private JButton buttonPlus;
	private JButton buttonMinus;
	private JButton buttonFun;

	/*
	 * Wenn eine View erstellt wird, muss ein zugehöriges Model angegeben werden.
	 * Das erledigt bei uns der Controller. Das Panel, das erstellt wird, kann man
	 * sich vereinfacht als eine Art Brett mit Nägeln dran vorstellen, das in den
	 * Bilderrahmen (das JFrame bzw. hier die View selbst) gehängt wird, um daran
	 * dann die konkreten Elemente zu hängen. Diese Elemente sind ein Label (eine
	 * Art "Anzeige", auf der im Beispiel die Zahl zu sehen ist) und zwei Buttons,
	 * ein Plus- und ein Minus-Button.
	 */

	public VIEW(MODEL m) {
		funMode = true;
		
		model = m;
		
		m.hinzufuegen(this);

		JPanel panel = new JPanel();

		// Dem Panel wird ein bestimmtes Layout gegeben. Um mehr über Layouts zu
		// erfahren,
		// empfehle ich ggf eine Internetrecherche oder eine Nachfrage im Unterricht.
		panel.setLayout(new FlowLayout());
		// Das Label wird erstellt und soll die Zahl anzeigen.
		label = new JLabel("" + model.inhaltHolen());
		// Die Schriftart des Labels wird festgelegt.
		panel.setFont(new Font("Serif", Font.PLAIN, 50));
		// Die Position des Texts innerhalb des Labels wird festgelegt.
		label.setVerticalAlignment(SwingConstants.CENTER);
		// Das Label wird zum Panel hinzugefügt.
		panel.add(label);

		// Die Buttons werden erstellt und dem Panel hinzugefügt.
		buttonPlus = new JButton("+");
		// Der Button wird dem panel hinzugefügt
		panel.add(buttonPlus);

		buttonMinus = new JButton("-");
		// Der Button wird dem panel hinzugefügt
		panel.add(buttonMinus);

		// Fun button
		buttonFun = new JButton("hm");
		panel.add(buttonFun);

		// Das Panel wird in den Bilderrahmen/JFrame gehängt. Davor wird es nicht
		// angezeigt !
		this.add(panel);

		// Die Größe des JFrames wird festgelegt (Breite, Höhe in Pixeln).
		// Verwende die Methode this.setSize(Breite, höhe)
		this.setSize(500, 150);

		// Das JFrane soll für den Nutzer sichtbar sein.
		// Verwende die Methodethis.setVisible(true oder false);
		this.setVisible(true);
		// Set title
		this.setTitle("The Rechentaschner");

	}

	/*
	 * In der Methode update wird festgelegt, was passieren soll, wen ein Update
	 * ansteht. In diesem Fall soll der aktuelle Wert des Zählers vom Model geholt
	 * werden, in ein int verwandelt werden (er wird im Beispiel allgemein als
	 * "Object" gesendet und muss erst wieder in das passende Datenformat gebracht
	 * werden). Anschließend wird der Inhalt des Labels neu gesetzt (und dadurch
	 * aktualisiert).
	 */

	@Override
	public void update() {
		label.setText(model.inhaltHolen().toString());
	}

	public void switchLabels() {
		if (funMode) {
			buttonPlus.setText("-");
			buttonMinus.setText("+");
			funMode = false;
		} else {
			buttonPlus.setText("+");
			buttonMinus.setText("-");
			funMode = true;
		}
	}

	/*
	 * Die letzten beiden Methoden dienen dazu, dass sich ein ActionListener (in
	 * unserem Fall der Controller) an einem Button "anmelden" kann, um dann
	 * informiert zu werden, wenn er gedrückt wird.
	 */
	public void addPlusListener(ActionListener listenForPlusButton) {
		buttonPlus.addActionListener(listenForPlusButton);
	}

	public void addMinusListener(ActionListener listenForMinusButton) {
		buttonMinus.addActionListener(listenForMinusButton);
	}

	public void addFunListener(ActionListener listenForFunButton) {
		buttonFun.addActionListener(listenForFunButton);
	}
}
