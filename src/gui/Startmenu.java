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
	JMenuItem benutzerAnlegen, benutzerÄndern, buchZurückgeben, buchAusleihen;
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	
	public final static String ACTION_BENUTZER_ANLEGEN = "BENUTZER_ANLEGEN";
	public final static String ACTION_BENUTZER_ÄNDERN = "BENUTZER_ÄNDERN";
	public final static String ACTION_BUCH_ZURÜCKGEBEN = "BUCH_ZURÜCKGEBEN";
	public final static String ACTION_BUCH_AUSLEIHEN = "BUCH_AUSLEIHEN";
	
	public MenuHandler controlMenu;
	
	public Startmenu(){
		controlMenu = new MenuHandler(this);
		panel = new JPanel();
		// Erstellen einer Menüleiste
		bar = new JMenuBar();
		// Erzeugung eines Objektes der Klasse JMenu
		menu = new JMenu("Menu");
		// Erzeugung eines Objektes der Klasse JMenuItem
		benutzerAnlegen = new JMenuItem("Benutzer anlegen");
		benutzerÄndern = new JMenuItem("Benutzer ändern");
		buchZurückgeben = new JMenuItem("Buch zurückgeben");
		buchAusleihen = new JMenuItem("Buch ausleihen");
		panel.setLayout(null);
	}
	
	public void launchAuswahl(JFrame frame){
		this.frame = frame;
		frame.setJMenuBar(bar);
		// Menü wird der Menüleiste hinzugefügt
		bar.add(menu);
		// Wir fügen das JMenuItem unserem JMenu hinzu
		menu.add(benutzerAnlegen);
		benutzerAnlegen.setActionCommand(ACTION_BENUTZER_ANLEGEN);
		benutzerAnlegen.addActionListener(controlMenu);
		menu.add(benutzerÄndern);
		benutzerÄndern.setActionCommand(ACTION_BENUTZER_ÄNDERN);
		benutzerÄndern.addActionListener(controlMenu);
		menu.add(buchZurückgeben);
		buchZurückgeben.setActionCommand(ACTION_BUCH_ZURÜCKGEBEN);
		buchZurückgeben.addActionListener(controlMenu);
		menu.add(buchAusleihen);
		buchAusleihen.setActionCommand(ACTION_BUCH_AUSLEIHEN);
		buchAusleihen.addActionListener(controlMenu);
		frame.setTitle("Menu Punkt wählen");
		frame.setContentPane(panel);
	}

}
