/**
 * Klasse die nach erfolgreicher Anmeldung erscheint
 */
package gui;

import javax.swing.*;

import application.PersonABC;

/**
 * @author Michael Gottinger, Sandra Speckmeier
 *
 */
public class Startmenu {
	JFrame frame;
	JPanel panel;
	JMenuBar bar;
	JMenu menu;
	JMenuItem benutzerAnlegen, benutzerÄndern, buchZurückgeben, buchAusleihen, buchInventarisieren, buchStatus;
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	
	public final static String ACTION_BENUTZER_ANLEGEN = "BENUTZER_ANLEGEN";
	public final static String ACTION_BENUTZER_ÄNDERN = "BENUTZER_ÄNDERN";
	public final static String ACTION_BUCH_ZURÜCKGEBEN = "BUCH_ZURÜCKGEBEN";
	public final static String ACTION_BUCH_AUSLEIHEN = "BUCH_AUSLEIHEN";
	public final static String ACTION_BUCH_INVENTARISIEREN = "BUCH_INVENTARISIEREN";
	public final static String ACTION_BUCH_STATUS_ANZEIGEN = "BUCH_STATUS_ANZEIGEN";
	
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
		buchInventarisieren = new JMenuItem("Buch inventarisieren");
		buchAusleihen = new JMenuItem("Buch ausleihen");
		buchZurückgeben = new JMenuItem("Buch zurückgeben");	
		buchStatus = new JMenuItem("Buchstatus anzeigen");
		
		panel.setLayout(null);
	}
	
	public void launchStartmenu(JFrame frame, char art){
		this.frame = frame;
		frame.setJMenuBar(bar);
		// Menü wird der Menüleiste hinzugefügt
		bar.add(menu);
		// Wir fügen das JMenuItem unserem JMenu hinzu
		if(art == 'b'){
		menu.add(benutzerAnlegen);
		benutzerAnlegen.setActionCommand(ACTION_BENUTZER_ANLEGEN);
		benutzerAnlegen.addActionListener(controlMenu);
		menu.add(benutzerÄndern);
		benutzerÄndern.setActionCommand(ACTION_BENUTZER_ÄNDERN);
		benutzerÄndern.addActionListener(controlMenu);
		menu.add(buchInventarisieren);
		buchAusleihen.setActionCommand(ACTION_BUCH_AUSLEIHEN);
		buchAusleihen.addActionListener(controlMenu);
		}
		menu.add(buchZurückgeben);
		buchInventarisieren.setActionCommand(ACTION_BUCH_INVENTARISIEREN);
		buchInventarisieren.addActionListener(controlMenu);
		menu.add(buchAusleihen);
		buchZurückgeben.setActionCommand(ACTION_BUCH_ZURÜCKGEBEN);
		buchZurückgeben.addActionListener(controlMenu);
		menu.add(buchAusleihen);
		buchStatus.setActionCommand(ACTION_BUCH_STATUS_ANZEIGEN);
		buchStatus.addActionListener(controlMenu);
		menu.add(buchStatus);
		
		frame.setTitle("Menu Punkt wählen");
		frame.setContentPane(panel);
	}

}
