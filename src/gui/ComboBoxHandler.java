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
	BenutzerÄndern benutzerÄndern;
	
	// create reference to GUI
	public ComboBoxHandler(BenutzerAnlegen benutzer) { 
		this.benutzerAnlegen = benutzer;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		//erhalte den ausgewählten Wert
        String item = (String)e.getItem();
        if (e.getStateChange() == ItemEvent.SELECTED){
        	this.benutzerAnlegen.setBenutzerArt(item);
        System.out.println("Ausgewählter Wert: "+item);
        }
        switch (item){
        case "Student":
        	this.benutzerAnlegen.matrikelnummer.setEditable(true);
        	this.benutzerAnlegen.studiengruppe.setEditable(true);
        	this.benutzerAnlegen.fakultät.setEditable(false);
        	break;
        case "Professor":
        	this.benutzerAnlegen.fakultät.setEditable(true);
        	this.benutzerAnlegen.matrikelnummer.setEditable(false);
        	this.benutzerAnlegen.studiengruppe.setEditable(false);
        	break;
        case "Personal":
           	this.benutzerAnlegen.fakultät.setEditable(false);
        	this.benutzerAnlegen.matrikelnummer.setEditable(false);
        	this.benutzerAnlegen.studiengruppe.setEditable(false);
        	this.benutzerAnlegen.straße.setEditable(false);
        	this.benutzerAnlegen.hausnummer.setEditable(false);
        	this.benutzerAnlegen.postleitzahl.setEditable(false);
        	this.benutzerAnlegen.ort.setEditable(false);
        }
    }
}
