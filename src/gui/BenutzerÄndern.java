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
public class Benutzerƒndern {
	JPanel panel;
	JButton ‰ndern, ausw‰hlen;
	public JTableview tableviewUser, tableviewAdress;
	JScrollPane scrollPane;
	private String benutzerArt;
	private String personID;
	private String adressID;
	
	ButtonHandler controlButton;
	
	public final static String ACTION_CHANGE = "ƒNDERN";
	public final static String ACTION_AUSWAHL = "AUSWƒHLEN";
	
	int x_right = 330;
	int x_right2=1070;
	int x_width = 160;
	int y_height = 20;
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
	int x_widthLibrary = 1060;
	int x_BUTTON_width = 110;
	int y_heightLibrary = 100;
	int y_Library = 420;
	
	public JTextField tfBenutzername, tfPasswort, tfName, tfVorname, tfMatrikelnummer, tfStudiengruppe, tfFakult‰t, tfStraﬂe, tfHausnummer, tfPostleitzahl, tfOrt;
	public JLabel labelBenutzername, labelPasswort, labelName, labelVorname, labelMatrikelnummer, labelStudiengruppe, labelFakult‰t, labelStraﬂe, labelHausnummer, labelPostleitzahl, labelOrt;
	
	public Benutzerƒndern(){
		// Erzeugung eines neuen Frames mit dem Titel "BenutzerAnlegen"
		panel = new JPanel();
		// Erzeugung eines Objektes der Klasse JButton
		‰ndern = new JButton("‰ndern");
		ausw‰hlen = new JButton("ausw‰hlen");
		// Erzeugung eines Objektes um ActionEvents zu handeln
		controlButton = new ButtonHandler(this);
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
		tfBenutzername = new JTextField(45);
		tfPasswort = new JTextField(45);
		tfName = new JTextField(45);
		tfVorname = new JTextField(45);
		tfMatrikelnummer = new JTextField(10);
		tfStudiengruppe = new JTextField(4);
		tfFakult‰t = new JTextField(30);
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
	
	public Studiengruppe getStudiengruppe(){
		Studiengruppe studiengruppe = Studiengruppe.valueOf(this.tfStudiengruppe.getText());
		return studiengruppe;
	}
	public void setStudiengruppe(String studiengruppe){
		this.tfStudiengruppe.setText(studiengruppe);
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
	
	public String getBenutzerArt(){
		return benutzerArt;
	}
	
	public void setBenutzerArt(String benutzerArt){
		this.benutzerArt = benutzerArt;
	}
	
	public String getPersonID(){
		return personID;
	}
	public void setPersonID(String personID){
		this.personID = personID;
	}
	
	public String getAdressID(){
		return adressID;
	}
	public void setAdressID(String adressID){
		this.adressID = adressID;
	}
		
	public void launchBenutzerƒndern(JFrame auswahl){
		auswahl.getContentPane().setVisible(false);
		// Wir f¸gen den JButton unserem Panel hinzu:
		‰ndern.setBounds(x_right, y_opt4, x_BUTTON_width, y_height);
		panel.add(‰ndern);
		‰ndern.setActionCommand(ACTION_CHANGE);
		‰ndern.addActionListener(controlButton);
		ausw‰hlen.setBounds(x_right2, y_Library, x_BUTTON_width, y_height);
		panel.add(ausw‰hlen);
		ausw‰hlen.setActionCommand(ACTION_AUSWAHL);
		ausw‰hlen.addActionListener(controlButton);
		

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
		tfName.setBounds(x_center, y_north_textField, x_width, y_height);
		panel.add(tfName);
		tfVorname.setBounds(x_center, y_center, x_width, y_height);
		panel.add(tfVorname);
		tfBenutzername.setBounds(x_center, y_center_textField, x_width, y_height);
		panel.add(tfBenutzername);
		tfPasswort.setBounds(x_center, y_center_textField2, x_width, y_height);
		panel.add(tfPasswort);
		tfMatrikelnummer.setBounds(x_center, y_south, x_width, y_height);
		panel.add(tfMatrikelnummer);
		tfMatrikelnummer.setEditable(false);
		tfStudiengruppe.setBounds(x_center, y_south_label2, x_width, y_height);
		panel.add(tfStudiengruppe);
		tfStudiengruppe.setEditable(false);
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
		
		tfMatrikelnummer.setEditable(true);
    	tfStudiengruppe.setEditable(true);
    	tfFakult‰t.setEditable(true);
		

		if(tableviewUser==null){
			tableviewUser = new JTableview(DB_connection.getUserInfo());
			scrollPane = new JScrollPane(tableviewUser.getSQLTable());
			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}	
		
		auswahl.setTitle("Benutzer ‰ndern");
		auswahl.setContentPane(panel);
	}

}
