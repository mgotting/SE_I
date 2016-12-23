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
	Benutzer�ndern benutzer�ndern;
	BuchRueckgabe buchR�ckgabe;
	BuchAusleihe buchAusleihen;
	
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
				benutzerAnlegen = new BenutzerAnlegen();
				benutzerAnlegen.LaunchBenutzerAnlegen();
				startmenu.auswahl.setVisible(false);
				break;
			case "BENUTZER_�NDERN":
				benutzer�ndern = new Benutzer�ndern();
				benutzer�ndern.launchBenutzer�ndern();
				startmenu.auswahl.setVisible(false);
				break;
			case "BUCH_ZUR�CKGEBEN":
				buchR�ckgabe = new BuchRueckgabe();
				buchR�ckgabe.launchR�ckgabe();
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
