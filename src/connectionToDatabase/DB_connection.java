package connectionToDatabase;



import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Gotti
 * Klasse mithilfe derer eine Verbindung zur Datenbank hergestellt wird sowie Abfragen ausgeführt werden können.
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
		return "SELECT * FROM benutzer";
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
	public ResultSet executequery_rs(String SQLquery) throws SQLException { // select
																			// entries
		if (cn == null)
			throw new SQLException("Keine gültige Verbindung zur Datenbank");
		if (SQLquery == null)
			throw new SQLException("Ungültiger SQL-Befehl");
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery(SQLquery);
		return rs;
	}

	// 2. connect, execute input query and return generatedID of the generated
	// key
	public int executequery_autoKey(String SQLquery, boolean autoKey)
			throws SQLException {
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
