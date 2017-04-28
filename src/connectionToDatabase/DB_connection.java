package connectionToDatabase;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.Benutzer;
import gui.BenutzerAnlegen;
import gui.Login;

/**
 * @author Michael Gottinger, Sandra Speckmeier Klasse mithilfe derer eine
 *         Verbindung zur Datenbank hergestellt wird sowie Abfragen ausgeführt
 *         werden können.
 */

public class DB_connection {
	// implement singleton pattern with static elements
	private static DB_connection instance;
	private static Connection cn = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	private String sDbDriver = null, sDbUrl = null, sUsr = "", sPwd = "";
	{
		sDbDriver = "com.mysql.jdbc.Driver";
		sDbUrl = "jdbc:mysql://localhost:3306/library";
		sUsr = "root";
		sPwd = "init";
	}

	// default constructor
	private DB_connection() {
	}

	// connect to database
	private void connect_DB() {
		try { // select fitting database driver and connect
			Class.forName(sDbDriver);
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		}
		try {
			cn = DriverManager.getConnection(sDbUrl, sUsr, sPwd);
			cn.setAutoCommit(false);
			cn.setTransactionIsolation(4);
			// cn.setTransactionIsolation(1);
			System.out.println("Isolation Level:" + cn.getTransactionIsolation());

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		}
	}

	// create or return a database connection (singleton pattern)
	public static DB_connection getDbConnection() {
		if (instance == null) {
			instance = new DB_connection();
			instance.connect_DB();
		}
		return instance;
	}

	// define generic SQL query for table 'benutzer'
	public static String getAllUsers() {
		return "SELECT library.benutzer.PersonID, Benutzername, Vorname, Name FROM library.benutzer, library.person WHERE library.benutzer.PersonID = library.person.PersonID";
	}

	/**
	 * Methode gibt SQL-Query aus, die Benutzer mit dem eingegebenen Passwort
	 * zurückgibt.
	 * 
	 * @param
	 * 
	 * @return SQL-Query
	 * 
	 * @author Michael Gottinger
	 */
	public static String checkAnmeldung(String benutzername, String passwort) {
		return "SELECT * FROM library.benutzer WHERE Benutzername='" + benutzername + "' AND Passwort='" + passwort
				+ "'";
	}

	/**
	 * Methode gibt SQL-Query zum Abruf aller ausgeliehenen Bücher des
	 * angemeldeten Benutzers aus.
	 * 
	 * @param
	 * 
	 * @return SQL-Query
	 * 
	 * @author Michael Gottinger
	 */
	public static String getAllRentBooks(String angemeldeterUser) {
		return "SELECT library.ausleihe.BuchID, Titel, Autor, Status FROM library.exemplar, library.buchtyp, library.ausleihe WHERE library.exemplar.ISBN = library.buchtyp.ISBN AND library.ausleihe.BuchID = library.exemplar.BuchID AND library.exemplar.Status = 'ausgeliehen' AND library.ausleihe.Benutzername ='"
				+ angemeldeterUser + "';";
	}

	/**
	 * Methode gibt SQL-Query aus, um zurückgegebenes Buch zu entfernen
	 * 
	 * @param zurückzugebene
	 * 
	 * @return SQL-Query
	 * 
	 * @author Michael Gottinger
	 */
	public static String buchZurückgegeben(String buchID) {
		return "DELETE FROM library.ausleihe WHERE BUCHID =" + buchID;
	}

	public static String buchInventarisieren(String buchID, String ISBN, String Status) {
		return "INSERT INTO Exemplar VALUES " + buchID + ",'" + ISBN + "','" + Status + "'";
	}

	/**
	 * Methode gibt SQL-Query zur Anzeige aller Bücher aus.
	 * 
	 * @return SQL-Query
	 * 
	 * @author Michael Gottinger
	 */
	public static String getAllBooks() {
		return "SELECT library.exemplar.BuchID, library.exemplar.ISBN, library.exemplar.Status, Titel, Autor FROM library.exemplar, library.buchtyp WHERE library.exemplar.ISBN = library.buchtyp.ISBN ORDER BY BuchID";
	}

	/**
	 * SQL-Query zeigt die Person zur übergebenen PersonID an
	 * 
	 * @return SQL-Query
	 * 
	 * @author Michael Gottinger
	 */
	public static String getPerson(String personID) {
		return "SELECT * FROM library.person WHERE library.person.personID = '" + personID + "';";
	}

	/**
	 * SQL-Query zeigt den Studenten zur übergebenen PersonID an
	 * 
	 * @return SQL-Query
	 * 
	 * @author Michael Gottinger
	 */
	public static String getStudent(String personID) {
		return "SELECT * FROM library.student WHERE library.student.personID = '" + personID + "';";
	}

	/**
	 * SQL-Query zeigt den Professor zur übergebenen PersonID an
	 * 
	 * @return SQL-Query
	 * 
	 * @author Michael Gottinger
	 */
	public static String getProfessor(String personID) {
		return "SELECT * FROM library.professor WHERE library.professor.personID = '" + personID + "';";
	}

	/**
	 * SQL-Query zeigt das Personal zur übergebenen PersonID an
	 * 
	 * @return SQL-Query
	 * 
	 * @author Michael Gottinger
	 */
	public static String getPersonal(String personID) {
		return "SELECT * FROM library.personal WHERE library.personal.personID = '" + personID + "';";
	}

	public static String getAllAvailableBooks() {
		return "SELECT Distinct Titel, Autor, library.exemplar.ISBN, library.exemplar.BuchID FROM library.exemplar, library.buchtyp WHERE library.exemplar.ISBN = library.buchtyp.ISBN AND Status = 'ausleihbar'";
	}

	public static String getUserInfo(String benutzername) {
		return "SELECT Name, Vorname, Benutzername, Passwort, Matrikelnummer, Studiengruppe, Fakultät, Straße, Hausnummer, Postleitzahl, Ort, Art, library.person.PersonID FROM library.person LEFT JOIN library.adresse ON library.adresse.AdressID=library.person.AdressID JOIN library.benutzer ON library.person.PersonID=library.benutzer.PersonID LEFT JOIN library.student ON library.person.PersonID=library.student.PersonID LEFT JOIN library.professor ON library.person.PersonID=library.professor.PersonID LEFT JOIN library.personal ON library.person.PersonID=library.personal.PersonID WHERE library.benutzer.Benutzername='"
				+ benutzername + "';";
	}

	public static String Passwörter() {
		return "SELECT Passwort FROM library.benutzer WHERE library.person.PersonID=library.benutzer.PersonID";
	}

	public static String getAdress() {
		return "SELECT AdressID FROM library.adresse, library.person WHERE library.adresse.AdressID=library.person.AdressID";
	}

	public static String getAnzahlAusleihe(String angemeldeterUser) {
		return "SELECT COUNT(*) FROM library.ausleihe WHERE Benutzername = '" + angemeldeterUser + "';";
	}

	// connect and execute input query and return boolean
	public boolean executequery(String SQLquery) throws SQLException {
		// insert resp. delete resp. modify data base entry
		if (cn == null)
			throw new SQLException("Keine gültige Verbindung zur Datenbank");
		if (SQLquery == null)
			throw new SQLException("Ungültiger SQL-Befehl");
		Statement st = cn.createStatement();
		st.execute(SQLquery);
		return true;
	}

	// connect and execute input query and return result set
	public ResultSet executequery_rs(String SQLquery) throws SQLException { // selectentries
		if (cn == null)
			throw new SQLException("Keine gültige Verbindung zur Datenbank");
		if (SQLquery == null)
			throw new SQLException("Ungültiger SQL-Befehl");
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery(SQLquery);
		return rs;
	}

	/**
	 * Methode gibt einen Wert aus Ergebnistabelle aus
	 * 
	 * @param auszuführende
	 * 
	 * @throws SQLException
	 * 
	 * @return erster Wert aus SQL-Tabelle
	 * 
	 * @author Michael Gottinger
	 */
	public String executequery_Value(String SQLquery, int Value) throws SQLException {
		System.out.println(SQLquery);
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery(SQLquery);
		String values = null;

		if (rs.next())
			values = rs.getString(Value);
		return values;
	}

	// TODO: Befüllt Array mit allen vorhandenen Benutzerdaten
	public String[] executequery_Array(String SQLquery) throws SQLException {
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery(SQLquery);
		String array[] = new String[14];

		while (rs.next()) {
			for (int i = 0; i < 13; i++) {
				array[i] = rs.getString(i + 1);
				// System.out.println(array[i]);
			}
		}
		return array;
	}

	// 2. connect, execute input query and return generatedID of the generated
	// key
	public int executequery_autoKey(String SQLquery, boolean autoKey) throws SQLException {
		if (cn == null)
			throw new SQLException("Keine gültige Verbindung zur Datenbank");
		if (SQLquery == null)
			throw new SQLException("Ungültiger SQL-Befehl");
		Statement st = cn.createStatement();
		st = cn.createStatement();
		if (autoKey)

		{
			ResultSet generatedKeys = null;
			int generatedID = -1;
			st.execute(SQLquery, Statement.RETURN_GENERATED_KEYS);
			generatedKeys = st.getGeneratedKeys();
			if (generatedKeys.next())
				generatedID = generatedKeys.getInt(1);
			else
				throw new SQLException("Es wurden keine erzeugten Keys gefunden" + SQLquery);
			if (generatedID == -1)
				throw new SQLException("Es wurde kein erzeugter Key gefunden" + SQLquery);
			if (generatedKeys != null)
				generatedKeys.close();
			return generatedID;
		} else
			st.execute(SQLquery);
		return 0;
	}

	// save closing SQL query
	public void disconnect() throws SQLException {
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
	}

	// RT
	public void commit() throws SQLException {
		cn.commit();
		disconnect();
	}

	// RT
	public void rollback() throws SQLException {
		cn.rollback();
		disconnect();
	}

	// RT
	public Savepoint createSavepoint(String name) throws SQLException {
		try {
			if (cn.isValid(0)) {
				System.out.println("Savepoint:" + name + "was set");
				return cn.setSavepoint(name);
			}

		} catch (SQLException e) {
			System.err.println("Error: Savepoint could not be set" + e.getMessage());
		}

		return null;
	}

	// RT
	public boolean rollbackChanges(Savepoint savepoint) throws SQLException {
		try {
			if (cn != null) {
				if (cn.isValid(0)) {
					cn.rollback(savepoint);
					System.out.println("Rollback until savepoint" + savepoint.getSavepointName() + "was performed");
				} else
					return true; // keine Verbindung verfügbar
			}
		} catch (SQLException e) {
			System.err.println("Error: Rollback could not be performed" + e.getMessage());
		}
		return true;
	}
}
