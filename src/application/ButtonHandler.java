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
	private String name, vorname, benutzername, passwort, stra�e, ort, hausnummer;
	private int postleitzahl, generatedID;
	private char art;
	private Benutzer benutzer;

	// create reference to GUI
	public ButtonHandler(BenutzerAnlegen benutzerAnlegen) {
		this.benutzerAnlegen = benutzerAnlegen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Pr�fen, welches Kommando kommt
			switch (e.getActionCommand()) {
			// Fall Student erstellen:
			case "ANLEGEN":
				GUIDaten();
				con = DB_connection.getDbConnection();
				switch (this.benutzerAnlegen.getBenutzerArt()) {
				case "Student":
					int matrikelnummer = this.benutzerAnlegen.getMatrikelnummer();
					Studiengruppe studiengruppe = this.benutzerAnlegen.getStudiengruppe();
					Student student = new Student(name, vorname,matrikelnummer, studiengruppe);
					art = 's';
					// Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
					ObjektErstellung(student);
					// 2. einf�gen in Tabelle Student
					String insertStudent = "INSERT INTO student(Matrikelnummer, Studiengruppe, PersonID) VALUES ("+ student.getMatrikelnummer()+ ",'"+ student.getStudiengruppe().toString()+ "',"+ generatedID + ");";
					boolean studentVerbucht = con.executequery(insertStudent);
					System.out.println("Student erfolgreich verbucht: "+ studentVerbucht);
					//4. einf�gen in Tabelle Adresse
					Adresse adresse = new Adresse (stra�e, hausnummer, postleitzahl, ort);
					String insertAdresse = "INSERT INTO adresse(AdressID, Stra�e, Hausnummer, Postleitzahl, Ort) VALUES ('"+generatedID+"','"+adresse.getStra�e()+"','"+adresse.getHausnummer()+"','"+adresse.getPostleitzahl()+"','"+adresse.getOrt()+"');";
					boolean adresseVerbucht = con.executequery(insertAdresse);
					System.out.println("Adresse erfolgreich verbucht: "+adresseVerbucht);
					JOptionPane.showMessageDialog(new JFrame(),"Student und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+ benutzer.getBenutzername()+ student.toString());
					benutzerAnlegen.tableview.updateSQLTable(DB_connection.getAllUsers());
					EintragL�schen();
					con.disconnect();
					break;
				case "Professor":
					//Daten aus GUI abziehen und Professorobjekt erstellen
					String fakult�t = this.benutzerAnlegen.getFakult�t();
					Professor professor = new Professor(name, vorname, fakult�t);
					art = 'p';
					//Wenn Professorenobjekt erfolgreich erstellt, dann in Datenbank sichern
					ObjektErstellung(professor);
					//2. einf�gen in Tabelle Professor
					String insertProfessor = "INSERT INTO professor(Fakult�t, PersonID) VALUES ('"+professor.getFakultaet()+"',"+generatedID+");";
					boolean professorVerbucht = con.executequery(insertProfessor);
					System.out.println("Professert erfolgreich verbucht: "+professorVerbucht);
					//4. einf�gen in Tabelle Adresse
					adresse = new Adresse (stra�e, hausnummer, postleitzahl, ort);
					insertAdresse = "INSERT INTO adresse(AdressID, Stra�e, Hausnummer, Postleitzahl, Ort) VALUES ('"+generatedID+"','"+adresse.getStra�e()+"','"+adresse.getHausnummer()+"','"+adresse.getPostleitzahl()+"','"+adresse.getOrt()+"');";
					adresseVerbucht = con.executequery(insertAdresse);
					System.out.println("Adresse erfolgreich verbucht: "+adresseVerbucht);
					JOptionPane.showMessageDialog(new JFrame(), "Professor und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+benutzer.getBenutzername()+professor.toString());
					benutzerAnlegen.tableview.updateSQLTable(DB_connection.getAllUsers());
					EintragL�schen();
					con.disconnect();
					break;
				case "Personal":
					//Daten aus GUI abziehen und Personalobjekt erstellen
					Personal personal = new Personal(name, vorname);
					art = 'b';
					ObjektErstellung(personal);
					//2. einf�gen in Tabelle Personal
					String insertPersonal = "INSERT INTO personal(PersonID) VALUES ("+generatedID+");";
					boolean personalVerbucht = con.executequery(insertPersonal);
					System.out.println("Personal erfolgreich verbucht: "+personalVerbucht);
					//4. einf�gen in Tabelle Adresse
					adresse = new Adresse (stra�e, hausnummer, postleitzahl, ort);
					insertAdresse = "INSERT INTO adresse(AdressID, Stra�e, Hausnummer, Postleitzahl, Ort) VALUES ('"+generatedID+"','"+adresse.getStra�e()+"','"+adresse.getHausnummer()+"','"+adresse.getPostleitzahl()+"','"+adresse.getOrt()+"');";
					adresseVerbucht = con.executequery(insertAdresse);
					System.out.println("Adresse erfolgreich verbucht: "+adresseVerbucht);
					JOptionPane.showMessageDialog(new JFrame(), "Personal und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+benutzer.getBenutzername()+personal.toString());
					this.benutzerAnlegen.tableview.updateSQLTable(DB_connection.getAllUsers());
					EintragL�schen();
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
			//1. einf�gen in Tabelle
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

	// bef�llt f�r jeden Benutzer die Grundinformationen
	private void GUIDaten() {
		name = this.benutzerAnlegen.getName();
		vorname = this.benutzerAnlegen.getVorname();
		benutzername = this.benutzerAnlegen.getBenutzername();
		passwort = this.benutzerAnlegen.getPasswort();
		stra�e = this.benutzerAnlegen.getStra�e();
		hausnummer = this.benutzerAnlegen.getHausnummer();
		postleitzahl = this.benutzerAnlegen.getPLZ();
		ort = this.benutzerAnlegen.getOrt();
	}
	
	// bef�llt die f�r jedes Objekt identischen Tabellen
	private void ObjektErstellung(PersonABC person){
		try{
		// 1. einf�gen in Tabelle Person
		String insertPerson = "INSERT INTO person(Vorname, Name, Art) VALUES ('"+ person.getVorname()+ "','"+ person.getName()+ "','"+art+"');";
			// erhalten der generierten PersonID und Verbuchung
			generatedID = con.executequery_autoKey(insertPerson, true);
			System.out.println("Erstellte PersonID: " + generatedID);
			// 3. einf�gen in Tabelle Benutzer
			benutzer = new Benutzer(benutzername, passwort,person);
			// Wenn Studentenobjekt erfolgreich erstellt, dann inDatenbank sichern
			String insertBenutzer = "INSERT INTO benutzer(Benutzername, Passwort, PersonID) VALUES ('"+ benutzer.getBenutzername()+ "','"+ benutzer.getPasswort()+ "',"+ generatedID+ ");";
			boolean benutzerVerbucht = con.executequery(insertBenutzer);
			System.out.println("Benutzer erfolgreich verbucht: "+ benutzerVerbucht);
		} catch (SQLException sqlEx) {
			JOptionPane.showMessageDialog(new JFrame(), sqlEx.getMessage());
		}
	}
		
	// entfernt alle Eintr�ge in GUI nach Verbuchung
	private void EintragL�schen(){
		benutzerAnlegen.setName(null);
		benutzerAnlegen.setVorname(null);
		benutzerAnlegen.setBenutzername(null);
		benutzerAnlegen.setPasswort(null);
		benutzerAnlegen.setFakult�t(null);
		benutzerAnlegen.setMatrikelnummer(null);
		benutzerAnlegen.setStudiengruppe(null);
		benutzerAnlegen.setStra�e(null);
		benutzerAnlegen.setHausnummer(null);
		benutzerAnlegen.setOrt(null);
		benutzerAnlegen.setPLZ(null);
	}
}
