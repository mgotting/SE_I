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
        	this.benutzerAnlegen.tfMatrikelnummer.show();
        	this.benutzerAnlegen.labelMatrikelnummer.show();
        	this.benutzerAnlegen.studiengruppe.setEditable(true);
        	this.benutzerAnlegen.studiengruppe.show();
        	this.benutzerAnlegen.labelStudiengruppe.show();
        	this.benutzerAnlegen.tfFakult‰t.hide();
        	this.benutzerAnlegen.labelFakult‰t.hide();
        	this.benutzerAnlegen.tfStraﬂe.setEditable(true);
        	this.benutzerAnlegen.tfHausnummer.setEditable(true);
        	this.benutzerAnlegen.tfPostleitzahl.setEditable(true);
        	this.benutzerAnlegen.tfOrt.setEditable(true);
        	break;
        case "Professor":
        	this.benutzerAnlegen.tfFakult‰t.setEditable(true);
        	this.benutzerAnlegen.tfFakult‰t.show();
        	this.benutzerAnlegen.labelFakult‰t.show();
        	this.benutzerAnlegen.tfMatrikelnummer.hide();
        	this.benutzerAnlegen.labelMatrikelnummer.hide();
        	this.benutzerAnlegen.studiengruppe.hide();
        	this.benutzerAnlegen.labelStudiengruppe.hide();
        	this.benutzerAnlegen.tfStraﬂe.setEditable(true);
        	this.benutzerAnlegen.tfHausnummer.setEditable(true);
        	this.benutzerAnlegen.tfPostleitzahl.setEditable(true);
        	this.benutzerAnlegen.tfOrt.setEditable(true);
        	break;
        case "Personal":
           	this.benutzerAnlegen.tfFakult‰t.hide();
           	this.benutzerAnlegen.labelFakult‰t.hide();
        	this.benutzerAnlegen.tfMatrikelnummer.hide();
        	this.benutzerAnlegen.labelMatrikelnummer.hide();
        	this.benutzerAnlegen.studiengruppe.hide();
        	this.benutzerAnlegen.labelStudiengruppe.hide();
        	this.benutzerAnlegen.tfStraﬂe.setEditable(false);
        	this.benutzerAnlegen.tfHausnummer.setEditable(false);
        	this.benutzerAnlegen.tfPostleitzahl.setEditable(false);
        	this.benutzerAnlegen.tfOrt.setEditable(false);
        } 
    }
}
