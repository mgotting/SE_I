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

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Prüfen, welches Kommando kommt
			switch (e.getActionCommand()) {
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
					benutzerAnlegen.tableview.updateSQLTable(DB_connection.getAllUsers());
					EintragLöschen();
					con.disconnect();
					break;
				case "Professor":
					//Daten aus GUI abziehen und Professorobjekt erstellen
					String fakultät = this.benutzerAnlegen.getFakultät();
					Professor professor = new Professor(name, vorname, fakultät);
					art = 'p';
					//Wenn Professorenobjekt erfolgreich erstellt, dann in Datenbank sichern
					ObjektErstellung(professor);
					//2. einfügen in Tabelle Professor
					String insertProfessor = "INSERT INTO professor(Fakultät, PersonID) VALUES ('"+professor.getFakultaet()+"',"+generatedID+");";
					boolean professorVerbucht = con.executequery(insertProfessor);
					System.out.println("Professert erfolgreich verbucht: "+professorVerbucht);
					JOptionPane.showMessageDialog(new JFrame(), "Professor und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+benutzer.getBenutzername()+professor.toString());
					benutzerAnlegen.tableview.updateSQLTable(DB_connection.getAllUsers());
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
					this.benutzerAnlegen.tableview.updateSQLTable(DB_connection.getAllUsers());
					EintragLöschen();
					con.disconnect();
					break;
				}
				break;
				//TODO
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
