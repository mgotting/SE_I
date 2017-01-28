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
	private BuchInventarisieren buchInventarisieren;
	private BuchStatus buchstatus;
	private Login login;
	private DB_connection con;
	private static String angemeldeterUser;
	private String name, vorname, benutzername, passwort, straße, ort, hausnummer, buchtitel, autor, isbn;
	private int postleitzahl, generatedID, generatedAdressID, adressID, anzahl;
	private char art;
	private Benutzer benutzer;
	private boolean adresseVorhanden;
	private Adresse adresse;
	private int counter = 0;

	// create reference to GUI
	public ButtonHandler(BenutzerAnlegen benutzerAnlegen) {
		this.benutzerAnlegen = benutzerAnlegen;
	}

	public ButtonHandler(BenutzerÄndern benutzerÄndern) {
		this.benutzerÄndern = benutzerÄndern;
	}

	public ButtonHandler(BuchAusleihe buchAusleihen) {
		this.buchAusleihen = buchAusleihen;
	}

	public ButtonHandler(BuchRueckgabe buchRückgabe) {
		this.buchRückgabe = buchRückgabe;
	}

	public ButtonHandler(BuchInventarisieren buchInventarisieren) {
		this.buchInventarisieren = buchInventarisieren;
	}

	public ButtonHandler(BuchStatus buchstatus) {
		this.buchstatus = buchstatus;
	}

	public ButtonHandler(Login login) {
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Prüfen, welches Kommando kommt
			switch (e.getActionCommand()) {
//Anwendungsfall anmelden:-------------------------------------------------------------------------------------------
			case "ANMELDEN":
				con = DB_connection.getDbConnection();
				if (login.getBenutzername().equals(con.executequery_Value(
						DB_connection.checkAnmeldung(login.getBenutzername(), login.getPasswort())))) {
					angemeldeterUser = login.getBenutzername();
					System.out.println(angemeldeterUser);
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
//F2: Anwendungsfall anlegen:--------------------------------------------------------------------------------------------
			case "ANLEGEN":
				GUIDaten();
				con = DB_connection.getDbConnection();
				if (adresseVorhanden == true) {
					// 4. einfügen in Tabelle Adresse
					Adresse adresse = new Adresse(straße, hausnummer, postleitzahl, ort);
					String insertAdresse = "INSERT INTO adresse(Straße, Hausnummer, Postleitzahl, Ort) VALUES ('"
							+ adresse.getStraße() + "','" + adresse.getHausnummer() + "','" + adresse.getPostleitzahl()
							+ "','" + adresse.getOrt() + "');";
					generatedAdressID = con.executequery_autoKey(insertAdresse, true);
				}

				if (benutzerAnlegen.tfName.getText().isEmpty() || benutzerAnlegen.tfVorname.getText().isEmpty()
						|| benutzerAnlegen.tfBenutzername.getText().isEmpty()
						|| benutzerAnlegen.tfPasswort.getText().isEmpty()) {
					// kein DB Eintrag
					JOptionPane.showMessageDialog(new JFrame(), "Fehler: Standardeingaben wurden nicht eingetragen!");
				} else {
					switch (this.benutzerAnlegen.getBenutzerArt()) {
  //Student anlegen---------------------------------------------------------------------------------------------------------
					case "Student":
						if (benutzerAnlegen.tfMatrikelnummer.getText().isEmpty()
								|| benutzerAnlegen.tfStudiengruppe.getText().isEmpty()) {
							// kein DB Eintrag
							JOptionPane.showMessageDialog(new JFrame(),
									"Fehler: Matrikelnummer oder Studiengruppe wurden nicht eingetragen!");
						} else {
							// einmal festlegen, nicht mehr änderbar
							int matrikelnummer = this.benutzerAnlegen.getMatrikelnummer();
							Studiengruppe studiengruppe = this.benutzerAnlegen.getStudiengruppe();
							Student student;
							art = 's';
							if (adresseVorhanden == false) {
								student = new Student(name, vorname, matrikelnummer, studiengruppe);
							} else {
								student = new Student(name, vorname, matrikelnummer, studiengruppe, straße, hausnummer,
										postleitzahl, ort);
							}
							// Wenn Studentenobjekt erfolgreich erstellt, dann
							// in Datenbank sichern
							ObjektErstellung(student);
							// 2. einfügen in Tabelle Student
							String insertStudent = "INSERT INTO student(Matrikelnummer, Studiengruppe, PersonID) VALUES ("
									+ student.getMatrikelnummer() + ",'" + student.getStudiengruppe().toString() + "',"
									+ generatedID + ");";
							boolean studentVerbucht = con.executequery(insertStudent);
							System.out.println("Student erfolgreich verbucht: " + studentVerbucht);
							JOptionPane.showMessageDialog(new JFrame(),
									"Student und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "
											+ benutzer.getBenutzername() + student.toString());
							benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
							EintragLöschen();
							con.disconnect();
						}
						break;
  //Professor anlegen---------------------------------------------------------------------------------------------------------
					case "Professor":
						if (benutzerAnlegen.tfFakultät.getText().isEmpty()) {
							// kein DB Eintrag
							JOptionPane.showMessageDialog(new JFrame(),
									"Fehler: Fakultät wurde nicht eingetragen!");
						} else {
							// Daten aus GUI abziehen und Professorobjekt
							// erstellen
							String fakultät = this.benutzerAnlegen.getFakultät();
							Professor professor;
							art = 'p';
							if (adresseVorhanden == false) {
								professor = new Professor(name, vorname, fakultät);
							} else {
								professor = new Professor(name, vorname, fakultät, straße, hausnummer, postleitzahl,
										ort);
							}
							// Wenn Professorenobjekt erfolgreich erstellt, dann
							// in Datenbank sichern
							ObjektErstellung(professor);
							// 2. einfügen in Tabelle Professor
							String insertProfessor = "INSERT INTO professor(Fakultät, PersonID) VALUES ('"
									+ professor.getFakultaet() + "'," + generatedID + ");";
							boolean professorVerbucht = con.executequery(insertProfessor);
							System.out.println("Professert erfolgreich verbucht: " + professorVerbucht);
							JOptionPane.showMessageDialog(new JFrame(),
									"Professor und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "
											+ benutzer.getBenutzername() + professor.toString());
							benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
							EintragLöschen();
							con.disconnect();
						}
						break;
  //Personal anlegen------------------------------------------------------------------------------------------------------
					case "Personal":
						// Daten aus GUI abziehen und Personalobjekt erstellen
						Personal personal = new Personal(name, vorname);
						art = 'b';
						ObjektErstellung(personal);
						// 2. einfügen in Tabelle Personal
						String insertPersonal = "INSERT INTO personal(PersonID) VALUES (" + generatedID + ");";
						boolean personalVerbucht = con.executequery(insertPersonal);
						System.out.println("Personal erfolgreich verbucht: " + personalVerbucht);
						JOptionPane.showMessageDialog(new JFrame(),
								"Personal und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "
										+ benutzer.getBenutzername() + personal.toString());
						this.benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
						EintragLöschen();
						con.disconnect();
						break;
					}
				}
				break;
  //zu ändernden Benutzer auswählen, damit sich die GUI mit den DB-Werten befüllt---------------------------------------------
			case "AUSWÄHLEN":

				if (benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow() == -1)
					throw new JTableException("Fehler: Zeile nicht markiert!");
				String art = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 12).toString();
				benutzerÄndern.setBenutzerArt(art);
				System.out.println("Welcher Benutzer bist du? " + art);

				benutzerÄndern.tfMatrikelnummer.setEditable(false);
				benutzerÄndern.tfStudiengruppe.setEditable(false);
				benutzerÄndern.tfFakultät.setEditable(false);
				benutzerÄndern.tfStraße.setEditable(false);
				benutzerÄndern.tfHausnummer.setEditable(false);
				benutzerÄndern.tfPostleitzahl.setEditable(false);
				benutzerÄndern.tfOrt.setEditable(false);

				String name = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 1).toString();
				benutzerÄndern.setName(name);
				String vorname = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 2).toString();
				benutzerÄndern.setVorname(vorname);
				String benutzername = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 3).toString();
				benutzerÄndern.setBenutzername(benutzername);
				String passwort = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 4).toString();
				benutzerÄndern.setPasswort(passwort);

				switch (art) {
   // Benutzer vom Typ Student (s) wurde ausgewählt
				case "s":
					String matrikelnummer = (String) benutzerÄndern.tableviewUser.getSQLTable()
							.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 5).toString();
					benutzerÄndern.setMatrikelnummer(matrikelnummer);
					String studiengruppe = (String) benutzerÄndern.tableviewUser.getSQLTable()
							.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 6).toString();
					benutzerÄndern.setStudiengruppe(studiengruppe);
					benutzerÄndern.tfStudiengruppe.setEditable(true);
					benutzerÄndern.tfStraße.setEditable(true);
					benutzerÄndern.tfHausnummer.setEditable(true);
					benutzerÄndern.tfPostleitzahl.setEditable(true);
					benutzerÄndern.tfOrt.setEditable(true);
					break;
  // Benutzer vom Typ Professor (p) wurde ausgewählt
				case "p":
					String fakultät = (String) benutzerÄndern.tableviewUser.getSQLTable()
							.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 7).toString();
					benutzerÄndern.setFakultät(fakultät);
					benutzerÄndern.tfFakultät.setEditable(true);
					benutzerÄndern.tfStraße.setEditable(true);
					benutzerÄndern.tfHausnummer.setEditable(true);
					benutzerÄndern.tfPostleitzahl.setEditable(true);
					benutzerÄndern.tfOrt.setEditable(true);
					break;
				}

				if (benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 13) == null) {
					adressID = -1;
				} else {
					adressID = Integer.parseInt(benutzerÄndern.tableviewUser.getSQLTable()
							.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 13).toString());
				}

				String straße = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 8).toString();
				benutzerÄndern.setStraße(straße);
				String hausnummer = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 9).toString();
				benutzerÄndern.setHausnummer(hausnummer);
				String postleitzahl = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 10).toString();
				benutzerÄndern.setPLZ(postleitzahl);
				String ort = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 11).toString();
				benutzerÄndern.setOrt(ort);

				break;

//3. Anwendungsfall ändern. DB-Werte werden in den jeweiligen Tabellen aktualisiert-------------------------------
			case "ÄNDERN":
				con = DB_connection.getDbConnection();

				String personID = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 0).toString();
				benutzerÄndern.setPersonID(personID);
				System.out.println("Welche PersonID hat der ausgewählte Benutzer: " + personID);

				art = (String) benutzerÄndern.tableviewUser.getSQLTable()
						.getValueAt(benutzerÄndern.tableviewUser.getSQLTable().getSelectedRow(), 12).toString();
				benutzerÄndern.setBenutzerArt(art);
				System.out.println("Welcher Benutzer bist du? " + art);

				// Personen-Informationen werden geändert
				System.out.println(benutzerÄndern.getVorname() + " " + benutzerÄndern.getName());
				String updatePerson = "UPDATE library.person SET Vorname = '" + benutzerÄndern.getVorname()
						+ "', Name = '" + benutzerÄndern.getName() + "' WHERE library.person.PersonID = ("
						+ benutzerÄndern.getPersonID() + ")";
				boolean personalPersonGeändert = con.executequery(updatePerson);
				System.out.println("Person erfolgreich geändert: " + personalPersonGeändert);
				// Benutzer-Informationen werden geändert
				String updateBenuzter = "UPDATE library.benutzer SET Benutzername = '"
						+ benutzerÄndern.getBenutzername() + "', Passwort = '" + benutzerÄndern.getPasswort()
						+ "' WHERE library.benutzer.PersonID= (" + benutzerÄndern.getPersonID() + ")";
				boolean BenutzerGeändert = con.executequery(updateBenuzter);
				System.out.println("Benutzer erfolgreich geändert: " + BenutzerGeändert);

				switch (art) {
  // Studenten-Infmormationen werden geändert, Matrikelnummer ausgeschlossen (diese wird einmal festgelegt)!
				case "s":
					String updateStudent = "UPDATE library.student SET Studiengruppe = '"
							+ benutzerÄndern.getStudiengruppe() + "' WHERE library.student.PersonID= ("
							+ benutzerÄndern.getPersonID() + ")";
					boolean studentGeändert = con.executequery(updateStudent);
					System.out.println("Student erfolgreich geändert: " + studentGeändert);
					break;
  // Professoren-Infmormationen werden geändert
				case "p":
					String updateProfessor = "UPDATE library.professor SET Fakultät = '" + benutzerÄndern.getFakultät()
							+ "' WHERE library.professor.PersonID= (" + benutzerÄndern.getPersonID() + ")";
					boolean professorGeändert = con.executequery(updateProfessor);
					System.out.println("Professor erfolgreich geändert: " + professorGeändert);
					break;
				}

				// Prüfung, ob bereits eine Adresse angelegt wurde
				if (adressID == -1) {
					System.out.println("Keine AdresseID vorhanden");
					if (benutzerÄndern.tfStraße.getText().isEmpty() || benutzerÄndern.tfHausnummer.getText().isEmpty()
							|| benutzerÄndern.tfPostleitzahl.getText().isEmpty()
							|| benutzerÄndern.tfOrt.getText().isEmpty()) {
						// kein DB Eintrag
						JOptionPane.showMessageDialog(new JFrame(), "Keine Adresse eingetragen");
					} else {
						adresse = new Adresse(this.straße, this.hausnummer, this.postleitzahl, this.ort);

						// neue Adresse in DB eintragen
						String insertAdresse = "INSERT INTO adresse(Straße, Hausnummer, Postleitzahl, Ort) VALUES ('"
								+ adresse.getStraße() + "','" + adresse.getHausnummer() + "',"
								+ adresse.getPostleitzahl() + ",'" + adresse.getOrt() + "');";
						generatedAdressID = con.executequery_autoKey(insertAdresse, true);

						// AdressID zur entsprechenden Person eintragen
						String updPerson = "UPDATE library.person SET AdressID = " + generatedAdressID
								+ " WHERE library.person.PersonID = " + Integer.parseInt(personID) + ";";
						boolean updPersonVerbucht = con.executequery(updPerson);
						boolean adresseVerbucht = con.executequery(insertAdresse);
						System.out.println("AdresseID erfolgreich in Tabelle Person verbucht: " + updPersonVerbucht);
						System.out.println("Adresse erfolgreich verbucht: " + adresseVerbucht);
					}

				} else {
					// wenn bereits eine Adresse vorhanden war, wird diese
					// aktualisiert
					System.out.println("Welche AdressID hast du? " + adressID);
					adresse = new Adresse(benutzerÄndern.tfStraße.getText().toString(),
							benutzerÄndern.tfHausnummer.getText().toString(),
							Integer.parseInt(benutzerÄndern.tfPostleitzahl.getText().toString()),
							benutzerÄndern.tfOrt.getText().toString());
					String updateAdresse = "UPDATE library.adresse SET Straße = '" + adresse.getStraße()
							+ "', Hausnummer = '" + adresse.getHausnummer() + "', Postleitzahl = "
							+ adresse.getPostleitzahl() + ", Ort = '" + adresse.getOrt()
							+ "' WHERE library.adresse.AdressID= " + adressID + "";
					System.out.println(updateAdresse);
					boolean adresseGeändert = con.executequery(updateAdresse);
					System.out.println("Adresse erfolgreich geändert: " + adresseGeändert);

				}

				benutzerÄndern.tableviewUser.updateSQLTable(DB_connection.getUserInfo());
				JOptionPane.showMessageDialog(new JFrame(), "Änderungen wurden erfolgreich verbucht!");
				EintragLöschenÄndern();
				con.disconnect();
				break;
//4. Anwendungsfall inventarisieren--------------------------------------------------------------------------------------
			case "INVENTARISIEREN":
				GUIDatenInv();
				con = DB_connection.getDbConnection();

				Buchtyp buch = new Buchtyp(autor, buchtitel, isbn);
				System.out.println("ActionCommand erhalten: " + e.getActionCommand());
				String insertBuch = "INSERT INTO buchtyp VALUES ('" + buch.getISBN() + "','" + buch.getAutor() + "','"
						+ buch.getTitel() + "');";
				con.executequery(insertBuch);

				System.out.println(buchInventarisieren.getStatus());

				if (buchInventarisieren.getStatus().equals("Ja")) {
					BuchstatusET bstatus = BuchstatusET.ausleihbar;
					ExemplarErstellung(buch, bstatus);
				} else {
					BuchstatusET bstatus = BuchstatusET.nichtausleihbar;
					ExemplarErstellung(buch, bstatus);
				}

				JOptionPane.showMessageDialog(new JFrame(), "Buch und Exemplar(e) wurden erfolgreich verbucht!");
				buchInventarisieren.tableviewBooks.updateSQLTable(DB_connection.getAllBooks());
				break;
//5.Anwendungsfall ausleihen--------------------------------------------------------------------------------------------
			case "AUSLEIHEN":
				if (counter == 2) {
					JOptionPane.showMessageDialog(new JFrame(), "Sie haben das Maximum von 2 Ausleihen erreicht");
					return;
				}
				counter++;
				System.out.println("is in Ausleihe drinnen" + angemeldeterUser);
				GUIDatenAus();
				con = DB_connection.getDbConnection();
				if (buchAusleihen.tfTitel.getText().isEmpty() || buchAusleihen.tfAutor.getText().isEmpty()
						|| buchAusleihen.tfIsbn.getText().isEmpty()) {
					// kein DB Eintrag
					JOptionPane.showMessageDialog(new JFrame(), "Fehler: Standardeingaben wurden nicht eingetragen!");
				} else {
					int BuchID = Integer.parseInt(buchAusleihen.tableviewBooks.getSQLTable()
							.getValueAt(buchAusleihen.tableviewBooks.getSQLTable().getSelectedRow(), 3).toString());
					Ausleihe ausleihe = new Ausleihe(BuchID, angemeldeterUser);
					System.out.println("ActionCommand erhalten: " + e.getActionCommand());
					String insertAusleihe = "INSERT INTO ausleihe VALUES('" + ausleihe.getBuchID() + "','"
							+ angemeldeterUser + "');";
					con.executequery(insertAusleihe);
					con.executequery("UPDATE library.exemplar SET Status = 'ausgeliehen' WHERE library.exemplar.BUCHID="
							+ BuchID);
					buchAusleihen.tableviewBooks.updateSQLTable(DB_connection.getAllAvailableBooks());
					JOptionPane.showMessageDialog(new JFrame(),
							"Der angemeldete User hat das Buch mit der ID: " + BuchID + " erfolgreich ausgeliehen.");
					System.out.println("Buch erfolgreich ausgeliehen");
				}
				break;
  //Buch auswählen----------------------------------------------------------------------------------------------------------
			case "BUCH_AUSWAHL":
				if (buchAusleihen.tableviewBooks.getSQLTable().getSelectedRow() == -1) {
					throw new JTableException("Fehler: Zeile nicht markiert!");
				}

				buchAusleihen.tfTitel.setEditable(false);
				buchAusleihen.tfAutor.setEditable(false);
				buchAusleihen.tfIsbn.setEditable(false);

				String titel = (String) buchAusleihen.tableviewBooks.getSQLTable()
						.getValueAt(buchAusleihen.tableviewBooks.getSQLTable().getSelectedRow(), 0).toString();
				buchAusleihen.setBuchtitel(titel);

				String autor = (String) buchAusleihen.tableviewBooks.getSQLTable()
						.getValueAt(buchAusleihen.tableviewBooks.getSQLTable().getSelectedRow(), 1).toString();
				buchAusleihen.setAutor(autor);

				String isbn = (String) buchAusleihen.tableviewBooks.getSQLTable()
						.getValueAt(buchAusleihen.tableviewBooks.getSQLTable().getSelectedRow(), 2).toString();
				buchAusleihen.setIsbn(isbn);

				break;
//F6: Anwendungsfall Buch zurückgeben------------------------------------------------------------------------------------
			case "BUCH_ZURÜCKGEBEN":
				String buchID = (String) buchRückgabe.tableview.getSQLTable()
						.getValueAt(buchRückgabe.tableview.getSQLTable().getSelectedRow(), 0).toString();
				con = DB_connection.getDbConnection();
				con.executequery(DB_connection.buchZurückgegeben(buchID));
				con.executequery(
						"UPDATE library.exemplar SET Status = 'ausleihbar' WHERE library.exemplar.BUCHID=" + buchID);
				buchRückgabe.tableview.updateSQLTable(DB_connection.getAllRentBooks(angemeldeterUser));
				JOptionPane.showMessageDialog(new JFrame(),
						"Der User "+angemeldeterUser+" hat das Buch mit der ID: " + buchID + " erfolgreich zurückgegeben.");
				System.out.println("Buch erfolgreich zurückgegeben");
			}
		} catch (AdressException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "AdressException: " + ex.getMessage());
		} catch (ArrayIndexOutOfBoundsException ex){
			JOptionPane.showMessageDialog(new JFrame(), "Bitte ein Buch zum zurückgeben auswählen");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Bitte Felder korrekt und vollständig ausfüllen");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "SQLException: " + ex.getMessage());
		} catch (Exception ex){
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
			}
	}
	

	/**
	 * Methode befüllt für jeden Benutzer die Grundinformationen und überprüft, ob eine Adresse vorhanden ist
	 *          
	 * @author Michael Gottinger
	 */
	private void GUIDaten() {
		name = this.benutzerAnlegen.getName();
		vorname = this.benutzerAnlegen.getVorname();
		benutzername = this.benutzerAnlegen.getBenutzername();
		passwort = this.benutzerAnlegen.getPasswort();
		if (this.benutzerAnlegen.getStraße().equals("") || this.benutzerAnlegen.getHausnummer().equals("")
				|| this.benutzerAnlegen.getPLZ() == 0 || this.benutzerAnlegen.getOrt().equals("")) {
			adresseVorhanden = false;
		} else {
			adresseVorhanden = true;
			straße = this.benutzerAnlegen.getStraße();
			hausnummer = this.benutzerAnlegen.getHausnummer();
			postleitzahl = this.benutzerAnlegen.getPLZ();
			ort = this.benutzerAnlegen.getOrt();
		}
		System.out.println("Adresse vorhanden: " + adresseVorhanden);
	}

	private void GUIDatenInv() {
		buchtitel = this.buchInventarisieren.getBuchtitel();
		autor = this.buchInventarisieren.getAutor();
		isbn = this.buchInventarisieren.getISBN();
		anzahl = this.buchInventarisieren.getAnzahl();

	}

	private void GUIDatenAus() {
		buchtitel = this.buchAusleihen.getBuchtitel();
		autor = this.buchAusleihen.getAutor();
		isbn = this.buchAusleihen.getISBN();

	}

	/**
	 * Methode befüllt die Tabelle Adresse -sofern Adresse vorhanden-, Person und Benutzer
	 * 
	 * @param Person für die Objekte erstellt werden sollen
	 *          
	 * @author Michael Gottinger
	 */
	private void ObjektErstellung(PersonABC person) {
		try {
			String insertPerson;
			if (adresseVorhanden == true) {
				// 1. einfügen in Tabelle Person
				insertPerson = "INSERT INTO person(Vorname, Name, AdressID, Art) VALUES ('" + person.getVorname()
						+ "','" + person.getName() + "'," + generatedAdressID + ",'" + art + "');";
				adresseVorhanden = false;
			} else {
				// 1. einfügen in Tabelle Person
				insertPerson = "INSERT INTO person(Vorname, Name, Art) VALUES ('" + person.getVorname() + "','"
						+ person.getName() + "','" + art + "');";
			}
			generatedID = con.executequery_autoKey(insertPerson, true);
			System.out.println("Erstellte PersonID: " + generatedID);
			// 3. einfügen in Tabelle Benutzer
			benutzer = new Benutzer(benutzername, passwort, person);
			// Wenn Studentenobjekt erfolgreich erstellt, dann inDatenbank
			// sichern
			String insertBenutzer = "INSERT INTO benutzer(Benutzername, Passwort, PersonID) VALUES ('"
					+ benutzer.getBenutzername() + "','" + benutzer.getPasswort() + "'," + generatedID + ");";
			boolean benutzerVerbucht = con.executequery(insertBenutzer);
			System.out.println("Benutzer erfolgreich verbucht: " + benutzerVerbucht);
		} catch (SQLException sqlEx) {
			JOptionPane.showMessageDialog(new JFrame(), sqlEx.getMessage());
		}
	}

	private void ExemplarErstellung(Buchtyp buch, BuchstatusET bstatus) throws SQLException {
		int i = buchInventarisieren.getAnzahl();
		System.out.println("i = " + i);
		while (i > 0) {
			Exemplar exemplar = new Exemplar(bstatus, buch);
			String insertExemplar = "INSERT INTO exemplar (ISBN,Status) VALUES ('" + exemplar.getISBN() + "','"
					+ exemplar.getStatus() + "');";
			generatedID = con.executequery_autoKey(insertExemplar, true);
			System.out.println("Erstellte BuchID: " + generatedID);
			System.out.println("Exemplar:" + i + " erfolgreich inventarisiert");
			i--;
		}
	}

	/**
	 * Methode entfernt alle Einträge aus der benutzerAnlegen GUI.
	 *          
	 * @author Michael Gottinger
	 */
	private void EintragLöschen() {
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

	// entfernt alle Einträge in GUI nach Aktualisierung/Änderung
	private void EintragLöschenÄndern() {
		benutzerÄndern.setName(null);
		benutzerÄndern.setVorname(null);
		benutzerÄndern.setBenutzername(null);
		benutzerÄndern.setPasswort(null);
		benutzerÄndern.setFakultät(null);
		benutzerÄndern.setMatrikelnummer(null);
		benutzerÄndern.setStudiengruppe(null);
		benutzerÄndern.setStraße(null);
		benutzerÄndern.setHausnummer(null);
		benutzerÄndern.setOrt(null);
		benutzerÄndern.setPLZ(null);
	}
}
