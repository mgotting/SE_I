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
	BenutzerAnlegen benutzerAnlegen;
	String name;
	String vorname;
	String benutzername;
	String passwort;
	String straße;
	int postleitzahl;
	String ort;
	String hausnummer;

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
				DB_connection con = DB_connection.getDbConnection();
				switch (this.benutzerAnlegen.getBenutzerArt()) {
				case "Student":
					int matrikelnummer = this.benutzerAnlegen.getMatrikelnummer();
					Studiengruppe studiengruppe = this.benutzerAnlegen.getStudiengruppe();
					Student student = new Student(name, vorname,matrikelnummer, studiengruppe);
					// Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
					// 1. einfügen in Tabelle Person
					String insertPerson = "INSERT INTO person(Vorname, Name, Art) VALUES ('"+ student.getVorname()+ "','"+ student.getName()+ "','s');";
					// erhalten der generierten PersonID und Verbuchung
					int generatedID = con.executequery_autoKey(insertPerson,true);
					System.out.println("Erstellte PersonID: " + generatedID);
					// 2. einfügen in Tabelle Student
					String insertStudent = "INSERT INTO student(Matrikelnummer, Studiengruppe, PersonID) VALUES ("+ student.getMatrikelnummer()+ ",'"+ student.getStudiengruppe().toString()+ "',"+ generatedID + ");";
					boolean studentVerbucht = con.executequery(insertStudent);
					System.out.println("Student erfolgreich verbucht: "+ studentVerbucht);
					// 3. einfügen in Tabelle Benutzer
					Benutzer benutzer = new Benutzer(benutzername, passwort,student);
					// Wenn Studentenobjekt erfolgreich erstellt, dann inDatenbank sichern
					String insertBenutzer = "INSERT INTO benutzer(Benutzername, Passwort, PersonID) VALUES ('"+ benutzer.getBenutzername()+ "','"+ benutzer.getPasswort()+ "',"+ generatedID+ ");";
					boolean benutzerVerbucht = con.executequery(insertBenutzer);
					System.out.println("Benutzer erfolgreich verbucht: "+ benutzerVerbucht);
					//4. einfügen in Tabelle Adresse
					Adresse adresse = new Adresse (straße, hausnummer, postleitzahl, ort);
					String insertAdresse = "INSERT INTO adresse(AdressID, Straße, Hausnummer, Postleitzahl, Ort) VALUES ('"+generatedID+"','"+adresse.getStraße()+"','"+adresse.getHausnummer()+"','"+adresse.getPostleitzahl()+"','"+adresse.getOrt()+"');";
					boolean adresseVerbucht = con.executequery(insertAdresse);
					System.out.println("Adresse erfolgreich verbucht: "+adresseVerbucht);
					JOptionPane.showMessageDialog(new JFrame(),"Student und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+ benutzer.getBenutzername()+ student.toString());
					benutzerAnlegen.tableview.updateSQLTable(DB_connection.getAllUsers());
					benutzerAnlegen.setName(null);
					benutzerAnlegen.setVorname(null);
					benutzerAnlegen.setBenutzername(null);
					benutzerAnlegen.setPasswort(null);
					benutzerAnlegen.setMatrikelnummer(null);
					benutzerAnlegen.setStudiengruppe(null);
					con.disconnect();
					break;
				case "Professor":
					//Daten aus GUI abziehen und Professorobjekt erstellen
					String fakultät = this.benutzerAnlegen.getFakultät();
					Professor professor = new Professor(name, vorname, fakultät);
					//Wenn Professorenobjekt erfolgreich erstellt, dann in Datenbank sichern
					con = DB_connection.getDbConnection();
					//1. einfügen in Tabelle Person
					insertPerson = "INSERT INTO person(Vorname, Name, Art) VALUES ('"+professor.getVorname()+"','"+professor.getName()+"','p');";
					//erhalten der generierten PersonID und Verbuchung
					generatedID = con.executequery_autoKey(insertPerson, true);
					System.out.println("Erstellte PersonID: "+generatedID);
					//2. einfügen in Tabelle Professor
					String insertProfessor = "INSERT INTO professor(Fakultät, PersonID) VALUES ('"+professor.getFakultaet()+"',"+generatedID+");";
					boolean professorVerbucht = con.executequery(insertProfessor);
					System.out.println("Professert erfolgreich verbucht: "+professorVerbucht);
					//3. einfügen in Tabelle Benutzer
					benutzer = new Benutzer(benutzername, passwort, professor);
					//Wenn Professorenobjekt erfolgreich erstellt, dann in Datenbank sichern
					insertBenutzer = "INSERT INTO benutzer(Benutzername, Passwort, PersonID) VALUES ('"+benutzer.getBenutzername()+"','"+benutzer.getPasswort()+"',"+generatedID+");";
					benutzerVerbucht = con.executequery(insertBenutzer);
					System.out.println("Benutzer erfolgreich verbucht: "+benutzerVerbucht);
					//4. einfügen in Tabelle Adresse
					adresse = new Adresse (straße, hausnummer, postleitzahl, ort);
					insertAdresse = "INSERT INTO adresse(AdressID, Straße, Hausnummer, Postleitzahl, Ort) VALUES ('"+generatedID+"','"+adresse.getStraße()+"','"+adresse.getHausnummer()+"','"+adresse.getPostleitzahl()+"','"+adresse.getOrt()+"');";
					adresseVerbucht = con.executequery(insertAdresse);
					System.out.println("Adresse erfolgreich verbucht: "+adresseVerbucht);
					JOptionPane.showMessageDialog(new JFrame(), "Professor und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+benutzer.getBenutzername()+professor.toString());
					benutzerAnlegen.tableview.updateSQLTable(DB_connection.getAllUsers());
					benutzerAnlegen.setName(null);
					benutzerAnlegen.setVorname(null);
					benutzerAnlegen.setBenutzername(null);
					benutzerAnlegen.setPasswort(null);
					benutzerAnlegen.setFakultät(null);
					con.disconnect();
					break;
				case "Personal":
					//Daten aus GUI abziehen und Personalobjekt erstellen
					Personal personal = new Personal(name, vorname);
					//Wenn Personobjekt erfolgreich erstellt, dann in Datenbank sichern 
					con = DB_connection.getDbConnection();
					//1. einfügen in Tabelle Person
					insertPerson = "INSERT INTO person(Vorname, Name, Art) VALUES ('"+personal.getVorname()+"','"+personal.getName()+"','b');";
					//erhalten der generierten PersonID und Verbuchung
					generatedID = con.executequery_autoKey(insertPerson, true);
					System.out.println("Erstellte PersonID: "+generatedID);
					//2. einfügen in Tabelle Personal
					String insertPersonal = "INSERT INTO personal(PersonID) VALUES ("+generatedID+");";
					boolean personalVerbucht = con.executequery(insertPersonal);
					System.out.println("Personal erfolgreich verbucht: "+personalVerbucht);
					//3. einfügen in Tabelle Benutzer
					benutzer = new Benutzer(benutzername, passwort, personal);
					//Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
					insertBenutzer = "INSERT INTO benutzer(Benutzername, Passwort, PersonID) VALUES ('"+benutzer.getBenutzername()+"','"+benutzer.getPasswort()+"',"+generatedID+");";
					benutzerVerbucht = con.executequery(insertBenutzer);
					System.out.println("Benutzer erfolgreich verbucht: "+benutzerVerbucht);
					//4. einfügen in Tabelle Adresse
					adresse = new Adresse (straße, hausnummer, postleitzahl, ort);
					insertAdresse = "INSERT INTO adresse(AdressID, Straße, Hausnummer, Postleitzahl, Ort) VALUES ('"+generatedID+"','"+adresse.getStraße()+"','"+adresse.getHausnummer()+"','"+adresse.getPostleitzahl()+"','"+adresse.getOrt()+"');";
					adresseVerbucht = con.executequery(insertAdresse);
					System.out.println("Adresse erfolgreich verbucht: "+adresseVerbucht);
					JOptionPane.showMessageDialog(new JFrame(), "Personal und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+benutzer.getBenutzername()+personal.toString());
					this.benutzerAnlegen.tableview.updateSQLTable(DB_connection.getAllUsers());
					benutzerAnlegen.setName(null);
					benutzerAnlegen.setVorname(null);
					benutzerAnlegen.setBenutzername(null);
					benutzerAnlegen.setPasswort(null);
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

	// befüllt für jeden Benutzer die Grundinformationen TODO ADRESSE?
	private void GUIDaten() {
		name = this.benutzerAnlegen.getName();
		vorname = this.benutzerAnlegen.getVorname();
		benutzername = this.benutzerAnlegen.getBenutzername();
		passwort = this.benutzerAnlegen.getPasswort();
		straße = this.benutzerAnlegen.getStraße();
		hausnummer = this.benutzerAnlegen.getHausnummer();
		postleitzahl = this.benutzerAnlegen.getPLZ();
		ort = this.benutzerAnlegen.getOrt();
	}
}
