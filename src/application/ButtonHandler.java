/**
 * Klasse mithilfe derer die ButtonEvents verarbeitet werden.
 */

package application;

import gui.*;
import connectionToDatabase.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Michael Gottinger, Sandra Speckmeier
 *
 */

public class ButtonHandler implements ActionListener {
	private BenutzerAnlegen benutzerAnlegen;
	private BenutzerÄndern benutzerÄndern;
	private BuchAusleihe buchAusleihen;
	private BuchRueckgabe buchRückgabe;
	private Login login;
	private DB_connection con;
	private String name, vorname, benutzername, passwort, straße, ort, hausnummer;
	private int postleitzahl, generatedID, generatedAdressID;
	private char art;
	private Benutzer benutzer;
	private boolean adresseVorhanden;

	// create reference to GUI
	public ButtonHandler(BenutzerAnlegen benutzerAnlegen) {
		this.benutzerAnlegen = benutzerAnlegen;
	}
	
	public ButtonHandler(BenutzerÄndern benutzerÄndern){
		this.benutzerÄndern = benutzerÄndern;
	}
	
	public ButtonHandler(BuchAusleihe buchAusleihen){
		this.buchAusleihen = buchAusleihen;
	}
	
	public ButtonHandler(BuchRueckgabe buchRückgabe){
		this.buchRückgabe = buchRückgabe;
	}
	
	public ButtonHandler(Login login) {
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Prüfen, welches Kommando kommt
			switch (e.getActionCommand()) {
			// Fall anmelden
			case "ANMELDEN":
				con = DB_connection.getDbConnection();
				if(login.getBenutzername().equals(con.executequery_Value(DB_connection.checkAnmeldung(login.getBenutzername(),login.getPasswort())))){
				login.panel.setVisible(false);
				Startmenu startmenu = new Startmenu();
				startmenu.launchAuswahl(this.login.login);
				//TODO Über PersonDB nach Art abfragen und dann je nach Art entsprechendes Objekt erstellen
				System.out.println("Benutzer erfolgreich angemeldet");
				} else {
				JOptionPane.showMessageDialog(new JFrame(), "Benutzername / Passwort falsch");
				login.setBenutzername(null);
				login.setPasswort(null);
				}
				break;
			// Fall Student erstellen:
			case "ANLEGEN":
				GUIDaten();
				con = DB_connection.getDbConnection();
				if(adresseVorhanden == true){
				//4. einfügen in Tabelle Adresse
				Adresse adresse = new Adresse (straße, hausnummer, postleitzahl, ort);
				String insertAdresse = "INSERT INTO adresse(Straße, Hausnummer, Postleitzahl, Ort) VALUES ('"+adresse.getStraße()+"','"+adresse.getHausnummer()+"','"+adresse.getPostleitzahl()+"','"+adresse.getOrt()+"');";
				generatedAdressID = con.executequery_autoKey(insertAdresse, true);
				}
				switch (this.benutzerAnlegen.getBenutzerArt()) {
				case "Student":
					int matrikelnummer = this.benutzerAnlegen.getMatrikelnummer();
					Studiengruppe studiengruppe = this.benutzerAnlegen.getStudiengruppe();
					Student student;
					art = 's';
					if(adresseVorhanden == false){
					student = new Student(name, vorname,matrikelnummer, studiengruppe);
					} else {
					student = new Student(name, vorname,matrikelnummer, studiengruppe, straße, hausnummer, postleitzahl, ort);
					}
					// Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
					ObjektErstellung(student);
					// 2. einfügen in Tabelle Student
					String insertStudent = "INSERT INTO student(Matrikelnummer, Studiengruppe, PersonID) VALUES ("+ student.getMatrikelnummer()+ ",'"+ student.getStudiengruppe().toString()+ "',"+ generatedID + ");";
					boolean studentVerbucht = con.executequery(insertStudent);
					System.out.println("Student erfolgreich verbucht: "+ studentVerbucht);
					JOptionPane.showMessageDialog(new JFrame(),"Student und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+ benutzer.getBenutzername()+ student.toString());
					benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
					EintragLöschen();
					con.disconnect();
					break;
				case "Professor":
					//Daten aus GUI abziehen und Professorobjekt erstellen
					String fakultät = this.benutzerAnlegen.getFakultät();
					Professor professor;
					art = 'p';
					if(adresseVorhanden == false){
					professor = new Professor(name, vorname, fakultät);
					} else {
					professor = new Professor(name, vorname,fakultät, straße, hausnummer, postleitzahl, ort);
					}
					//Wenn Professorenobjekt erfolgreich erstellt, dann in Datenbank sichern
					ObjektErstellung(professor);
					//2. einfügen in Tabelle Professor
					String insertProfessor = "INSERT INTO professor(Fakultät, PersonID) VALUES ('"+professor.getFakultaet()+"',"+generatedID+");";
					boolean professorVerbucht = con.executequery(insertProfessor);
					System.out.println("Professert erfolgreich verbucht: "+professorVerbucht);
					JOptionPane.showMessageDialog(new JFrame(), "Professor und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+benutzer.getBenutzername()+professor.toString());
					benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
					EintragLöschen();
					con.disconnect();
					break;
				case "Personal":
					//Daten aus GUI abziehen und Personalobjekt erstellen
					Personal personal = new Personal(name, vorname);
					art = 'b';
					ObjektErstellung(personal);
					//2. einfügen in Tabelle Personal
					String insertPersonal = "INSERT INTO personal(PersonID) VALUES ("+generatedID+");";
					boolean personalVerbucht = con.executequery(insertPersonal);
					System.out.println("Personal erfolgreich verbucht: "+personalVerbucht);
					JOptionPane.showMessageDialog(new JFrame(), "Personal und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+benutzer.getBenutzername()+personal.toString());
					this.benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
					EintragLöschen();
					con.disconnect();
					break;
				}
				break;
			case "AUSWÄHLEN":
				if(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow()==-1)
						throw new JTableException("Fehler: Zeile nicht markiert!");
				String art = (String)
					benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 12).toString();		
					benutzerÄndern.setBenutzerArt(art);	
					System.out.println("Welcher Benutzer bist du? "+art);
					
					benutzerÄndern.matrikelnummer.setEditable(false);
		        	benutzerÄndern.studiengruppe.setEditable(false);
		        	benutzerÄndern.fakultät.setEditable(false);
		        	benutzerÄndern.straße.setEditable(false);
		        	benutzerÄndern.hausnummer.setEditable(false);
		        	benutzerÄndern.postleitzahl.setEditable(false);
		        	benutzerÄndern.ort.setEditable(false);
		        	
				String name = (String)
					benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 1).toString();		
					benutzerÄndern.setName(name);
				String vorname = (String)
					benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 2).toString();		
					benutzerÄndern.setVorname(vorname);
				String benutzername = (String)
					benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 3).toString();		
					benutzerÄndern.setBenutzername(benutzername);
				String passwort = (String)
					benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 4).toString();		
					benutzerÄndern.setPasswort(passwort);		
					
				switch(art){
				case "s":
				String matrikelnummer = (String)
					benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 5).toString();		
					benutzerÄndern.setMatrikelnummer(matrikelnummer);
				String studiengruppe = (String)
					benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 6).toString();		
					benutzerÄndern.setStudiengruppe(studiengruppe);
//					benutzerÄndern.fakultät.setEditable(false);
					benutzerÄndern.matrikelnummer.setEditable(true);
					benutzerÄndern.studiengruppe.setEditable(true);
					benutzerÄndern.straße.setEditable(true);
					benutzerÄndern.hausnummer.setEditable(true);
					benutzerÄndern.postleitzahl.setEditable(true);
					benutzerÄndern.ort.setEditable(true);
					break;
				case "p":
					String fakultät = (String)
					benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 7).toString();		
					benutzerÄndern.setFakultät(fakultät);
					benutzerÄndern.fakultät.setEditable(true);
					benutzerÄndern.straße.setEditable(true);
					benutzerÄndern.hausnummer.setEditable(true);
					benutzerÄndern.postleitzahl.setEditable(true);
					benutzerÄndern.ort.setEditable(true);
//					benutzerÄndern.matrikelnummer.setEditable(false);
//					benutzerÄndern.studiengruppe.setEditable(false);
					break;
				
				}
				
				String straße = (String)
						benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 8).toString();		
						benutzerÄndern.setStraße(straße);	
					String hausnummer = (String)
						benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 9).toString();		
						benutzerÄndern.setHausnummer(hausnummer);	
					String postleitzahl = (String)
						benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 10).toString();		
						benutzerÄndern.setPLZ(postleitzahl);
					String ort = (String)
						benutzerÄndern.tableviewUser.getSQLTable().getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 11).toString();		
						benutzerÄndern.setOrt(ort);
				
					break;
				

				
			case "INVENTAR":
			//Check Konsole
			System.out.println("ActionCommand erhalten: "+e.getActionCommand());
			//Daten aus der GUI
			//Exemplar exemplar = new Exemplar (status, buch);
			con = DB_connection.getDbConnection();
			//1. einfügen in Tabelle
			//insertBuch = "INSERT INTO Inventar (status, ISBN) VALUES ('"+Exemplar.getStatus()+"', '"+exemplar.getISBN()+"');"
			break;
			}
			
		} catch (AdressException ex){
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		} catch (IllegalArgumentException ex) {
			this.benutzerAnlegen.setStudiengruppe(null);
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		}
	}

	// befüllt für jeden Benutzer die Grundinformationen und prüft ob Adresse vorhanden.
	private void GUIDaten() {
		name = this.benutzerAnlegen.getName();
		vorname = this.benutzerAnlegen.getVorname();
		benutzername = this.benutzerAnlegen.getBenutzername();
		passwort = this.benutzerAnlegen.getPasswort();
		if(this.benutzerAnlegen.getStraße().equals("")||this.benutzerAnlegen.getHausnummer().equals("") || this.benutzerAnlegen.getPLZ()==0 || this.benutzerAnlegen.getOrt().equals("")){
			adresseVorhanden = false;
		} else {
			adresseVorhanden = true;
			straße = this.benutzerAnlegen.getStraße();
			hausnummer = this.benutzerAnlegen.getHausnummer();
			postleitzahl = this.benutzerAnlegen.getPLZ();
			ort = this.benutzerAnlegen.getOrt();
		}
		System.out.println("Adresse vorhanden: "+adresseVorhanden);
	}
	
	// befüllt die für jedes Objekt identischen Tabellen
	private void ObjektErstellung(PersonABC person){
		try{
			String insertPerson;
			if(adresseVorhanden == true){
		// 1. einfügen in Tabelle Person
			insertPerson = "INSERT INTO person(Vorname, Name, AdressID, Art) VALUES ('"+ person.getVorname()+ "','"+ person.getName()+ "',"+generatedAdressID+",'"+art+"');";
			adresseVorhanden=false;
			} else {
		// 1. einfügen in Tabelle Person
			insertPerson = "INSERT INTO person(Vorname, Name, Art) VALUES ('"+ person.getVorname()+ "','"+ person.getName()+ "','"+art+"');";
			}
			generatedID = con.executequery_autoKey(insertPerson, true);
			System.out.println("Erstellte PersonID: " + generatedID);
			// 3. einfügen in Tabelle Benutzer
			benutzer = new Benutzer(benutzername, passwort,person);
			// Wenn Studentenobjekt erfolgreich erstellt, dann inDatenbank sichern
			String insertBenutzer = "INSERT INTO benutzer(Benutzername, Passwort, PersonID) VALUES ('"+ benutzer.getBenutzername()+ "','"+ benutzer.getPasswort()+ "',"+ generatedID+ ");";
			boolean benutzerVerbucht = con.executequery(insertBenutzer);
			System.out.println("Benutzer erfolgreich verbucht: "+ benutzerVerbucht);
		} catch (SQLException sqlEx) {
			JOptionPane.showMessageDialog(new JFrame(), sqlEx.getMessage());
		}
	}
		
	// entfernt alle Einträge in GUI nach Verbuchung
	private void EintragLöschen(){
		benutzerAnlegen.setName(null);
		benutzerAnlegen.setVorname(null);
		benutzerAnlegen.setBenutzername(null);
		benutzerAnlegen.setPasswort(null);
		benutzerAnlegen.setFakultät(null);
		benutzerAnlegen.setMatrikelnummer(null);
		benutzerAnlegen.setStudiengruppe(null);
		benutzerAnlegen.setStraße(null);
		benutzerAnlegen.setHausnummer(null);
		benutzerAnlegen.setOrt(null);
		benutzerAnlegen.setPLZ(null);
	}
}
