/**
 * 
 */
package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import application.ButtonHandler;
import application.Studiengruppe;
import connectionToDatabase.DB_connection;
import connectionToDatabase.JTableview;

/**
 * @author Sandra
 *
 */
public class BenutzerÄndern {
	JFrame änderung;
	JPanel panel;
	JMenuBar bar;
	JMenu menu;
	JMenuItem benutzerÄndern, benutzerAnlegen;
	JButton ändern, auswählen;
	public JTableview tableviewUser;
	JScrollPane scrollPane;
	
	ButtonHandler controlButton;
//	ComboBoxHandler controlComboBox;
	
	public final static String ACTION_CHANGE = "ÄNDERN";
	public final static String ACTION_AUSWAHL = "AUSWÄHLEN";
	public final static String ACTION_BENUTZER_ANLEGEN = "BENUTZER_ANLEGEN";
	public final static String ACTION_BENUTZER_ÄNDERN = "BENUTZER_ÄNDERN";
	
	public MenuHandler controlMenu;
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	int y_anlegen_center = 200;
	int x_left = 10;
	int y_north_label = 100;
	int y_center = 120;
	int y_center_label2 = 140;
	int y_center_label3 = 160;
	int y_south = 200;
	int y_south_label2 = 220;
	int y_south_label3 = 260;
	int y_opt1 = 300;
	int y_opt2 = 320;
	int y_opt3 = 340;
	int y_opt4 = 360;
	int x_center = 140;
	int y_north_textField = 100;
	int y_center_textField = 140;
	int y_center_textField2 = 160;
	int y_MENU_north = 10;
	int x_widthLibrary = 300;
	int x_BUTTON_width = 120;
	int y_heightLibrary = 100;
	int y_Library = 420;
	
	public JTextField benutzername, passwort, name, vorname, matrikelnummer, studiengruppe, fakultät, straße, hausnummer, postleitzahl, ort;
	public JLabel labelBenutzername, labelPasswort, labelName, labelVorname, labelMatrikelnummer, labelStudiengruppe, labelFakultät, labelStraße, labelHausnummer, labelPostleitzahl, labelOrt;
	
	public BenutzerÄndern(){
		// Erzeugung eines neuen Frames mit dem Titel "BenutzerAnlegen"
		änderung = new JFrame("Benutzer ändern");
		panel = new JPanel();
		// Erstellen einer Menüleiste
		bar = new JMenuBar();
		// Erzeugung eines Objektes der Klasse JMenu
		menu = new JMenu("Menu");
		// Erzeugung eines Objektes der Klasse JMenuItem
		benutzerÄndern = new JMenuItem("Benutzer ändern");
		// Erzeugung eines Objektes der Klasse JButton
		ändern = new JButton("ändern");
		auswählen = new JButton("auswählen");
		// Erzeugung eines Objektes um ActionEvents zu handeln
		controlButton = new ButtonHandler(this);
		
		änderung.setContentPane(panel);
		panel.setLayout(null);
		
		//Erzeugung der JLabels
		labelBenutzername = new JLabel("Benutzername: ", JLabel.LEFT);
		labelPasswort = new JLabel("Passwort: ", JLabel.LEFT);
		labelName = new JLabel("Name:", JLabel.LEFT);
		labelVorname = new JLabel("Vorname:", JLabel.LEFT);
		labelMatrikelnummer = new JLabel("Matrikelnummer:", JLabel.LEFT);
		labelStudiengruppe = new JLabel("Studiengruppe:", JLabel.LEFT);
		labelFakultät = new JLabel("Fakultät:", JLabel.LEFT);
		labelStraße = new JLabel("Straße:", JLabel.LEFT);
		labelHausnummer = new JLabel("Hausnummer:", JLabel.LEFT);
		labelPostleitzahl = new JLabel("Postleitzahl:", JLabel.LEFT);
		labelOrt = new JLabel("Ort:", JLabel.LEFT);
		
		//Erzeugung der JTextFields
		benutzername = new JTextField(45);
		passwort = new JTextField(45);
		name = new JTextField(45);
		vorname = new JTextField(45);
		matrikelnummer = new JTextField(10);
		studiengruppe = new JTextField(4);
		fakultät = new JTextField(30);
		straße = new JTextField(80);
		hausnummer = new JTextField(4);
		postleitzahl = new JTextField(5);
		ort = new JTextField(30);
	}
	
	
	public String getBenutzername(){
		String benutzername = this.benutzername.getText();
		return benutzername;
	}
	
	public void setBenutzername(String benutzername){
		this.benutzername.setText(benutzername);
	}
	
	public String getPasswort(){
		String passwort = this.passwort.getText();
		return passwort;
	}
	
	public void setPasswort(String passwort){
		this.passwort.setText(passwort);
	}
	
	public String getName(){
		String name = this.name.getText();
		return name;
	}
	
	public void setName(String nachname){
		this.name.setText(nachname);
	}
	
	public String getVorname(){
		String vorname = this.vorname.getText();
		return vorname;
	}
	public void setVorname(String vorname){
		this.vorname.setText(vorname);
	}
	
	public int getMatrikelnummer(){
		int matrikelnummer = Integer.parseInt(this.matrikelnummer.getText());
		return matrikelnummer;
	}
	public void setMatrikelnummer(String matrikelnummer){
		this.matrikelnummer.setText(matrikelnummer);
	}
	
	public Studiengruppe getStudiengruppe(){
		Studiengruppe studiengruppe = Studiengruppe.valueOf(this.studiengruppe.getText());
		return studiengruppe;
	}
	public void setStudiengruppe(String studiengruppe){
		this.studiengruppe.setText(studiengruppe);
	}
	
	public String getFakultät(){
		String fakultät = this.fakultät.getText();
		return fakultät;
	}
	public void setFakultät(String fakultät){
		this.fakultät.setText(fakultät);
	}
	
	public String getStraße(){
		String straße = this.straße.getText();
		return straße;
	}
	
	public void setStraße(String straße){
		this.straße.setText(straße);
	}
	
	public String getHausnummer(){
		String hausnummer = this.hausnummer.getText();
		return hausnummer;
	}
	
	public void setHausnummer(String hausnummer){
		this.hausnummer.setText(hausnummer);
	}
	
	public int getPLZ(){
		int postleitzahl = Integer.parseInt(this.postleitzahl.getText());
		return postleitzahl;
	}
	
	public void setPLZ(String postleitzahl){
		this.postleitzahl.setText(postleitzahl);
	}
	
	public String getOrt(){
		 String ort = this.ort.getText();
		return ort;
	}
	
	public void setOrt(String ort){
		this.ort.setText(ort);
	}
		
	public void launchBenutzerÄndern(){
		änderung.setBounds(x_right, y_north, x_width, y_height);
		änderung.setJMenuBar(bar);
		// Menü wird der Menüleiste hinzugefügt
		bar.add(menu);
		// Wir fügen das JMenuItem unserem JMenu hinzu
		menu.add(benutzerÄndern);
		
		// Wir fügen den JButton unserem Panel hinzu:
		ändern.setBounds(x_right, y_opt4, x_BUTTON_width, y_height);
		panel.add(ändern);
		ändern.setActionCommand(ACTION_CHANGE);
		ändern.addActionListener(controlButton);
		auswählen.setBounds(x_right, y_Library, x_BUTTON_width, y_height);
		panel.add(auswählen);
		auswählen.setActionCommand(ACTION_AUSWAHL);
		auswählen.addActionListener(controlButton);
		

		// Wir fügen die JLabel unserem Panel hinzu:
		labelName.setBounds(x_left, y_north_label, x_width, y_height);
		panel.add(labelName);
		labelVorname.setBounds(x_left, y_center, x_width, y_height);
		panel.add(labelVorname);
		labelBenutzername.setBounds(x_left, y_center_label2, x_width, y_height);
		panel.add(labelBenutzername);
		labelPasswort.setBounds(x_left, y_center_label3, x_width, y_height);
		panel.add(labelPasswort);
		labelMatrikelnummer.setBounds(x_left, y_south, x_width, y_height);
		panel.add(labelMatrikelnummer);
		labelStudiengruppe.setBounds(x_left, y_south_label2, x_width, y_height);
		panel.add(labelStudiengruppe);
		labelFakultät.setBounds(x_left, y_south_label3, x_width, y_height);
		panel.add(labelFakultät);

		// optional
		labelStraße.setBounds(x_left, y_opt1, x_width, y_height);
		panel.add(labelStraße);
		labelHausnummer.setBounds(x_left, y_opt2, x_width, y_height);
		panel.add(labelHausnummer);
		labelPostleitzahl.setBounds(x_left, y_opt3, x_width, y_height);
		panel.add(labelPostleitzahl);
		labelOrt.setBounds(x_left, y_opt4, x_width, y_height);
		panel.add(labelOrt);

		// JTextField:
		name.setBounds(x_center, y_north_textField, x_width, y_height);
		panel.add(name);
		vorname.setBounds(x_center, y_center, x_width, y_height);
		panel.add(vorname);
		benutzername.setBounds(x_center, y_center_textField, x_width, y_height);
		panel.add(benutzername);
		passwort.setBounds(x_center, y_center_textField2, x_width, y_height);
		panel.add(passwort);
		matrikelnummer.setBounds(x_center, y_south, x_width, y_height);
		panel.add(matrikelnummer);
		matrikelnummer.setEditable(false);
		studiengruppe.setBounds(x_center, y_south_label2, x_width, y_height);
		panel.add(studiengruppe);
		studiengruppe.setEditable(false);
		fakultät.setBounds(x_center, y_south_label3, x_width, y_height);
		panel.add(fakultät);
		fakultät.setEditable(false);

		// optional
		straße.setBounds(x_center, y_opt1, x_width, y_height);
		panel.add(straße);
		hausnummer.setBounds(x_center, y_opt2, x_width, y_height);
		panel.add(hausnummer);
		postleitzahl.setBounds(x_center, y_opt3, x_width, y_height);
		panel.add(postleitzahl);
		ort.setBounds(x_center, y_opt4, x_width, y_height);
		panel.add(ort);
	
        // Wir setzen die Breite und die Höhe unseres Fensters auf 500 Pixel */ 
		änderung.setSize(520, 600);
		änderung.setLocation(100, 100);
		// Beim schließen des GUI-Fensters wird JFrame geschlossen
		änderung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Wir lassen unseren Frame anzeigen
		änderung.setVisible(true);
		
		if(tableviewUser==null){
			tableviewUser = new JTableview(DB_connection.getAllUsers());
			scrollPane = new JScrollPane(tableviewUser.getSQLTable());
			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}	
	}

}
