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
	private Benutzer�ndern benutzer�ndern;
	private BuchAusleihe buchAusleihen;
	private BuchRueckgabe buchR�ckgabe;
	private Login login;
	private DB_connection con;
	private String name, vorname, benutzername, passwort, stra�e, ort, hausnummer, angemeldeterUser;
	private int postleitzahl, generatedID, generatedAdressID;
	private char art;
	private Benutzer benutzer;
	private boolean adresseVorhanden;
	private boolean adresseEingetragen;

	// create reference to GUI
	public ButtonHandler(BenutzerAnlegen benutzerAnlegen) {
		this.benutzerAnlegen = benutzerAnlegen;
	}
	
	public ButtonHandler(Benutzer�ndern benutzer�ndern){
		this.benutzer�ndern = benutzer�ndern;
	}
	
	public ButtonHandler(BuchAusleihe buchAusleihen){
		this.buchAusleihen = buchAusleihen;
	}
	
	public ButtonHandler(BuchRueckgabe buchR�ckgabe){
		this.buchR�ckgabe = buchR�ckgabe;
	}
	
	public ButtonHandler(Login login) {
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Pr�fen, welches Kommando kommt
			switch (e.getActionCommand()) {
			// Fall anmelden
			case "ANMELDEN":
				con = DB_connection.getDbConnection();
				if(login.getBenutzername().equals(con.executequery_Value(DB_connection.checkAnmeldung(login.getBenutzername(),login.getPasswort())))){
				angemeldeterUser = login.getBenutzername();
				login.panel.setVisible(false);
				Startmenu startmenu = new Startmenu(angemeldeterUser);
				startmenu.launchAuswahl(this.login.login);
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
				//4. einf�gen in Tabelle Adresse
				Adresse adresse = new Adresse (stra�e, hausnummer, postleitzahl, ort);
				String insertAdresse = "INSERT INTO adresse(Stra�e, Hausnummer, Postleitzahl, Ort) VALUES ('"+adresse.getStra�e()+"','"+adresse.getHausnummer()+"','"+adresse.getPostleitzahl()+"','"+adresse.getOrt()+"');";
				generatedAdressID = con.executequery_autoKey(insertAdresse, true);
				}
				switch (this.benutzerAnlegen.getBenutzerArt()) {
				case "Student":
					int matrikelnummer = this.benutzerAnlegen.getMatrikelnummer(); //einmal festlegen, nicht mehr �nderbar
					Studiengruppe studiengruppe = this.benutzerAnlegen.getStudiengruppe();
					Student student;
					art = 's';
					if(adresseVorhanden == false){
					student = new Student(name, vorname,matrikelnummer, studiengruppe);
					} else {
					student = new Student(name, vorname,matrikelnummer, studiengruppe, stra�e, hausnummer, postleitzahl, ort);
					}
					// Wenn Studentenobjekt erfolgreich erstellt, dann in Datenbank sichern
					ObjektErstellung(student);
					// 2. einf�gen in Tabelle Student
					String insertStudent = "INSERT INTO student(Matrikelnummer, Studiengruppe, PersonID) VALUES ("+ student.getMatrikelnummer()+ ",'"+ student.getStudiengruppe().toString()+ "',"+ generatedID + ");";
					boolean studentVerbucht = con.executequery(insertStudent);
					System.out.println("Student erfolgreich verbucht: "+ studentVerbucht);
					JOptionPane.showMessageDialog(new JFrame(),"Student und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+ benutzer.getBenutzername()+ student.toString());
					benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
					EintragL�schen();
					con.disconnect();
					break;
				case "Professor":
					//Daten aus GUI abziehen und Professorobjekt erstellen
					String fakult�t = this.benutzerAnlegen.getFakult�t();
					Professor professor;
					art = 'p';
					if(adresseVorhanden == false){
					professor = new Professor(name, vorname, fakult�t);
					} else {
					professor = new Professor(name, vorname,fakult�t, stra�e, hausnummer, postleitzahl, ort);
					}
					//Wenn Professorenobjekt erfolgreich erstellt, dann in Datenbank sichern
					ObjektErstellung(professor);
					//2. einf�gen in Tabelle Professor
					String insertProfessor = "INSERT INTO professor(Fakult�t, PersonID) VALUES ('"+professor.getFakultaet()+"',"+generatedID+");";
					boolean professorVerbucht = con.executequery(insertProfessor);
					System.out.println("Professert erfolgreich verbucht: "+professorVerbucht);
					JOptionPane.showMessageDialog(new JFrame(), "Professor und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+benutzer.getBenutzername()+professor.toString());
					benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
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
					JOptionPane.showMessageDialog(new JFrame(), "Personal und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "+benutzer.getBenutzername()+personal.toString());
					this.benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
					EintragL�schen();
					con.disconnect();
					break;
				}
				break;
			//zu �ndernden Benutzer ausw�hlen, damit sich die GUI mit den DB-Werten bef�llt
			case "AUSW�HLEN":
				
				if(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow()==-1)
						throw new JTableException("Fehler: Zeile nicht markiert!");
				String art = (String)
					benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 12).toString();		
					benutzer�ndern.setBenutzerArt(art);	
					System.out.println("Welcher Benutzer bist du? "+art);
					
					benutzer�ndern.matrikelnummer.setEditable(false);
		        	benutzer�ndern.studiengruppe.setEditable(false);
		        	benutzer�ndern.fakult�t.setEditable(false);
		        	benutzer�ndern.stra�e.setEditable(false);
		        	benutzer�ndern.hausnummer.setEditable(false);
		        	benutzer�ndern.postleitzahl.setEditable(false);
		        	benutzer�ndern.ort.setEditable(false);
		        	
				String name = (String)
					benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 1).toString();		
					benutzer�ndern.setName(name);
				String vorname = (String)
					benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 2).toString();		
					benutzer�ndern.setVorname(vorname);
				String benutzername = (String)
					benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 3).toString();		
					benutzer�ndern.setBenutzername(benutzername);
				String passwort = (String)
					benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 4).toString();		
					benutzer�ndern.setPasswort(passwort);		
					
				switch(art){
				case "s":
				String matrikelnummer = (String)
					benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 5).toString();		
					benutzer�ndern.setMatrikelnummer(matrikelnummer);
				String studiengruppe = (String)
					benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 6).toString();		
					benutzer�ndern.setStudiengruppe(studiengruppe);
					benutzer�ndern.studiengruppe.setEditable(true);
					benutzer�ndern.stra�e.setEditable(true);
					benutzer�ndern.hausnummer.setEditable(true);
					benutzer�ndern.postleitzahl.setEditable(true);
					benutzer�ndern.ort.setEditable(true);
					break;
				case "p":
					String fakult�t = (String)
					benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 7).toString();		
					benutzer�ndern.setFakult�t(fakult�t);
					benutzer�ndern.fakult�t.setEditable(true);
					benutzer�ndern.stra�e.setEditable(true);
					benutzer�ndern.hausnummer.setEditable(true);
					benutzer�ndern.postleitzahl.setEditable(true);
					benutzer�ndern.ort.setEditable(true);
					break;
				}
				
					String stra�e = (String)
						benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 8).toString();		
						benutzer�ndern.setStra�e(stra�e);	
					String hausnummer = (String)
						benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 9).toString();		
						benutzer�ndern.setHausnummer(hausnummer);	
					String postleitzahl = (String)
						benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 10).toString();		
						benutzer�ndern.setPLZ(postleitzahl);
					String ort = (String)
						benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 11).toString();		
						benutzer�ndern.setOrt(ort);
										
//					String adressID = (String)
//						benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 13).toString();		
//						benutzer�ndern.setAdressID(adressID);
//						System.out.println("Welche AdressID hat der ausgew�hlte Benutzer: "+adressID);
						break;
						
			//DB-Werte werden in den jeweiligen Tabellen aktualisiert		
			case "�NDERN":
				GUIDaten�ndern();
				//TODO
				con = DB_connection.getDbConnection();
				
				String personID = (String)
					benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 0).toString();		
					benutzer�ndern.setPersonID(personID);
				System.out.println("Welche PersonID hat der ausgew�hlte Benutzer: "+personID);
				
				art = (String)
						benutzer�ndern.tableviewUser.getSQLTable().getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(), 12).toString();		
						benutzer�ndern.setBenutzerArt(art);	
				System.out.println("Welcher Benutzer bist du? "+art);
						
							
						
//				boolean adressID = con.executequery_AdressID().isEmpty();
//				System.out.println("Ist eine keine AdressID vorhanden?: "+adressID);
				
				//Personen-Informationen werden ge�ndert
				System.out.println(benutzer�ndern.getVorname()+" "+benutzer�ndern.getName());
				String updatePerson = "UPDATE library.person SET Vorname = '"+benutzer�ndern.getVorname()+"', Name = '"+benutzer�ndern.getName()+"' WHERE library.person.PersonID = ("+benutzer�ndern.getPersonID()+")";
				boolean personalPersonGe�ndert = con.executequery(updatePerson);
				System.out.println("Person erfolgreich ge�ndert: "+ personalPersonGe�ndert);
				//Benutzer-Informationen werden ge�ndert
				String updateBenuzter = "UPDATE library.benutzer SET Benutzername = '"+benutzer�ndern.getBenutzername()+"', Passwort = '"+benutzer�ndern.getPasswort()+"' WHERE library.benutzer.PersonID= ("+benutzer�ndern.getPersonID()+")";
				boolean BenutzerGe�ndert =con.executequery(updateBenuzter);
				System.out.println("Benutzer erfolgreich ge�ndert: " + BenutzerGe�ndert);
				benutzer�ndern.tableviewUser.updateSQLTable(DB_connection.getUserInfo());
				
				switch(art){
				//Studenten-Infmormationen werden ge�ndert, Matrikelnummer ausgeschlossen (diese wird eimal festgelegt)!
				case "s": 
					String updateStudent = "UPDATE library.student SET Studiengruppe = '"+benutzer�ndern.getStudiengruppe()+"' WHERE library.student.PersonID= ("+benutzer�ndern.getPersonID()+")";
					boolean studentGe�ndert =con.executequery(updateStudent);
					System.out.println("Student erfolgreich ge�ndert: " + studentGe�ndert);
					benutzer�ndern.tableviewUser.updateSQLTable(DB_connection.getUserInfo());
					break;
				//Professoren-Infmormationen werden ge�ndert	
				case "p":
					String updateProfessor = "UPDATE library.professor SET Fakult�t = '"+benutzer�ndern.getFakult�t()+"' WHERE library.professor.PersonID= ("+benutzer�ndern.getPersonID()+")";
					boolean professorGe�ndert =con.executequery(updateProfessor);
					System.out.println("Professor erfolgreich ge�ndert: " + professorGe�ndert);
					benutzer�ndern.tableviewUser.updateSQLTable(DB_connection.getUserInfo());
					break;
				}
				
				
				//TODO wenn eine Adresse eingetragen ist, �berpr�fe ob diese schon angelegt war oder nicht
				if (adresseEingetragen == true){
//					if(adressID==null)
//					Adresse adresse = new Adresse (stra�e, hausnummer, postleitzahl, ort);
//					String insertAdresse = "INSERT INTO adresse(Stra�e, Hausnummer, Postleitzahl, Ort) VALUES ('"+adresse.getStra�e()+"','"+adresse.getHausnummer()+"','"+adresse.getPostleitzahl()+"','"+adresse.getOrt()+"');";
//					generatedAdressID = con.executequery_autoKey(insertAdresse, true);
//					boolean adresseVerbucht = con.executequery(insertAdresse);
//					System.out.println("Adresse erfolgreich verbucht: "+insertAdresse);
//					}else{
//					String updateAdresse = "UPDATE library.adresse SET Stra�e = '"+benutzer�ndern.getStra�e()+"', Hausnummer = '"+benutzer�ndern.getHausnummer()+"', Postleitzahl = '"+benutzer�ndern.getPLZ()+"', Ort = '"+benutzer�ndern.getOrt()+"' WHERE library.person.AdressID= ("+benutzer�ndern.getAdressID()+")";
//					boolean adresseGe�ndert =con.executequery(updateAdresse);
//					System.out.println("Adresse erfolgreich ge�ndert: " + adresseGe�ndert);
//					benutzer�ndern.tableviewUser.updateSQLTable(DB_connection.getUserInfo());
//					}
				}
				break;
				
			case "INVENTAR":
			//Check Konsole
			System.out.println("ActionCommand erhalten: "+e.getActionCommand());
			//Daten aus der GUI
			//Exemplar exemplar = new Exemplar (status, buch);
			con = DB_connection.getDbConnection();
			//1. einf�gen in Tabelle
			//insertBuch = "INSERT INTO Inventar (status, ISBN) VALUES ('"+Exemplar.getStatus()+"', '"+exemplar.getISBN()+"');"
			break;
			
			
			case "BUCH_ZUR�CKGEBEN":
				String buchID = (String)
				buchR�ckgabe.tableview.getSQLTable().getValueAt(buchR�ckgabe.tableview.getSQLTable().getSelectedRow(), 0).toString();
				con = DB_connection.getDbConnection();
				con.executequery(DB_connection.buchZur�ckgegeben(buchID));
				con.executequery("UPDATE library.exemplar SET Status = 'ausleihbar' WHERE library.exemplar.BUCHID="+buchID);
				buchR�ckgabe.tableview.updateSQLTable(DB_connection.getAllRentBooks(angemeldeterUser));
				JOptionPane.showMessageDialog(new JFrame(), "Der angemeldete User hat das Buch mit der ID: "+buchID+" erfolgreich zur�ckgegeben.");
				System.out.println("Buch erfolgreich zur�ckgegeben");
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

	// bef�llt f�r jeden Benutzer die Grundinformationen und pr�ft ob Adresse vorhanden.
	private void GUIDaten() {
		name = this.benutzerAnlegen.getName();
		vorname = this.benutzerAnlegen.getVorname();
		benutzername = this.benutzerAnlegen.getBenutzername();
		passwort = this.benutzerAnlegen.getPasswort();
		if(this.benutzerAnlegen.getStra�e().equals("")||this.benutzerAnlegen.getHausnummer().equals("") || this.benutzerAnlegen.getPLZ()==0 || this.benutzerAnlegen.getOrt().equals("")){
			adresseVorhanden = false;
		} else {
			adresseVorhanden = true;
			stra�e = this.benutzerAnlegen.getStra�e();
			hausnummer = this.benutzerAnlegen.getHausnummer();
			postleitzahl = this.benutzerAnlegen.getPLZ();
			ort = this.benutzerAnlegen.getOrt();
		}
		System.out.println("Adresse vorhanden: "+adresseVorhanden);
	}
	
	private void GUIDaten�ndern(){
		name = this.benutzer�ndern.getName();
		vorname = this.benutzer�ndern.getVorname();
		benutzername = this.benutzer�ndern.getBenutzername();
		passwort = this.benutzer�ndern.getPasswort();
		
		if(this.benutzer�ndern.getStra�e().equals("")||this.benutzer�ndern.getHausnummer().equals("") || this.benutzer�ndern.getPLZ()==0 || this.benutzer�ndern.getOrt().equals("")){
			adresseEingetragen = false;
		} else {
			adresseEingetragen = true;
			stra�e = this.benutzer�ndern.getStra�e();
			hausnummer = this.benutzer�ndern.getHausnummer();
			postleitzahl = this.benutzer�ndern.getPLZ();
			ort = this.benutzer�ndern.getOrt();
		}
		System.out.println("Adresse eingetragen: "+adresseEingetragen);
	}
	
	// bef�llt die f�r jedes Objekt identischen Tabellen
	private void ObjektErstellung(PersonABC person){
		try{
			String insertPerson;
			if(adresseVorhanden == true){
		// 1. einf�gen in Tabelle Person
			insertPerson = "INSERT INTO person(Vorname, Name, AdressID, Art) VALUES ('"+ person.getVorname()+ "','"+ person.getName()+ "',"+generatedAdressID+",'"+art+"');";
			adresseVorhanden=false;
			} else {
		// 1. einf�gen in Tabelle Person
			insertPerson = "INSERT INTO person(Vorname, Name, Art) VALUES ('"+ person.getVorname()+ "','"+ person.getName()+ "','"+art+"');";
			}
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
