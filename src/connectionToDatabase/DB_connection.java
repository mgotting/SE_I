package connectionToDatabase;



import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.Benutzer;
import gui.BenutzerAnlegen;
import gui.Login;

/**
 * @author Michael Gottinger, Sandra Speckmeier
 * Klasse mithilfe derer eine Verbindung zur Datenbank hergestellt wird sowie Abfragen ausgef�hrt werden k�nnen.
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
	
	public static String checkAnmeldung(String benutzername, String passwort){
		return "SELECT Benutzername FROM library.benutzer WHERE Benutzername='"+benutzername+"' AND Passwort='"+passwort+"'";
	}
	
	public static String getAllRentBooks(String angemeldeterUser) {
		return "SELECT library.ausleihe.BuchID, Titel, Autor, Status FROM library.exemplar, library.buchtyp, library.ausleihe WHERE library.exemplar.ISBN = library.buchtyp.ISBN AND library.ausleihe.BuchID = library.exemplar.BuchID AND library.exemplar.Status = 'ausgeliehen' AND library.ausleihe.Benutzername ='"+angemeldeterUser+"';";
	}
	
	public static String buchZur�ckgegeben(String buchID){
		return "DELETE FROM library.ausleihe WHERE BUCHID ="+buchID;
	}
	
	public static String buchInventarisieren(String buchID, String ISBN, String Status )
	{
		return "INSERT INTO Exemplar VALUES " + buchID +",'"+ISBN+"','"+Status+"'";
	}
	
	//TODO getAllBooks alles was in der JTable angezeigt werden soll
//	public static String getAllBooks(){
//		return "SELECT library.exemplar.
//	}
	
	public static String getUserInfo(){
		return "SELECT library.person.PersonID, Name, Vorname, Benutzername, Passwort, Matrikelnummer, Studiengruppe, Fakult�t, Stra�e, Hausnummer, Postleitzahl, Ort, Art, library.adresse.AdressID FROM library.person LEFT JOIN library.adresse ON library.adresse.AdressID=library.person.AdressID JOIN library.benutzer ON library.person.PersonID=library.benutzer.PersonID LEFT JOIN library.student ON library.person.PersonID=library.student.PersonID LEFT JOIN library.professor ON library.person.PersonID=library.professor.PersonID LEFT JOIN library.personal ON library.person.PersonID=library.personal.PersonID";
	}
	
	public static String getAdress(){
		return "SELECT AdressID FROM library.adresse, library.person WHERE library.adresse.AdressID=library.person.AdressID";
	}

	// connect and execute input query and return boolean
	public boolean executequery(String SQLquery) throws SQLException {
		// insert resp. delete resp. modify data base entry
		if (cn == null)
			throw new SQLException("Keine g�ltige Verbindung zur Datenbank");
		if (SQLquery == null)
			throw new SQLException("Ung�ltiger SQL-Befehl");
		Statement st = cn.createStatement();
		st.execute(SQLquery);
		return true;
	}

	// connect and execute input query and return result set
	public ResultSet executequery_rs(String SQLquery) throws SQLException { // selectentries
		if (cn == null)
			throw new SQLException("Keine g�ltige Verbindung zur Datenbank");
		if (SQLquery == null)
			throw new SQLException("Ung�ltiger SQL-Befehl");
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery(SQLquery);
		return rs;
	}

	public String executequery_Value(String SQLquery) throws SQLException{
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery(SQLquery);
		String values = null;
		if (rs.next())
			values = rs.getString(1);
		return values;
	}
	
	// 2. connect, execute input query and return generatedID of the generated key
	public int executequery_autoKey(String SQLquery, boolean autoKey)
			throws SQLException {
		if (cn == null)
			throw new SQLException("Keine g�ltige Verbindung zur Datenbank");
		if (SQLquery == null)
			throw new SQLException("Ung�ltiger SQL-Befehl");
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
				throw new SQLException(
						"Es wurden keine erzeugten Keys gefunden" + SQLquery);
			if (generatedID == -1)
				throw new SQLException("Es wurde kein erzeugter Key gefunden"
						+ SQLquery);
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
}
