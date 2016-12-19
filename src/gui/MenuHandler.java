/**
 * Klasse, die die Events der MenuAuswahl bearbeitet
 */
package gui;

import java.awt.event.*;

/**
 * @author Gotti
 *
 */
public class MenuHandler implements ActionListener {
	MenuAuswahl menuAuswahl;
	BenutzerAnlegen benutzerAnlegen;
	
	// create reference to GUI
	public MenuHandler(MenuAuswahl gui) { 
		this.menuAuswahl = gui;
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
				menuAuswahl.auswahl.setVisible(false);
				break;
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
