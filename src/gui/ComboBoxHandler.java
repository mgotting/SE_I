/**
 * Klasse die die ComboBox Events bearbeitet
 */
package gui;

import java.awt.event.*;

/**
 * @author Michael Gottinger
 *
 */
public class ComboBoxHandler implements ItemListener {
	BenutzerAnlegen benutzerAnlegen;
	Benutzerƒndern benutzerƒndern;
	
	// create reference to GUI
	public ComboBoxHandler(BenutzerAnlegen benutzer) { 
		this.benutzerAnlegen = benutzer;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		//erhalte den ausgew‰hlten Wert
        String item = (String)e.getItem();
        if (e.getStateChange() == ItemEvent.SELECTED){
        	this.benutzerAnlegen.setBenutzerArt(item);
        System.out.println("Ausgew‰hlter Wert: "+item);
        }
        switch (item){
        case "Student":
        	this.benutzerAnlegen.matrikelnummer.setEditable(true);
        	this.benutzerAnlegen.studiengruppe.setEditable(true);
        	this.benutzerAnlegen.fakult‰t.setEditable(false);
        	this.benutzerAnlegen.straﬂe.setEditable(true);
        	this.benutzerAnlegen.hausnummer.setEditable(true);
        	this.benutzerAnlegen.postleitzahl.setEditable(true);
        	this.benutzerAnlegen.ort.setEditable(true);
        	break;
        case "Professor":
        	this.benutzerAnlegen.fakult‰t.setEditable(true);
        	this.benutzerAnlegen.matrikelnummer.setEditable(false);
        	this.benutzerAnlegen.studiengruppe.setEditable(false);
        	this.benutzerAnlegen.straﬂe.setEditable(true);
        	this.benutzerAnlegen.hausnummer.setEditable(true);
        	this.benutzerAnlegen.postleitzahl.setEditable(true);
        	this.benutzerAnlegen.ort.setEditable(true);
        	break;
        case "Personal":
           	this.benutzerAnlegen.fakult‰t.setEditable(false);
        	this.benutzerAnlegen.matrikelnummer.setEditable(false);
        	this.benutzerAnlegen.studiengruppe.setEditable(false);
        	this.benutzerAnlegen.straﬂe.setEditable(false);
        	this.benutzerAnlegen.hausnummer.setEditable(false);
        	this.benutzerAnlegen.postleitzahl.setEditable(false);
        	this.benutzerAnlegen.ort.setEditable(false);
        }
    }
}
