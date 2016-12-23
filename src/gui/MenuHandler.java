/**
 * Klasse, die die Events der Startmenu bearbeitet
 */
package gui;

import java.awt.event.*;

/**
 * @author Michael Gottinger, Sandra Speckmeier
 *
 */

public class MenuHandler implements ActionListener {
	Startmenu startmenu;
	BenutzerAnlegen benutzerAnlegen;
	BenutzerÄndern benutzerÄndern;
	BuchRueckgabe buchRückgabe;
	BuchAusleihe buchAusleihen;
	
	// create reference to GUI
	public MenuHandler(Startmenu gui) { 
		this.startmenu = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
			//Prüfen, welches Kommando kommt
			switch (e.getActionCommand()){ 
			//Fall Student erstellen:
			case "BENUTZER_ANLEGEN":
				benutzerAnlegen = new BenutzerAnlegen();
				benutzerAnlegen.LaunchBenutzerAnlegen();
				startmenu.auswahl.setVisible(false);
				break;
			case "BENUTZER_ÄNDERN":
				benutzerÄndern = new BenutzerÄndern();
				benutzerÄndern.launchBenutzerÄndern();
				startmenu.auswahl.setVisible(false);
				break;
			case "BUCH_ZURÜCKGEBEN":
				buchRückgabe = new BuchRueckgabe();
				buchRückgabe.launchRückgabe();
				startmenu.auswahl.setVisible(false);
				break;
			case "BUCH_AUSLEIHEN":
				buchAusleihen = new BuchAusleihe();
				buchAusleihen.launchBuchAusleihen();
				startmenu.auswahl.setVisible(false);
				break;
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
