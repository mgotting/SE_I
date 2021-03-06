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
	private BuchInventarisieren buchInventarisieren;
	private static Login login;
	private static DB_connection con;
	private static String angemeldeterUser;
	private static char benutzerArt;
	private String name, vorname, benutzername, passwort, stra�e, ort, hausnummer, buchtitel, autor, isbn,
			insertAdresse;
	private int postleitzahl, generatedID, generatedAdressID, adressID;
	private char art;
	private Benutzer benutzer;
	private boolean adresseVorhanden;
	private Adresse adresse;
	private PersonABC person;

	// create reference to GUI
	public ButtonHandler(BenutzerAnlegen benutzerAnlegen) {
		this.benutzerAnlegen = benutzerAnlegen;
	}

	public ButtonHandler(Benutzer�ndern benutzer�ndern) {
		this.benutzer�ndern = benutzer�ndern;
	}

	public ButtonHandler(BuchAusleihe buchAusleihen) {
		this.buchAusleihen = buchAusleihen;
	}

	public ButtonHandler(BuchRueckgabe buchR�ckgabe) {
		this.buchR�ckgabe = buchR�ckgabe;
	}

	public ButtonHandler(BuchInventarisieren buchInventarisieren) {
		this.buchInventarisieren = buchInventarisieren;
	}

	public ButtonHandler(Login login) {
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Pr�fen, welches Kommando kommt
			switch (e.getActionCommand()) {
			// Anmeldemaske---------------------------------------------------------------------------------------------------
			case "ANMELDEN":
				con = DB_connection.getDbConnection();
				angemeldeterUser = login.getBenutzername();
				if (Pr�fungAnmeldung() == true) {
					login.panel.setVisible(false);
					personAnlegen();
					Startmenu startmenu = new Startmenu();
					startmenu.launchStartmenu(this.login.login, art);
					System.out.println(angemeldeterUser + " erfolgreich angemeldet");
				} else {
					System.out.println(angemeldeterUser + " konne nicht angemeldet werden");
					JOptionPane.showMessageDialog(new JFrame(), "Benutzername / Passwort falsch");
					login.setBenutzername(null);
					login.setPasswort(null);
				}
				break;
			// F2 Anwendungsfall: Benutzer integriert
			// anlegen--------------------------------------------------------------------------------------------
			case "ANLEGEN":
				if (pflichtfelderNichtBef�llt()) {
					JOptionPane.showMessageDialog(new JFrame(), "Fehler: Standardeingaben wurden nicht eingetragen!");
					System.out.println("Standardeingaben wurden nicht eingetragen");
					break;
				}
				if (adresseNichtKorrektBef�llt()) {
					JOptionPane.showMessageDialog(new JFrame(), "Die Adresse ist nicht vollst�ndig eingetragen");
					System.out.println("Adresse nicht vollst�ndig eingetragen");
					break;
				}
				GUIDaten();
				con = DB_connection.getDbConnection();
				if (adresseVorhanden == true) {
					// 4. einf�gen in Tabelle Adresse
					Adresse adresse = new Adresse(stra�e, hausnummer, postleitzahl, ort);
					insertAdresse = "INSERT INTO adresse(Stra�e, Hausnummer, Postleitzahl, Ort) VALUES ('"
							+ adresse.getStra�e() + "','" + adresse.getHausnummer() + "','" + adresse.getPostleitzahl()
							+ "','" + adresse.getOrt() + "');";
				}
				switch (this.benutzerAnlegen.getBenutzerArt()) {
				// ------------------Student
				// anlegen---------------------------------------------------------------------------------------------------------
				case "Student":
					if (benutzerAnlegen.tfMatrikelnummer.getText().isEmpty()
							|| benutzerAnlegen.getStudiengruppe().toString().isEmpty()) {
						// kein DB Eintrag
						JOptionPane.showMessageDialog(new JFrame(),
								"Fehler: Matrikelnummer oder Studiengruppe wurden nicht eingetragen!");
						break;
					} else {
						// einmal festlegen, nicht mehr �nderbar
						int matrikelnummer = this.benutzerAnlegen.getMatrikelnummer();
						Studiengruppe studiengruppe = this.benutzerAnlegen.getStudiengruppe();
						Student student;
						art = 's';
						if (adresseVorhanden == false) {
							student = new Student(name, vorname, matrikelnummer, studiengruppe);
						} else {
							student = new Student(name, vorname, matrikelnummer, studiengruppe, stra�e, hausnummer,
									postleitzahl, ort);
							generatedAdressID = con.executequery_autoKey(insertAdresse, true);
						}
						// Wenn Studentenobjekt erfolgreich erstellt, dann
						// in Datenbank sichern
						ObjektErstellung(student);
						// 2. einf�gen in Tabelle Student
						String insertStudent = "INSERT INTO student(Matrikelnummer, Studiengruppe, PersonID) VALUES ("
								+ student.getMatrikelnummer() + ",'" + student.getStudiengruppe().toString() + "',"
								+ generatedID + ");";
						boolean studentVerbucht = con.executequery(insertStudent);
						System.out.println("Student erfolgreich verbucht: " + studentVerbucht);
						JOptionPane.showMessageDialog(new JFrame(),
								"Student und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "
										+ benutzer.getBenutzername() + student.toString());
						benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
						EintragL�schen();
						con.disconnect();
					}
					break;
				// ------------------Professor
				// anlegen---------------------------------------------------------------------------------------------------------
				case "Professor":
					if (benutzerAnlegen.tfFakult�t.getText().isEmpty()) {
						// kein DB Eintrag
						JOptionPane.showMessageDialog(new JFrame(), "Fehler: Fakult�t wurde nicht eingetragen!");
						break;
					} else {
						// Daten aus GUI abziehen und Professorobjekt
						// erstellen
						String fakult�t = this.benutzerAnlegen.getFakult�t();
						Professor professor;
						art = 'p';
						if (adresseVorhanden == false) {
							professor = new Professor(name, vorname, fakult�t);
						} else {
							professor = new Professor(name, vorname, fakult�t, stra�e, hausnummer, postleitzahl, ort);
							generatedAdressID = con.executequery_autoKey(insertAdresse, true);
						}
						// Wenn Professorenobjekt erfolgreich erstellt, dann
						// in Datenbank sichern
						ObjektErstellung(professor);
						// 2. einf�gen in Tabelle Professor
						String insertProfessor = "INSERT INTO professor(Fakult�t, PersonID) VALUES ('"
								+ professor.getFakultaet() + "'," + generatedID + ");";
						boolean professorVerbucht = con.executequery(insertProfessor);
						System.out.println("Professert erfolgreich verbucht: " + professorVerbucht);
						JOptionPane.showMessageDialog(new JFrame(),
								"Professor und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "
										+ benutzer.getBenutzername() + professor.toString());
						benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
						EintragL�schen();
						con.disconnect();
					}
					break;
				// ------------------Personal
				// anlegen------------------------------------------------------------------------------------------------------
				case "Personal":
					// Daten aus GUI abziehen und Personalobjekt erstellen
					Personal personal = new Personal(name, vorname);
					art = 'b';
					ObjektErstellung(personal);
					// 2. einf�gen in Tabelle Personal
					String insertPersonal = "INSERT INTO personal(PersonID) VALUES (" + generatedID + ");";
					boolean personalVerbucht = con.executequery(insertPersonal);
					System.out.println("Personal erfolgreich verbucht: " + personalVerbucht);
					JOptionPane.showMessageDialog(new JFrame(),
							"Personal und Benutzer mit folgenden Daten integriert angelegt:\n\nBenutzername: "
									+ benutzer.getBenutzername() + personal.toString());
					this.benutzerAnlegen.tableviewUser.updateSQLTable(DB_connection.getAllUsers());
					EintragL�schen();
					con.disconnect();
					break;
				}
				break;
			// TODO: L�schen
			// // zu �ndernden Benutzer ausw�hlen, damit sich die GUI mit den
			// // DB-Werten bef�llt---------------------------------------------
			// case "AUSW�HLEN":
			// benutzer�ndern.adresseSperren();
			// if (benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow()
			// == -1)
			// throw new JTableException("Fehler: Zeile nicht markiert!");
			// String art = (String) benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 11).toString();
			// benutzer�ndern.setBenutzerArt(art);
			// System.out.println("Welcher Benutzer bist du? " + art);
			//
			// String name = (String) benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 1).toString();
			// benutzer�ndern.setName(name);
			// benutzer�ndern.tfName.setEditable(true);
			// String vorname = (String)
			// benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 2).toString();
			// benutzer�ndern.setVorname(vorname);
			// benutzer�ndern.tfVorname.setEditable(true);
			// String benutzername = (String)
			// benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 3).toString();
			// benutzer�ndern.setBenutzername(benutzername);
			// benutzer�ndern.tfBenutzername.setEditable(true);
			//// String passwort = (String)
			// benutzer�ndern.tableviewUser.getSQLTable()
			//// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 4).toString();
			// String passwort = (String) DB_connection.Passw�rter().toString();
			// benutzer�ndern.setPasswort(passwort);
			// benutzer�ndern.tfPasswort.setEditable(true);
			//
			// switch (art) {
			// // Benutzer vom Typ Student (s) wurde ausgew�hlt
			// case "s":
			// String matrikelnummer = (String)
			// benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 4).toString();
			// benutzer�ndern.setMatrikelnummer(matrikelnummer);
			// Studiengruppe studiengruppe = Studiengruppe.valueOf(
			// benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 5).toString());
			// benutzer�ndern.setStudiengruppe(studiengruppe);
			// benutzer�ndern.studiengruppe.setEditable(true);
			// benutzer�ndern.adresseFreigeben();
			// break;
			// // Benutzer vom Typ Professor (p) wurde ausgew�hlt
			// case "p":
			// String fakult�t = (String)
			// benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 6).toString();
			// benutzer�ndern.setFakult�t(fakult�t);
			// benutzer�ndern.tfFakult�t.setEditable(true);
			// benutzer�ndern.adresseFreigeben();
			// break;
			// }
			//
			// if (benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 12) == null) {
			// adressID = -1;
			// } else {
			// adressID =
			// Integer.parseInt(benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 12).toString());
			// }
			//
			// String stra�e = (String)
			// benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 7).toString();
			// benutzer�ndern.setStra�e(stra�e);
			// String hausnummer = (String)
			// benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 8).toString();
			// benutzer�ndern.setHausnummer(hausnummer);
			// String postleitzahl = (String)
			// benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 9).toString();
			// benutzer�ndern.setPLZ(postleitzahl);
			// String ort = (String) benutzer�ndern.tableviewUser.getSQLTable()
			// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
			// 10).toString();
			// benutzer�ndern.setOrt(ort);
			//
			// break;

			// 3. Anwendungsfall �ndern. DB-Werte werden in den jeweiligen
			// Tabellen aktualisiert-------------------------------
			case "�NDERN":

				// TODO:L�schen
				// String personID = (String)
				// benutzer�ndern.tableviewUser.getSQLTable()
				// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
				// 0).toString();
				// benutzer�ndern.setPersonID(personID);
				// System.out.println("Welche PersonID hat der ausgew�hlte
				// Benutzer: " + personID);
				//
				// art = (String) benutzer�ndern.tableviewUser.getSQLTable()
				// .getValueAt(benutzer�ndern.tableviewUser.getSQLTable().getSelectedRow(),
				// 11).toString();
				// benutzer�ndern.setBenutzerArt(art);
				// System.out.println("Welcher Benutzer bist du? " + art);

				// Personen-Informationen werden ge�ndert
				System.out.println(benutzer�ndern.getVorname() + " " + benutzer�ndern.getName());
				String updatePerson = "UPDATE library.person SET Vorname = '" + benutzer�ndern.getVorname()
						+ "', Name = '" + benutzer�ndern.getName() + "' WHERE library.person.PersonID = ("
						+ benutzer�ndern.getPersonID() + ")";
				boolean personalPersonGe�ndert = con.executequery(updatePerson);
				System.out.println("Person erfolgreich ge�ndert: " + personalPersonGe�ndert);
				// Benutzer-Informationen werden ge�ndert
				String updateBenuzter = "UPDATE library.benutzer SET Benutzername = '"
						+ benutzer�ndern.getBenutzername() + "', Passwort = '" + benutzer�ndern.getPasswort()
						+ "' WHERE library.benutzer.PersonID= (" + benutzer�ndern.getPersonID() + ")";
				boolean BenutzerGe�ndert = con.executequery(updateBenuzter);
				System.out.println("Benutzer erfolgreich ge�ndert: " + BenutzerGe�ndert);

				switch (this.benutzer�ndern.getBenutzerArt()) {
				// Studenten-Infmormationen werden ge�ndert, Matrikelnummer
				// ausgeschlossen (diese wird einmal festgelegt)!
				case "Student":
					String updateStudent = "UPDATE library.student SET Studiengruppe = '"
							+ benutzer�ndern.getStudiengruppe() + "' WHERE library.student.PersonID= ("
							+ benutzer�ndern.getPersonID() + ")";
					boolean studentGe�ndert = con.executequery(updateStudent);
					System.out.println("Student erfolgreich ge�ndert: " + studentGe�ndert);
					break;
				// Professoren-Infmormationen werden ge�ndert
				case "Professor":
					String updateProfessor = "UPDATE library.professor SET Fakult�t = '" + benutzer�ndern.getFakult�t()
							+ "' WHERE library.professor.PersonID= (" + benutzer�ndern.getPersonID() + ")";
					boolean professorGe�ndert = con.executequery(updateProfessor);
					System.out.println("Professor erfolgreich ge�ndert: " + professorGe�ndert);
					break;
				}

				// Pr�fung, ob bereits eine Adresse angelegt wurde
				if (adressID == -1) {
					System.out.println("Keine AdresseID vorhanden");
					if (benutzer�ndern.tfStra�e.getText().isEmpty() || benutzer�ndern.tfHausnummer.getText().isEmpty()
							|| benutzer�ndern.tfPostleitzahl.getText().isEmpty()
							|| benutzer�ndern.tfOrt.getText().isEmpty()) {
						// kein DB Eintrag
						JOptionPane.showMessageDialog(new JFrame(), "Keine Adresse eingetragen");
					} else {
						adresse = new Adresse(this.stra�e, this.hausnummer, this.postleitzahl, this.ort);

						// neue Adresse in DB eintragen
						String insertAdresse = "INSERT INTO adresse(Stra�e, Hausnummer, Postleitzahl, Ort) VALUES ('"
								+ benutzer�ndern.getStra�e() + "','" + benutzer�ndern.getHausnummer() + "',"
								+ benutzer�ndern.getPLZ() + ",'" + benutzer�ndern.getOrt() + "');";
						generatedAdressID = con.executequery_autoKey(insertAdresse, true);

						// AdressID zur entsprechenden Person eintragen
						String updPerson = "UPDATE library.person SET AdressID = " + generatedAdressID
								+ " WHERE library.person.PersonID = " + Integer.parseInt(benutzer�ndern.getPersonID())
								+ ";";
						boolean updPersonVerbucht = con.executequery(updPerson);
						boolean adresseVerbucht = con.executequery(insertAdresse);
						System.out.println("AdresseID erfolgreich in Tabelle Person verbucht: " + updPersonVerbucht);
						System.out.println("Adresse erfolgreich verbucht: " + adresseVerbucht);
					}

				} else {
					// wenn bereits eine Adresse vorhanden war, wird diese
					// aktualisiert
					System.out.println("Welche AdressID hast du? " + adressID);
					adresse = new Adresse(benutzer�ndern.tfStra�e.getText().toString(),
							benutzer�ndern.tfHausnummer.getText().toString(),
							Integer.parseInt(benutzer�ndern.tfPostleitzahl.getText().toString()),
							benutzer�ndern.tfOrt.getText().toString());
					String updateAdresse = "UPDATE library.adresse SET Stra�e = '" + adresse.getStra�e()
							+ "', Hausnummer = '" + adresse.getHausnummer() + "', Postleitzahl = "
							+ adresse.getPostleitzahl() + ", Ort = '" + adresse.getOrt()
							+ "' WHERE library.adresse.AdressID= " + adressID + "";
					boolean adresseGe�ndert = con.executequery(updateAdresse);
					System.out.println("Adresse erfolgreich ge�ndert: " + adresseGe�ndert);

				}
				// TODO:L�schen
				// benutzer�ndern.tableviewUser.updateSQLTable(DB_connection.getUserInfo());
				JOptionPane.showMessageDialog(new JFrame(), "�nderungen wurden erfolgreich verbucht!");
				EintragL�schen�ndern();
				con.disconnect();
				break;
			// 4. Anwendungsfall
			// inventarisieren--------------------------------------------------------------------------------------
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

				if (this.buchInventarisieren.getAnzahl() == 0) {
					JOptionPane.showMessageDialog(new JFrame(), "Buch wurde erfolgreich verbucht");
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Buch und Exemplar(e) wurden erfolgreich verbucht!");
				}
				buchInventarisieren.tableviewBooks.updateSQLTable(DB_connection.getAllBooks());
				break;

			// Buch
			// ausw�hlen----------------------------------------------------------------------------------------------------------
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

			// 5.Anwendungsfall
			// ausleihen--------------------------------------------------------------------------------------------
			case "AUSLEIHEN":
				if (Pr�fungAusleihe() == 3) {
					JOptionPane.showMessageDialog(new JFrame(), "Sie haben das Maximum von 3 Ausleihen erreicht");
					return;
				}
				GUIDatenAus();
				buchAusleihen.svpnt = con.createSavepoint("Ausleihe");
				con = DB_connection.getDbConnection();
				if (buchAusleihen.tfTitel.getText().isEmpty() || buchAusleihen.tfAutor.getText().isEmpty()
						|| buchAusleihen.tfIsbn.getText().isEmpty()) {
					// kein DB Eintrag
					JOptionPane.showMessageDialog(new JFrame(), "Fehler: Standardeingaben wurden nicht eingetragen!");
					return;
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
					buchAusleihen.tableviewRentBooks
							.updateSQLTable(DB_connection.getAllRentBooks(ButtonHandler.getAngemeldeterUser()));
				}
				break;

			case "BEST�TIGEN":
				try {
					if (con != null) {
						con.commit();
						con.disconnect();
					}

					JOptionPane.showMessageDialog(new JFrame(), "B�cher erfolgreich ausgeliehen");
					System.out.println("Buch erfolgreich ausgeliehen");
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
				break;

			case "ABBRECHEN":
				System.out.println("Trying rollback");

				try {

					if (con != null) {
						con.rollbackChanges(buchAusleihen.svpnt);

						buchAusleihen.tfAutor.setText(null);
						buchAusleihen.tfIsbn.setText(null);
						buchAusleihen.tfTitel.setText(null);

						buchAusleihen.tfTitel.setEditable(true);
						buchAusleihen.tfAutor.setEditable(true);
						buchAusleihen.tfIsbn.setEditable(true);

						buchAusleihen.tableviewBooks.updateSQLTable(DB_connection.getAllAvailableBooks());
						buchAusleihen.tableviewRentBooks
								.updateSQLTable(DB_connection.getAllRentBooks(ButtonHandler.getAngemeldeterUser()));

					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				break;
			// F6: Anwendungsfall Buch
			// zur�ckgeben------------------------------------------------------------------------------------
			case "BUCH_ZUR�CKGEBEN":
				String buchID = (String) buchR�ckgabe.tableview.getSQLTable()
						.getValueAt(buchR�ckgabe.tableview.getSQLTable().getSelectedRow(), 0).toString();
				con = DB_connection.getDbConnection();
				con.executequery(DB_connection.buchZur�ckgegeben(buchID));
				con.executequery(
						"UPDATE library.exemplar SET Status = 'ausleihbar' WHERE library.exemplar.BUCHID=" + buchID);
				buchR�ckgabe.tableview.updateSQLTable(DB_connection.getAllRentBooks(angemeldeterUser));
				JOptionPane.showMessageDialog(new JFrame(), "Der User " + angemeldeterUser
						+ " hat das Buch mit der ID: " + buchID + " erfolgreich zur�ckgegeben.");
				System.out.println("Buch erfolgreich zur�ckgegeben");
			}
		} catch (AdressException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "AdressException: " + ex.getMessage());
		} catch (ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Bitte ein Buch zum zur�ckgeben ausw�hlen");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Bitte Felder korrekt und vollst�ndig ausf�llen");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "SQLException: " + ex.getMessage());
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "SQLException: " + ex.getMessage());

		}
	}

	/**
	 * Methode bef�llt f�r jeden Benutzer die Grundinformationen und �berpr�ft,
	 * ob eine Adresse vorhanden ist
	 * 
	 * @author Michael Gottinger
	 */
	private void GUIDaten() {
		name = this.benutzerAnlegen.getName();
		vorname = this.benutzerAnlegen.getVorname();
		benutzername = this.benutzerAnlegen.getBenutzername();
		passwort = this.benutzerAnlegen.getPasswort();
		if (adresseNichtVorhanden()) {
			adresseVorhanden = false;
		} else {
			if (adresseNichtKorrektBef�llt()) {
				adresseVorhanden = false;
			} else {
				adresseVorhanden = true;
				stra�e = this.benutzerAnlegen.getStra�e();
				hausnummer = this.benutzerAnlegen.getHausnummer();
				postleitzahl = this.benutzerAnlegen.getPLZ();
				ort = this.benutzerAnlegen.getOrt();
			}
		}
		System.out.println("Adresse vorhanden: " + adresseVorhanden);
	}

	private void GUIDatenInv() {
		buchtitel = this.buchInventarisieren.getBuchtitel();
		autor = this.buchInventarisieren.getAutor();
		isbn = this.buchInventarisieren.getISBN();

	}

	private void GUIDatenAus() {
		buchtitel = this.buchAusleihen.getBuchtitel();
		autor = this.buchAusleihen.getAutor();
		isbn = this.buchAusleihen.getISBN();

	}

	/**
	 * Methode bef�llt die Tabelle Adresse -sofern Adresse vorhanden-, Person
	 * und Benutzer
	 * 
	 * @param Person
	 *            f�r die Objekte erstellt werden sollen
	 * 
	 * @author Michael Gottinger
	 */
	private void ObjektErstellung(PersonABC person) {
		try {
			String insertPerson;
			if (adresseVorhanden == true) {
				// 1. einf�gen in Tabelle Person
				insertPerson = "INSERT INTO person(Vorname, Name, AdressID, Art) VALUES ('" + person.getVorname()
						+ "','" + person.getName() + "'," + generatedAdressID + ",'" + art + "');";
				adresseVorhanden = false;
			} else {
				// 1. einf�gen in Tabelle Person
				insertPerson = "INSERT INTO person(Vorname, Name, Art) VALUES ('" + person.getVorname() + "','"
						+ person.getName() + "','" + art + "');";
			}
			generatedID = con.executequery_autoKey(insertPerson, true);
			System.out.println("Erstellte PersonID: " + generatedID);
			// 3. einf�gen in Tabelle Benutzer
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
	 * Methode entfernt alle Eintr�ge aus der benutzerAnlegen GUI.
	 * 
	 * @author Michael Gottinger
	 */
	private void EintragL�schen() {
		benutzerAnlegen.setName(null);
		benutzerAnlegen.setVorname(null);
		benutzerAnlegen.setBenutzername(null);
		benutzerAnlegen.setPasswort(null);
		benutzerAnlegen.setFakult�t(null);
		benutzerAnlegen.setMatrikelnummer(null);
		benutzerAnlegen.setStra�e(null);
		benutzerAnlegen.setHausnummer(null);
		benutzerAnlegen.setOrt(null);
		benutzerAnlegen.setPLZ(null);
	}

	/**
	 * Methode entfernt alle Eintr�ge aus der benutzer�ndern GUI, nach dem
	 * �nderungen durchgef�hrt wurden.
	 * 
	 * @author Sandra Speckmeier
	 */

	private void EintragL�schen�ndern() {
		benutzer�ndern.setName(null);
		benutzer�ndern.setVorname(null);
		benutzer�ndern.setBenutzername(null);
		benutzer�ndern.setPasswort(null);
		benutzer�ndern.setFakult�t(null);
		benutzer�ndern.setMatrikelnummer(null);
		benutzer�ndern.setStudiengruppe(null);
		benutzer�ndern.setStra�e(null);
		benutzer�ndern.setHausnummer(null);
		benutzer�ndern.setOrt(null);
		benutzer�ndern.setPLZ(null);
	}

	/**
	 * Methode pr�ft, ob Anmeldung durchgef�hrt werden kann
	 * 
	 * @author Michael Gottinger
	 */
	private boolean Pr�fungAnmeldung() throws SQLException {
		return angemeldeterUser
				.equals(con.executequery_Value(DB_connection.checkAnmeldung(angemeldeterUser, login.getPasswort()), 1));
	}

	private int Pr�fungAusleihe() throws SQLException {
		return Integer.parseInt(con.executequery_Value(DB_connection.getAnzahlAusleihe(angemeldeterUser), 1));
	}

	public static String getAngemeldeterUser() {
		return angemeldeterUser;
	}

	public static char getArt() throws SQLException {
		return benutzerArt = (con.executequery_Value(
				DB_connection.getPerson(
						con.executequery_Value(DB_connection.checkAnmeldung(angemeldeterUser, login.getPasswort()), 3)),
				5)).charAt(0);
	}

	/**
	 * Methode legt f�r den anzumeldenden Benutzer die Objekte Person sowie
	 * Benutzer an
	 * 
	 * @author Michael Gottinger
	 */
	private void personAnlegen() throws SQLException {
		String personID = con.executequery_Value(DB_connection.checkAnmeldung(angemeldeterUser, login.getPasswort()),
				3);
		art = (con.executequery_Value(DB_connection.getPerson(personID), 5)).charAt(0);
		String vorname = con.executequery_Value(DB_connection.getPerson(personID), 2);
		String nachname = con.executequery_Value(DB_connection.getPerson(personID), 3);
		if (art == 's') {
			int matrikelnummer = Integer.parseInt(con.executequery_Value(DB_connection.getStudent(personID), 3));
			Studiengruppe studiengruppe = Studiengruppe
					.valueOf(con.executequery_Value(DB_connection.getStudent(personID), 2));
			person = new Student(nachname, vorname, matrikelnummer, studiengruppe);
			System.out.println("Student erstellt");
		}
		if (art == 'p') {
			String fakult�t = con.executequery_Value(DB_connection.getProfessor(personID), 1);
			person = new Professor(nachname, vorname, fakult�t);
			System.out.println("Professor erstellt");
		} else {
			person = new Personal(nachname, vorname);
			System.out.println("Personal erstellt");
		}
		benutzer = new Benutzer(angemeldeterUser, login.getPasswort(), person);
		System.out.println("Benutzer erstellt");
	}

	/**
	 * Methode pr�ft ob alle Pflichtfelder bef�llt sind
	 * 
	 * @author Michael Gottinger
	 * 
	 * @return Wahrheitswert ob Pflichtfeld nicht bef�llt ist
	 */
	private boolean pflichtfelderNichtBef�llt() {
		return benutzerAnlegen.tfName.getText().isEmpty() || benutzerAnlegen.tfVorname.getText().isEmpty()
				|| benutzerAnlegen.tfBenutzername.getText().isEmpty() || benutzerAnlegen.tfPasswort.getText().isEmpty();
	}

	/**
	 * Methode pr�ft ob Adresseingabe leer ist
	 * 
	 * @author Michael Gottinger
	 * 
	 * @return Wahrheitswert ob Adressfelder komplett leer sind
	 */
	private boolean adresseNichtVorhanden() {
		return benutzerAnlegen.tfStra�e.getText().isEmpty() && benutzerAnlegen.tfHausnummer.getText().isEmpty()
				&& benutzerAnlegen.tfPostleitzahl.getText().isEmpty() && benutzerAnlegen.tfOrt.getText().isEmpty();
	}

	/**
	 * Methode pr�ft ob Textfeld bei Adresse vollst�ndig bef�llt
	 * 
	 * @author Michael Gottinger
	 * 
	 * @return Wahrheitswert ob Adresse nicht vollst�ndig bef�llt wurde
	 */
	private boolean adresseNichtKorrektBef�llt() {
		boolean adresseNichtKorrektBef�llt;
		if (adresseNichtVorhanden()) {
			adresseNichtKorrektBef�llt = false;
		} else {
			adresseNichtKorrektBef�llt = benutzerAnlegen.tfStra�e.getText().isEmpty()
					|| benutzerAnlegen.tfHausnummer.getText().isEmpty()
					|| benutzerAnlegen.tfPostleitzahl.getText().isEmpty() || benutzerAnlegen.tfOrt.getText().isEmpty();
		}
		return adresseNichtKorrektBef�llt;
	}
}
