/**
 * 
 */
package gui;

import javax.swing.*;

import connectionToDatabase.*;
import application.*;

/**
 * @author Gotti
 *
 */
public class BenutzerAnlegen {
	JPanel panel;
	JButton anlegen;
	JComboBox benutzer;
	public JTableview tableviewUser;
	JScrollPane scrollPane;
	
	ButtonHandler controlButton;
	ComboBoxHandler controlComboBox;
	
	public final static String ACTION_CREATE = "ANLEGEN";
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	int x_anlegen_width = 120;
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
	int x_widthLibrary = 450;
	int y_heightLibrary = 100;
	int y_Library = 420;
	
	public JTextField benutzername, passwort, name, vorname, matrikelnummer, studiengruppe, fakult‰t, straﬂe, hausnummer, postleitzahl, ort;
	public JLabel labelBenutzername, labelPasswort, labelName, labelVorname, labelMatrikelnummer, labelStudiengruppe, labelFakult‰t, labelStraﬂe, labelHausnummer, labelPostleitzahl, labelOrt;
	private static String[] benutzerliste = { "Benutzer w‰hlen", "Student", "Professor", "Personal" };
	private String benutzerArt;
	
	public BenutzerAnlegen(){
		panel = new JPanel();
		// Erzeugung eines Objektes der Klasse JButton
		anlegen = new JButton("anlegen");
		// Erzeugung eines Objektes um ActionEvents zu handeln
		controlButton = new ButtonHandler(this);
		// Erzeugung eines Objektes der Klasse JComboBox
		benutzer = new JComboBox(benutzerliste);
		// Erzeugung eines Objekt um
		controlComboBox = new ComboBoxHandler(this);
		benutzer.addItemListener(controlComboBox);
		panel.setLayout(null);
		
		//Erzeugung der JLabels
		labelBenutzername = new JLabel("Benutzername: ", JLabel.LEFT);
		labelPasswort = new JLabel("Passwort: ", JLabel.LEFT);
		labelName = new JLabel("Name:", JLabel.LEFT);
		labelVorname = new JLabel("Vorname:", JLabel.LEFT);
		labelMatrikelnummer = new JLabel("Matrikelnummer:", JLabel.LEFT);
		labelStudiengruppe = new JLabel("Studiengruppe:", JLabel.LEFT);
		labelFakult‰t = new JLabel("Fakult‰t:", JLabel.LEFT);
		labelStraﬂe = new JLabel("Straﬂe:", JLabel.LEFT);
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
		fakult‰t = new JTextField(30);
		straﬂe = new JTextField(80);
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
	
	public String getBenutzerArt(){
		return benutzerArt;
	}
	
	public void setBenutzerArt(String benutzerArt){
		this.benutzerArt = benutzerArt;
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
	
	public String getFakult‰t(){
		String fakult‰t = this.fakult‰t.getText();
		return fakult‰t;
	}
	public void setFakult‰t(String fakult‰t){
		this.fakult‰t.setText(fakult‰t);
	}
	
	public String getStraﬂe(){
		String straﬂe = this.straﬂe.getText();
		return straﬂe;
	}
	
	public void setStraﬂe(String straﬂe){
		this.straﬂe.setText(straﬂe);
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
		
	public void LaunchBenutzerAnlegen(JFrame auswahl){	
		auswahl.getContentPane().setVisible(false);
		// Wir f¸gen den JButton unserem Panel hinzu:
		anlegen.setBounds(x_right, y_opt4, x_anlegen_width, y_height);
		panel.add(anlegen);
		anlegen.setActionCommand(ACTION_CREATE);
		anlegen.addActionListener(controlButton);

		// Wir f¸gen die JLabel unserem Panel hinzu:
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
		labelFakult‰t.setBounds(x_left, y_south_label3, x_width, y_height);
		panel.add(labelFakult‰t);

		// optional
		labelStraﬂe.setBounds(x_left, y_opt1, x_width, y_height);
		panel.add(labelStraﬂe);
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
		fakult‰t.setBounds(x_center, y_south_label3, x_width, y_height);
		panel.add(fakult‰t);
		fakult‰t.setEditable(false);

		// optional
		straﬂe.setBounds(x_center, y_opt1, x_width, y_height);
		panel.add(straﬂe);
		hausnummer.setBounds(x_center, y_opt2, x_width, y_height);
		panel.add(hausnummer);
		postleitzahl.setBounds(x_center, y_opt3, x_width, y_height);
		panel.add(postleitzahl);
		ort.setBounds(x_center, y_opt4, x_width, y_height);
		panel.add(ort);

		// JComboBoxes:
		benutzer.setBounds(x_left, y_MENU_north, x_width, y_height);
		panel.add(benutzer);
		
		if(tableviewUser==null){
			tableviewUser = new JTableview(DB_connection.getAllUsers());
			scrollPane = new JScrollPane(tableviewUser.getSQLTable());
			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}	
		auswahl.setTitle("Benutzer/Person integriert Anlegen");
		auswahl.setContentPane(panel);
	}
}
