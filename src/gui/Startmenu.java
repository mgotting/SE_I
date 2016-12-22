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
	JFrame auswahl;
	JPanel panel;
	JMenuBar bar;
	JMenu menu;
	JMenuItem benutzerAnlegen, benutzerÄndern;
	Login login;
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	
	public final static String ACTION_BENUTZER_ANLEGEN = "BENUTZER_ANLEGEN";
	
	public MenuHandler controlMenu;
	
	public Startmenu(){
		controlMenu = new MenuHandler(this);
		// Erzeugung eines neuen Frames mit dem Titel "Menupunkt wählen "
		auswahl = new JFrame("Menupunkt wählen");
		panel = new JPanel();
		// Erstellen einer Menüleiste
		bar = new JMenuBar();
		// Erzeugung eines Objektes der Klasse JMenu
		menu = new JMenu("Menu");
		// Erzeugung eines Objektes der Klasse JMenuItem
		benutzerAnlegen = new JMenuItem("Benutzer anlegen");
		benutzerÄndern = new JMenuItem("Benutzer ändern");
		
		auswahl.setContentPane(panel);
		panel.setLayout(null);
	}
	
	public void launchAuswahl(){
		auswahl.setBounds(x_right, y_north, x_width, y_height);
		auswahl.setJMenuBar(bar);
		// Menü wird der Menüleiste hinzugefügt
		bar.add(menu);
		// Wir fügen das JMenuItem unserem JMenu hinzu
		menu.add(benutzerAnlegen);
		benutzerAnlegen.setActionCommand(ACTION_BENUTZER_ANLEGEN);
		benutzerAnlegen.addActionListener(controlMenu);
		menu.add(benutzerÄndern);
        // Wir setzen die Breite und die Höhe unseres Fensters auf 500 Pixel */ 
		auswahl.setSize(500, 500);
		auswahl.setLocation(100, 100);
		// Beim schließen des GUI-Fensters wird JFrame geschlossen
		auswahl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Wir lassen unseren Frame anzeigen
		auswahl.setVisible(true);
	}

}
