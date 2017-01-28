/**
 * Klasse die die ComboBox Events bearbeitet
 */
package gui;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Michael Gottinger
 *
 */
public class ComboBoxHandler implements ItemListener {
	BenutzerAnlegen benutzerAnlegen;
//	BenutzerÄndern benutzerÄndern;
	
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
        	this.benutzerAnlegen.tfMatrikelnummer.setEditable(true);
        	this.benutzerAnlegen.tfStudiengruppe.setEditable(true);
        	this.benutzerAnlegen.tfFakultät.setEditable(false);
        	this.benutzerAnlegen.tfStraße.setEditable(true);
        	this.benutzerAnlegen.tfHausnummer.setEditable(true);
        	this.benutzerAnlegen.tfPostleitzahl.setEditable(true);
        	this.benutzerAnlegen.tfOrt.setEditable(true);
        	break;
        case "Professor":
        	this.benutzerAnlegen.tfFakultät.setEditable(true);
        	this.benutzerAnlegen.tfMatrikelnummer.setEditable(false);
        	this.benutzerAnlegen.tfStudiengruppe.setEditable(false);
        	this.benutzerAnlegen.tfStraße.setEditable(true);
        	this.benutzerAnlegen.tfHausnummer.setEditable(true);
        	this.benutzerAnlegen.tfPostleitzahl.setEditable(true);
        	this.benutzerAnlegen.tfOrt.setEditable(true);
        	break;
        case "Personal":
           	this.benutzerAnlegen.tfFakultät.setEditable(false);
        	this.benutzerAnlegen.tfMatrikelnummer.setEditable(false);
        	this.benutzerAnlegen.tfStudiengruppe.setEditable(false);
        	this.benutzerAnlegen.tfStraße.setEditable(false);
        	this.benutzerAnlegen.tfHausnummer.setEditable(false);
        	this.benutzerAnlegen.tfPostleitzahl.setEditable(false);
        	this.benutzerAnlegen.tfOrt.setEditable(false);
        } 
    }
}
