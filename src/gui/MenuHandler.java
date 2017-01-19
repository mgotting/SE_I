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
			//Pr�fen, welches Kommando kommt
			switch (e.getActionCommand()){ 
			//Fall Student erstellen:
			case "BENUTZER_ANLEGEN":
				BenutzerAnlegen benutzerAnlegen = new BenutzerAnlegen();
				benutzerAnlegen.LaunchBenutzerAnlegen(this.startmenu.frame);
				break;
			case "BENUTZER_�NDERN":
				Benutzer�ndern benutzer�ndern = new Benutzer�ndern();
				benutzer�ndern.launchBenutzer�ndern(this.startmenu.frame);
				break;
			case "BUCH_ZUR�CKGEBEN":
				BuchRueckgabe buchR�ckgabe = new BuchRueckgabe(this.startmenu.angemeldeterUser);
				buchR�ckgabe.launchR�ckgabe(this.startmenu.frame);
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
