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
				BenutzerAnlegen benutzerAnlegen = new BenutzerAnlegen();
				benutzerAnlegen.LaunchBenutzerAnlegen(this.startmenu.frame);
				break;
			case "BENUTZER_ÄNDERN":
				BenutzerÄndern benutzerÄndern = new BenutzerÄndern();
				benutzerÄndern.launchBenutzerÄndern(this.startmenu.frame);
				break;
			case "BUCH_ZURÜCKGEBEN":
				BuchRueckgabe buchRückgabe = new BuchRueckgabe(this.startmenu.angemeldeterUser);
				buchRückgabe.launchRückgabe(this.startmenu.frame);
				break;
			case "BUCH_AUSLEIHEN":
				BuchAusleihe buchAusleihe = new BuchAusleihe(this.startmenu.angemeldeterUser);
				buchAusleihe.launchBuchAusleihen(this.startmenu.frame);
				break;
			case "BUCH_INVENTARISIEREN":
				BuchInventarisieren buchInventarisieren = new BuchInventarisieren();
				buchInventarisieren.launchBuchInventarisieren(this.startmenu.frame);
				break;
			case "BUCH_STATUS_ANZEIGEN":
				BuchStatus buchStatus = new BuchStatus();
				buchStatus.launchBuchStatus(this.startmenu.frame);
				break;
				
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
