package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import connectionToDatabase.DB_connection;
import gui.surface;

/**
 * @author Gotti
 *
 */

public class ButtonHandler implements ActionListener {
	surface gui;
	
	// create reference to GUI
	public ButtonHandler(gui.surface gui) { 
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		try{
			//Prüfen, welches Kommando kommt
			//TODO: welches Kommando kommt?
			if(e.getActionCommand().equals("CREATE_STUD")){ 
				String name = this.gui.getName();
				String vorname = this.gui.getVorname();
				int matrikelnummer = this.gui.getMatrikelnummer();
				Studiengruppe studiengruppe = this.gui.getStudiengruppe();
				String benutzername = this.gui.getBenutzername();
				String passwort = this.gui.getPasswort();
				Student student = new Student(name, vorname, matrikelnummer, studiengruppe);
				//Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
				DB_connection con = DB_connection.getDbConnection();
				//1. einfügen in Tabelle Person
				String insertPerson = "INSERT INTO person(Vorname, Name) VALUES ('"+student.getVorname()+"','"+student.getName()+"');";
				boolean personVerbucht = con.executequery(insertPerson);
				//erhalten der generierten PersonID
				int generatedID = con.executequery_autoKey(insertPerson, true);
				//2. einfügen in Tabelle Student
				String insertStudent = "INSERT INTO student(Matrikelnummer, Studiengruppe, PersonID) VALUES ('"+student.getMatrikelnummer()+"',"+student.getStudiengruppe().toString()+"',"+generatedID+");";
				boolean studentVerbucht = con.executequery(insertStudent);
				//3. einfügen in Tabelle Benutzer
				//TODO: Wo sind in der GUI die Daten hinterlegt?
				Benutzer benutzer = new Benutzer(benutzername, passwort, student, ausgelieheneBuecher);
				//Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
				String insertBenutzer = "INSERT INTO benutzer(Benutzername, Passwort) VALUES ('"+benutzer.getBenutzername()+"',"+benutzer.getPasswort()+"');";
				boolean benutzerVerbucht = con.executequery(insertBenutzer);
				con.disconnect();
			}
		} catch (Exception f){
			f.printStackTrace();
		}
	}
}
