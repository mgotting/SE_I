/**
 * Klasse die nach erfolgreicher Anmeldung erscheint
 */
package gui;

import javax.swing.*;

/**
 * @author Michael Gottinger, Sandra Speckmeier
 *
 */
public class Startmenu {
	JFrame frame;
	JPanel panel;
	JMenuBar bar;
	JMenu menu;
	JMenuItem benutzerAnlegen, benutzer�ndern, buchZur�ckgeben, buchAusleihen;
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	
	public final static String ACTION_BENUTZER_ANLEGEN = "BENUTZER_ANLEGEN";
	public final static String ACTION_BENUTZER_�NDERN = "BENUTZER_�NDERN";
	public final static String ACTION_BUCH_ZUR�CKGEBEN = "BUCH_ZUR�CKGEBEN";
	public final static String ACTION_BUCH_AUSLEIHEN = "BUCH_AUSLEIHEN";
	
	public MenuHandler controlMenu;
	
	public Startmenu(){
		controlMenu = new MenuHandler(this);
		panel = new JPanel();
		// Erstellen einer Men�leiste
		bar = new JMenuBar();
		// Erzeugung eines Objektes der Klasse JMenu
		menu = new JMenu("Menu");
		// Erzeugung eines Objektes der Klasse JMenuItem
		benutzerAnlegen = new JMenuItem("Benutzer anlegen");
		benutzer�ndern = new JMenuItem("Benutzer �ndern");
		buchZur�ckgeben = new JMenuItem("Buch zur�ckgeben");
		buchAusleihen = new JMenuItem("Buch ausleihen");
		panel.setLayout(null);
	}
	
	public void launchAuswahl(JFrame frame){
		this.frame = frame;
		frame.setJMenuBar(bar);
		// Men� wird der Men�leiste hinzugef�gt
		bar.add(menu);
		// Wir f�gen das JMenuItem unserem JMenu hinzu
		menu.add(benutzerAnlegen);
		benutzerAnlegen.setActionCommand(ACTION_BENUTZER_ANLEGEN);
		benutzerAnlegen.addActionListener(controlMenu);
		menu.add(benutzer�ndern);
		benutzer�ndern.setActionCommand(ACTION_BENUTZER_�NDERN);
		benutzer�ndern.addActionListener(controlMenu);
		menu.add(buchZur�ckgeben);
		buchZur�ckgeben.setActionCommand(ACTION_BUCH_ZUR�CKGEBEN);
		buchZur�ckgeben.addActionListener(controlMenu);
		menu.add(buchAusleihen);
		buchAusleihen.setActionCommand(ACTION_BUCH_AUSLEIHEN);
		buchAusleihen.addActionListener(controlMenu);
		frame.setTitle("Menu Punkt w�hlen");
		frame.setContentPane(panel);
	}

}
