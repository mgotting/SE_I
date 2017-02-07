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
	JComboBox benutzer, studiengruppe;
	public JTableview tableviewUser;
	JScrollPane scrollPane;
	
	ButtonHandler controlButton;
	ComboBoxHandler controlComboBox;
	
	public final static String ACTION_CREATE = "ANLEGEN";
	
	int x_left = 370;
	int x_right = 700;
	int x_center = 500;
	int y_north = 40;
	int y_north2 = 100;
	int y_center = 120;
	int y_center2 = 140;
	int y_center3 = 160;
	int y_south = 200;
	int y_south_label2 = 220;
	int y_south_label3 = 260;
	int y_opt1 = 300;
	int y_opt2 = 320;
	int y_opt3 = 340;
	int y_opt4 = 360;	
	int x_width = 160;
	int y_height = 20;
	int x_widthLibrary = 450;
	int y_heightLibrary = 100;
	int y_Library = 420;
	int x_anlegen_width = 120;
	
	public JTextField tfBenutzername, tfPasswort, tfName, tfVorname, tfMatrikelnummer, tfFakult‰t, tfStraﬂe, tfHausnummer, tfPostleitzahl, tfOrt;
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
		studiengruppe = new JComboBox(Studiengruppe.values());
		studiengruppe.hide();
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
		labelMatrikelnummer.hide();
		labelStudiengruppe = new JLabel("Studiengruppe:", JLabel.LEFT);
		labelStudiengruppe.hide();
		labelFakult‰t = new JLabel("Fakult‰t:", JLabel.LEFT);
		labelFakult‰t.hide();
		labelStraﬂe = new JLabel("Straﬂe:", JLabel.LEFT);
		labelHausnummer = new JLabel("Hausnummer:", JLabel.LEFT);
		labelPostleitzahl = new JLabel("Postleitzahl:", JLabel.LEFT);
		labelOrt = new JLabel("Ort:", JLabel.LEFT);
		
		//Erzeugung der JTextFields
		tfBenutzername = new JTextField(45);
		tfPasswort = new JTextField(45);
		tfName = new JTextField(45);
		tfVorname = new JTextField(45);
		tfMatrikelnummer = new JTextField(10);
		tfMatrikelnummer.hide();
		tfFakult‰t = new JTextField(30);
		tfFakult‰t.hide();
		tfStraﬂe = new JTextField(80);
		tfHausnummer = new JTextField(4);
		tfPostleitzahl = new JTextField(5);
		tfOrt = new JTextField(30);
	}
	
	
	public String getBenutzername(){
		String benutzername = this.tfBenutzername.getText();
		return benutzername;
	}
	
	public void setBenutzername(String benutzername){
		this.tfBenutzername.setText(benutzername);
	}
	
	public String getBenutzerArt() throws NullPointerException{
		if (benutzerArt==null){
		throw new NullPointerException("Keine Person ausgew‰hlt!");
		} else {
		return benutzerArt;
		}
	}
	
	public void setBenutzerArt(String benutzerArt){
		if (benutzerArt==null){
		throw new NullPointerException("Keine Person ausgew‰hlt!");
		} else {
		this.benutzerArt = benutzerArt;
		}
	}
	
	public String getPasswort(){
		String passwort = this.tfPasswort.getText();
		return passwort;
	}
	
	public void setPasswort(String passwort){
		this.tfPasswort.setText(passwort);
	}
	
	public String getName(){
		String name = this.tfName.getText();
		return name;
	}
	
	public void setName(String nachname){
		this.tfName.setText(nachname);
	}
	
	public String getVorname(){
		String vorname = this.tfVorname.getText();
		return vorname;
	}
	public void setVorname(String vorname){
		this.tfVorname.setText(vorname);
	}
	
	public int getMatrikelnummer(){
		int matrikelnummer = Integer.parseInt(this.tfMatrikelnummer.getText());
		return matrikelnummer;
	}
	public void setMatrikelnummer(String matrikelnummer){
		this.tfMatrikelnummer.setText(matrikelnummer);
	}
	
	public Studiengruppe getStudiengruppe() throws NullPointerException{
		return (Studiengruppe) this.studiengruppe.getSelectedItem();
	}
	
	public String getFakult‰t(){
		String fakult‰t = this.tfFakult‰t.getText();
		return fakult‰t;
	}
	public void setFakult‰t(String fakult‰t){
		this.tfFakult‰t.setText(fakult‰t);
	}
	
	public String getStraﬂe(){
		String straﬂe = this.tfStraﬂe.getText();
		return straﬂe;
	}
	
	public void setStraﬂe(String straﬂe){
		this.tfStraﬂe.setText(straﬂe);
	}
	
	public String getHausnummer(){
		String hausnummer = this.tfHausnummer.getText();
		return hausnummer;
	}
	
	public void setHausnummer(String hausnummer){
		this.tfHausnummer.setText(hausnummer);
	}
	
	public int getPLZ(){
		int postleitzahl = Integer.parseInt(this.tfPostleitzahl.getText());
		return postleitzahl;
	}
	
	public void setPLZ(String postleitzahl){
		this.tfPostleitzahl.setText(postleitzahl);
	}
	
	public String getOrt(){
		 String ort = this.tfOrt.getText();
		return ort;
	}
	
	public void setOrt(String ort){
		this.tfOrt.setText(ort);
	}
		
	public void LaunchBenutzerAnlegen(JFrame auswahl){	
		auswahl.getContentPane().setVisible(false);
		// Wir f¸gen den JButton unserem Panel hinzu:
		anlegen.setBounds(x_right, y_opt4, x_anlegen_width, y_height);
		panel.add(anlegen);
		anlegen.setActionCommand(ACTION_CREATE);
		anlegen.addActionListener(controlButton);

		// Wir f¸gen die JLabel unserem Panel hinzu:
		labelName.setBounds(x_left, y_north2, x_width, y_height);
		panel.add(labelName);
		labelVorname.setBounds(x_left, y_center, x_width, y_height);
		panel.add(labelVorname);
		labelBenutzername.setBounds(x_left, y_center2, x_width, y_height);
		panel.add(labelBenutzername);
		labelPasswort.setBounds(x_left, y_center3, x_width, y_height);
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
		tfName.setBounds(x_center, y_north2, x_width, y_height);
		panel.add(tfName);
		tfVorname.setBounds(x_center, y_center, x_width, y_height);
		panel.add(tfVorname);
		tfBenutzername.setBounds(x_center, y_center2, x_width, y_height);
		panel.add(tfBenutzername);
		tfPasswort.setBounds(x_center, y_center3, x_width, y_height);
		panel.add(tfPasswort);
		tfMatrikelnummer.setBounds(x_center, y_south, x_width, y_height);
		panel.add(tfMatrikelnummer);
		tfMatrikelnummer.setEditable(false);
		tfFakult‰t.setBounds(x_center, y_south_label3, x_width, y_height);
		panel.add(tfFakult‰t);
		tfFakult‰t.setEditable(false);

		// optional
		tfStraﬂe.setBounds(x_center, y_opt1, x_width, y_height);
		panel.add(tfStraﬂe);
		tfHausnummer.setBounds(x_center, y_opt2, x_width, y_height);
		panel.add(tfHausnummer);
		tfPostleitzahl.setBounds(x_center, y_opt3, x_width, y_height);
		panel.add(tfPostleitzahl);
		tfOrt.setBounds(x_center, y_opt4, x_width, y_height);
		panel.add(tfOrt);

		// JComboBoxes:
		benutzer.setBounds(x_center, y_north, x_width, y_height);
		panel.add(benutzer);
		studiengruppe.setBounds(x_center, y_south_label2, x_width, y_height);
		panel.add(studiengruppe);
		
		if(tableviewUser==null){
			tableviewUser = new JTableview(DB_connection.getAllUsers());
			scrollPane = new JScrollPane(tableviewUser.getSQLTable());
			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}	
		auswahl.setTitle("Benutzer anlegen");
		auswahl.setContentPane(panel);
	}
}
