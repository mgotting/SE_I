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
//	Benutzerƒndern benutzerƒndern;
	
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
        	this.benutzerAnlegen.tfMatrikelnummer.setEditable(true);
        	this.benutzerAnlegen.tfStudiengruppe.setEditable(true);
        	this.benutzerAnlegen.tfFakult‰t.setEditable(false);
        	this.benutzerAnlegen.tfStraﬂe.setEditable(true);
        	this.benutzerAnlegen.tfHausnummer.setEditable(true);
        	this.benutzerAnlegen.tfPostleitzahl.setEditable(true);
        	this.benutzerAnlegen.tfOrt.setEditable(true);
        	break;
        case "Professor":
        	this.benutzerAnlegen.tfFakult‰t.setEditable(true);
        	this.benutzerAnlegen.tfMatrikelnummer.setEditable(false);
        	this.benutzerAnlegen.tfStudiengruppe.setEditable(false);
        	this.benutzerAnlegen.tfStraﬂe.setEditable(true);
        	this.benutzerAnlegen.tfHausnummer.setEditable(true);
        	this.benutzerAnlegen.tfPostleitzahl.setEditable(true);
        	this.benutzerAnlegen.tfOrt.setEditable(true);
        	break;
        case "Personal":
           	this.benutzerAnlegen.tfFakult‰t.setEditable(false);
        	this.benutzerAnlegen.tfMatrikelnummer.setEditable(false);
        	this.benutzerAnlegen.tfStudiengruppe.setEditable(false);
        	this.benutzerAnlegen.tfStraﬂe.setEditable(false);
        	this.benutzerAnlegen.tfHausnummer.setEditable(false);
        	this.benutzerAnlegen.tfPostleitzahl.setEditable(false);
        	this.benutzerAnlegen.tfOrt.setEditable(false);
        }
    }
}
