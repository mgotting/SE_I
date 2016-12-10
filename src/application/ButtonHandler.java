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
			//TODO: welches Kommando kommt? Wo in der GUI sind die Daten hinterlegt?
			if(e.getActionCommand().equals("CREATE_STUD")){ 
				Student student = new Student(name, vorname, matrikelnummer, studiengruppe);
				//Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
				DB_connection con = DB_connection.getDbConnection();
				//1. einfügen in Tabelle Person
				String insertPerson = "INSERT INTO person(Vorname, Name) VALUES ('"+student.getVorname()+"','"+student.getName()+"');";
				boolean personVerbucht = con.executequery(insertPerson);
				//TODO PersonID erhalten, die in Datenbank per AI erstellt wurde
				//2. einfügen in Tabelle Student
				String insertStudent = "INSERT INTO student(Matrikelnummer, Studiengruppe, PersonID) VALUES ('"+student.getMatrikelnummer()+"',"+student.getStudiengruppe().toString()+"');";
				boolean studentVerbucht = con.executequery(insertStudent);
				con.disconnect();
			}
		} catch (Exception f){
			f.printStackTrace();
		}
	}
}
