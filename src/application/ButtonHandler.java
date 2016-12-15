package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import connectionToDatabase.DB_connection;
import gui.surface;

/**
 * @author Michael Gottinger
 *
 */

public class ButtonHandler implements ActionListener {
	surface gui;
	String name;
	String vorname;
	String benutzername;
	String passwort;
	
	// create reference to GUI
	public ButtonHandler(gui.surface gui) { 
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		try{
			//Prüfen, welches Kommando kommt
			switch (e.getActionCommand()){ 
			//Fall Student erstellen:
			case "CREATE_STUDENT":
				//Check Konsole
				System.out.println("ActionCommand erhalten: "+e.getActionCommand());
				//Daten aus GUI abziehen und Studentenobjekt erstellen
				GUIDaten();
				int matrikelnummer = this.gui.getMatrikelnummer();
				Studiengruppe studiengruppe = this.gui.getStudiengruppe();
				Student student = new Student(name, vorname, matrikelnummer, studiengruppe);
				//Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
				DB_connection con = DB_connection.getDbConnection();
				//1. einfügen in Tabelle Person
				String insertPerson = "INSERT INTO person(Vorname, Name, Art) VALUES ('"+student.getVorname()+"','"+student.getName()+"','s');";
				//erhalten der generierten PersonID und Verbuchung
				int generatedID = con.executequery_autoKey(insertPerson, true);
				System.out.println("Erstellte PersonID: "+generatedID);
				//2. einfügen in Tabelle Student
				String insertStudent = "INSERT INTO student(Matrikelnummer, Studiengruppe, PersonID) VALUES ("+student.getMatrikelnummer()+",'"+student.getStudiengruppe().toString()+"',"+generatedID+");";
				boolean studentVerbucht = con.executequery(insertStudent);
				System.out.println("Student erfolgreich verbucht: "+studentVerbucht);
				//3. einfügen in Tabelle Benutzer
				Benutzer benutzer = new Benutzer(benutzername, passwort, student);
				//Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
				String insertBenutzer = "INSERT INTO benutzer(Benutzername, Passwort, PersonID) VALUES ('"+benutzer.getBenutzername()+"','"+benutzer.getPasswort()+"',"+generatedID+");";
				boolean benutzerVerbucht = con.executequery(insertBenutzer);
				System.out.println("Benutzer erfolgreich verbucht: "+benutzerVerbucht);
				JOptionPane.showMessageDialog(new JFrame(), "Student und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "
				+benutzer.getBenutzername()+student.toString());
				con.disconnect();
				break;
				//Fall Professor erstellen:
				case "CREATE_PROFESSOR":
					//Check Konsole
					System.out.println("ActionCommand erhalten: "+e.getActionCommand());
					//Daten aus GUI abziehen und Professorobjekt erstellen
					GUIDaten();
					String fakultät = this.gui.getFakultät();
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
					JOptionPane.showMessageDialog(new JFrame(), "Professor und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "
					+benutzer.getBenutzername()+professor.toString());
					con.disconnect();
					break;
				case "CREATE_PERSONAL":
					//Check Konsole
					System.out.println("ActionCommand erhalten: "+e.getActionCommand());
					//Daten aus GUI abziehen und Personalobjekt erstellen
					GUIDaten();
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
					JOptionPane.showMessageDialog(new JFrame(), "Personal und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "
					+benutzer.getBenutzername()+personal.toString());
					con.disconnect();
					break;
			}
		} catch (IllegalArgumentException ex){
			this.gui.setStudiengruppe(null);
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		} catch (SQLException ex){
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		}
	}
	
	private void GUIDaten(){
		name = this.gui.getName();
		vorname = this.gui.getVorname();
		benutzername = this.gui.getUsername();
		passwort = this.gui.getPasswort();
	}
}
