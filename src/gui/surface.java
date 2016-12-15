package gui;

import javax.swing.*;

import application.ButtonHandler;
import application.Studiengruppe;
import connectionToDatabase.DB_connection;
import connectionToDatabase.JTableview;

import java.awt.*;

/**
 * @author Sandra
 *
 */

// Erstellt die Oberfläche der GUI

public class surface /* extends JFrame */ {
	public JFrame login, rent, back, status, inventory, create, alter; // warum public?
	private JPanel contentpane, contentpane2, contentpane3, contentpane4, contentpane5, contentpane6, contentpane7;
	private JButton ok, ausleihen, zurückgeben, ändern, löschen;
	public JTextField username, pw, titel, autor, isbn, name, vorname, matnr, sg, fak, str, hausnr, plz, ort; // warum public?
	public JLabel benutzername, passwort, bTitel, bAutor, bISBN, pName, pVorname, matrikelnummer, studiengruppe,
			fakultaet, strasse, hausnummer, postleitzahl, pOrt;

	private static String[] benutzerliste = { "Benutzer wählen", "Student", "Professor", "Personal" };
	JComboBox benutzerAuswahl = new JComboBox(benutzerliste);
	private static String[] menueliste = { "Menü:", "Ausleihe", "Rückgabe", "Bücher anzeigen",
			"Bücher inventarisieren", "Benutzer anlegen/anzeigen", "Person ändern", "Logout" };
	JComboBox menueAuswahl = new JComboBox(menueliste);
	private static String[] statusliste = { "Status wählen", "ausleihbar", "ausgeliehen", "nicht ausleihbar" };
	JComboBox statusAuswahl = new JComboBox(statusliste);
	
	public ButtonHandler control;
	public JTableview tableview;
	public JScrollPane scrollPane;

	//Action Commands for Action Listener
	public final static String ACTION_CREATE_STUD = "CREATE_STUDENT";
	public final static String ACTION_CREATE_PROF = "CREATE_PROFESSOR";
	public final static String ACTION_CREATE_PERS = "CREATE_PERSONAL";
	public final static String ACTION_NEXT_AUSLEIHE = "NEXT_AUSLEIHE";
	public final static String ACTION_NEXT_RÜCKGABE = "NEXT_RÜCKGABE";
	public final static String ACTION_NEXT_ANZEIGE = "NEXT_ANZEIGE";
	public final static String ACTION_NEXT_INVENTARISIERUNG = "NEXT_INVENTARISIERUNG";
	public final static String ACTION_NEXT_ANLEGEN = "NEXT_ANLEGEN";
	public final static String ACTION_NEXT_ÄNDERN = "NEXT_ÄNDERN";
	public final static String ACTION_NEXT_LOGOUT = "NEXT_LOGOUT";
	//TODO ACTION_NEXT  
	
	// Layout LOGIN-GUI:
	//int ylogin_north = 20;
	//int ylogin_center = 70;
	int yloginUser_center = 120;
	int yloginUser2_center = 140;
	int yloginPW_center = 180;
	int yloginPW2_center = 200;

	// Layout allgemein:
	int xOK_width = 70;
	int xButton_width = 90;
	int yMENU_north = 10;

	int y_north = 60;
	int y_north2 = 80;
	int y_north3 = 100;
	int y_center = 120;
	int y_center2 = 140;
	int y_center3 = 160;
	int y_south = 180;
	int y_south2 = 200;
	int y_south3 = 220;
	int y_south4 = 240;
	int y_south5 = 250;
	int y_south6 = 260;
	int y_opt1 = 300;
	int y_opt2 = 320;
	int y_opt3 = 340;
	int y_opt4 = 360;
	int y_opt5 = 390;
	int y_Library = 420;
	int y_bottom = 350;
	int x_left = 10;
	int x_center = 140;
	int x_centerLibrary = 90;
	int x_right = 330;
	int x_width = 160;
	int y_height = 20;
	int x_widthLibrary = 260;
	int y_heightLibrary = 100;
	

	public String getUsername(){
		String name = username.getText();
		return name;
	}
	public void setUsername(String name){
		this.username.setText(name);
	}
	
	public String getPasswort(){
		String passwort = pw.getText();
		return passwort;
	}
	public void setPasswort(String passwort){
		this.pw.setText(passwort);
	}
	
	public String getTitel(){
		String buchtitel = titel.getText();
		return buchtitel;
	}
	public void setTitel(String buchtitel){
		this.titel.setText(buchtitel);
	}
	
	public String getAutor(){
		String buchautor = autor.getText();
		return buchautor;
	}
	public void setAutor(String buchautor){
		this.autor.setText(buchautor);
	}
	
	public String getIsbn(){
		String buchisbn = isbn.getText();
		return buchisbn;
	}
	public void setIsbn(String buchisbn){
		this.isbn.setText(buchisbn);
	}
	
	public String getName(){
		String nachname = name.getText();
		return nachname;
	}
	public void setName(String nachname){
		this.name.setText(nachname);
	}
	
	public String getVorname(){
		String firstname = vorname.getText();
		return firstname;
	}
	public void setVorname(String firstname){
		this.vorname.setText(firstname);
	}
	
	public int getMatrikelnummer(){
		int matrikelnr = Integer.parseInt(matnr.getText());
		return matrikelnr;
	}
	public void setMatrikelnummer(String matrikelnr){
		this.matnr.setText(matrikelnr);
	}
	
	public Studiengruppe getStudiengruppe(){
		Studiengruppe studygroup = Studiengruppe.valueOf(sg.getText());
		return studygroup;
	}
	public void setStudiengruppe(String studygroup){
		this.sg.setText(studygroup);
	}
	
	public String getFakultät(){
		String fakultät = fak.getText();
		return fakultät;
	}
	public void setFakultät(String fakultät){
		this.fak.setText(fakultät);
	}
	
	public String getStraße(){
		String straße = str.getText();
		return straße;
	}
	public void setStraße(String straße){
		this.str.setText(straße);
	}
	
	public String getHausnummer(){
		String hausnummer = hausnr.getText();
		return hausnummer;
	}
	public void setHausnummer(String hausnummer){
		this.hausnr.setText(hausnummer);
	}
	
	public String getPLZ(){
		String postleitzahl = plz.getText();
		return postleitzahl;
	}
	public void setPLZ(String postleitzahl){
		this.plz.setText(postleitzahl);
	}
	
	public String getOrt(){
		String place = ort.getText();
		return place;
	}
	public void setOrt(String place){
		this.plz.setText(place);
	}
	
	public surface() {
		// LOGIN-GUI
		control = new ButtonHandler(this);
		login = new JFrame("Login");
		contentpane = new JPanel();
		login.setContentPane(contentpane);
		contentpane.setLayout(null);
		ok = new JButton("OK");
		benutzername = new JLabel("Benutzername:", JLabel.LEFT);
		passwort = new JLabel("Passwort:", JLabel.LEFT);
		username = new JTextField(45);
		pw = new JTextField(10);

		// AUSLEIH-GUI
		rent = new JFrame("Ausleihe");
		contentpane2 = new JPanel();
		rent.setContentPane(contentpane2);
		contentpane2.setLayout(null);
		ausleihen = new JButton("ausleihen");
		bTitel = new JLabel("Buchtitel:", JLabel.LEFT);
		bAutor = new JLabel("Autor:", JLabel.LEFT);
		bISBN = new JLabel("ISBN-Nummer:", JLabel.LEFT);
		titel = new JTextField(50);
		autor = new JTextField(45);
		isbn = new JTextField(20);

		menueAuswahl.setBackground(Color.white);
		benutzerAuswahl.setBackground(Color.white);

		// RÜCKGABE-GUI
		back = new JFrame("Rückgabe");
		contentpane3 = new JPanel();
		back.setContentPane(contentpane3);
		contentpane3.setLayout(null);
		zurückgeben = new JButton("zurückgeben");

		// STATUS-GUI
		status = new JFrame("Buchstatus");
		contentpane4 = new JPanel();
		status.setContentPane(contentpane4);
		contentpane4.setLayout(null);
		statusAuswahl.setBackground(Color.white);

		// INVENT-GUI
		inventory = new JFrame("Buch inventarisieren");
		contentpane5 = new JPanel();
		inventory.setContentPane(contentpane5);
		contentpane5.setLayout(null);

		// CREATE-GUI
		create = new JFrame("Benutzer anlegen");
		contentpane6 = new JPanel();
		create.setContentPane(contentpane6);
		contentpane6.setLayout(null);
		pName = new JLabel("Name:", JLabel.LEFT);
		pVorname = new JLabel("Vorname:", JLabel.LEFT);
		matrikelnummer = new JLabel("Matrikelnummer:", JLabel.LEFT);
		studiengruppe = new JLabel("Studiengruppe:", JLabel.LEFT);
		fakultaet = new JLabel("Fakultät:", JLabel.LEFT);
		strasse = new JLabel("Straße:", JLabel.LEFT);
		hausnummer = new JLabel("Hausnummer:", JLabel.LEFT);
		postleitzahl = new JLabel("Postleitzahl:", JLabel.LEFT);
		pOrt = new JLabel("Ort:", JLabel.LEFT);
		name = new JTextField(45);
		vorname = new JTextField(45);
		matnr = new JTextField(10);
		sg = new JTextField(4);
		fak = new JTextField(30);
		str = new JTextField(80);
		hausnr = new JTextField(4);
		plz = new JTextField(5);
		ort = new JTextField(30);
		
		 //ALTER-GUI 
		alter = new JFrame ("Person ändern"); 
		contentpane7 = new JPanel(); 
		alter.setContentPane(contentpane7);
		contentpane7.setLayout(null); 
		ändern = new JButton ("ändern");
		löschen = new JButton ("löschen");
	}

	public void launchLogin() {
		login.setBounds(x_right, y_north, x_width, y_height);

		// JButtons:
		ok.setBounds(x_right, y_center2, xOK_width, y_height);
		contentpane.add(ok);

		// JLables:
		benutzername.setBounds(x_center, y_north, x_width, y_height);
		contentpane.add(benutzername);
		passwort.setBounds(x_center, y_center, x_width, y_height);
		contentpane.add(passwort);

		// JTextFields:
		username.setBounds(x_center, y_north2, x_width, y_height);
		contentpane.add(username);
		pw.setBounds(x_center, y_center2, x_width, y_height);
		contentpane.add(pw);

		login.setSize(450, 500);
		login.setLocation(100, 100);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		login.setVisible(true);
		
		if(tableview==null){
			tableview = new JTableview(DB_connection.getAllUsers());
			scrollPane = new JScrollPane(tableview.getSQLTable());
			scrollPane.setBounds(x_centerLibrary, y_south2, x_widthLibrary, y_heightLibrary);
			contentpane.add(scrollPane);
		}
	}

	public void launchRent() {
		rent.setBounds(x_right, y_north, x_width, y_height);

		// JButton:
		ok.setBounds(x_right, yloginPW2_center, xOK_width, y_height);
		contentpane2.add(ok);
		ausleihen.setBounds(x_right, y_bottom, xButton_width, y_height);
		contentpane2.add(ausleihen);

		// JLabels:
		bTitel.setBounds(x_center, y_north, x_width, y_height);
		contentpane2.add(bTitel);
		bAutor.setBounds(x_center, y_center, x_width, y_height);
		contentpane2.add(bAutor);
		bISBN.setBounds(x_center, y_south, x_width, y_height);
		contentpane2.add(bISBN);

		// JTextField:
		titel.setBounds(x_center, y_north2, x_width, y_height);
		contentpane2.add(titel);
		autor.setBounds(x_center, y_center2, x_width, y_height);
		contentpane2.add(autor);
		isbn.setBounds(x_center, y_south2, x_width, y_height);
		contentpane2.add(isbn);

		// JComboBox
		menueAuswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane2.add(menueAuswahl);

		rent.setSize(450, 500);
		rent.setLocation(100, 100);
		rent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		rent.setVisible(true);
	}

	public void launchReturn() {
		back.setBounds(x_right, y_north, x_width, y_height);

		// JButtons:
		zurückgeben.setBounds(x_center, y_bottom, x_width, y_height);
		contentpane3.add(zurückgeben);

		// JComboBox
		menueAuswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane3.add(menueAuswahl);

		back.setSize(450, 500);
		back.setLocation(100, 100);
		back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		back.setVisible(true);
	}

	public void launchStatus() {
		status.setBounds(x_right, y_north, x_width, y_height);

		// JComboBox
		menueAuswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane4.add(menueAuswahl);
		statusAuswahl.setBounds(x_center, y_north2, x_width, y_height);
		contentpane4.add(statusAuswahl);

		status.setSize(450, 500);
		status.setLocation(100, 100);
		status.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		status.setVisible(true);
	}

	public void launchInventar() {
		inventory.setBounds(x_right, y_north, x_width, y_height);

		// JButton:
		ok.setBounds(x_right, y_south5, xOK_width, y_height);
		contentpane5.add(ok);

		// JLabels:
		bTitel.setBounds(x_center, y_north, x_width, y_height);
		contentpane5.add(bTitel);
		bAutor.setBounds(x_center, y_center, x_width, y_height);
		contentpane5.add(bAutor);
		bISBN.setBounds(x_center, y_south, x_width, y_height);
		contentpane5.add(bISBN);

		// JTextField:
		titel.setBounds(x_center, y_north2, x_width, y_height);
		contentpane5.add(titel);
		autor.setBounds(x_center, y_center2, x_width, y_height);
		contentpane5.add(autor);
		isbn.setBounds(x_center, y_south2, x_width, y_height);
		contentpane5.add(isbn);

		// JComboBox
		menueAuswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane5.add(menueAuswahl);
		statusAuswahl.setBounds(x_center, y_south5, x_width, y_height);
		contentpane5.add(statusAuswahl);

		inventory.setSize(450, 500);
		inventory.setLocation(100, 100);
		inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		inventory.setVisible(true);
	}

	public void launchCreate() {
		create.setBounds(x_right, y_north, x_width, y_height);

		// JButton:
		ok.setBounds(x_right, y_opt4, xOK_width, y_height);
		contentpane6.add(ok);

		// JLabels:
		pName.setBounds(x_left, y_north3, x_width, y_height);
		contentpane6.add(pName);
		pVorname.setBounds(x_left, y_center, x_width, y_height);
		contentpane6.add(pVorname);
		benutzername.setBounds(x_left, y_center2, x_width, y_height);
		contentpane6.add(benutzername);
		passwort.setBounds(x_left, y_center3, x_width, y_height);
		contentpane6.add(passwort);
		matrikelnummer.setBounds(x_left, y_south2, x_width, y_height);
		contentpane6.add(matrikelnummer);
		studiengruppe.setBounds(x_left, y_south3, x_width, y_height);
		contentpane6.add(studiengruppe);
		fakultaet.setBounds(x_left, y_south6, x_width, y_height);
		contentpane6.add(fakultaet);

		// optional
		strasse.setBounds(x_left, y_opt1, x_width, y_height);
		contentpane6.add(strasse);
		hausnummer.setBounds(x_left, y_opt2, x_width, y_height);
		contentpane6.add(hausnummer);
		postleitzahl.setBounds(x_left, y_opt3, x_width, y_height);
		contentpane6.add(postleitzahl);
		pOrt.setBounds(x_left, y_opt4, x_width, y_height);
		contentpane6.add(pOrt);

		// JTextField:
		name.setBounds(x_center, y_north3, x_width, y_height);
		contentpane6.add(name);
		vorname.setBounds(x_center, y_center, x_width, y_height);
		contentpane6.add(vorname);
		username.setBounds(x_center, y_center2, x_width, y_height);
		contentpane6.add(username);
		pw.setBounds(x_center, y_center3, x_width, y_height);
		contentpane6.add(pw);
		matnr.setBounds(x_center, y_south2, x_width, y_height);
		contentpane6.add(matnr);
		sg.setBounds(x_center, y_south3, x_width, y_height);
		contentpane6.add(sg);
		fak.setBounds(x_center, y_south6, x_width, y_height);
		contentpane6.add(fak);

		// optional
		str.setBounds(x_center, y_opt1, x_width, y_height);
		contentpane6.add(str);
		hausnr.setBounds(x_center, y_opt2, x_width, y_height);
		contentpane6.add(hausnr);
		plz.setBounds(x_center, y_opt3, x_width, y_height);
		contentpane6.add(plz);
		ort.setBounds(x_center, y_opt4, x_width, y_height);
		contentpane6.add(ort);

		// JComboBoxes:
		menueAuswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane6.add(menueAuswahl);
		benutzerAuswahl.setBounds(x_center, y_north, x_width, y_height);
		contentpane6.add(benutzerAuswahl);

		create.setSize(450, 600);
		create.setLocation(100, 100);
		create.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		create.setVisible(true);
		
		if(tableview==null){
			tableview = new JTableview(DB_connection.getAllUsers());
			scrollPane = new JScrollPane(tableview.getSQLTable());
			scrollPane.setBounds(x_centerLibrary, y_Library, x_widthLibrary, y_heightLibrary);
			contentpane6.add(scrollPane);
		}	
		
		Benutzer();
		

	}

	public void launchAlter() {
		alter.setBounds(x_right, y_north, x_width, y_height);

		// JButton:
		ändern.setBounds(x_right, y_opt4, xButton_width, y_height);
		contentpane7.add(ändern);
		löschen.setBounds(x_right, y_opt5, xButton_width, y_height);
		contentpane7.add(löschen);

		// JLabels:
		pName.setBounds(x_left, y_north3, x_width, y_height);
		contentpane7.add(pName);
		pVorname.setBounds(x_left, y_center, x_width, y_height);
		contentpane7.add(pVorname);
		benutzername.setBounds(x_left, y_center2, x_width, y_height);
		contentpane7.add(benutzername);
		passwort.setBounds(x_left, y_center3, x_width, y_height);
		contentpane7.add(passwort);
		matrikelnummer.setBounds(x_left, y_south2, x_width, y_height);
		contentpane7.add(matrikelnummer);
		studiengruppe.setBounds(x_left, y_south3, x_width, y_height);
		contentpane7.add(studiengruppe);
		fakultaet.setBounds(x_left, y_south6, x_width, y_height);
		contentpane7.add(fakultaet);

		strasse.setBounds(x_left, y_opt1, x_width, y_height);
		contentpane7.add(strasse);
		hausnummer.setBounds(x_left, y_opt2, x_width, y_height);
		contentpane7.add(hausnummer);
		postleitzahl.setBounds(x_left, y_opt3, x_width, y_height);
		contentpane7.add(postleitzahl);
		pOrt.setBounds(x_left, y_opt4, x_width, y_height);
		contentpane7.add(pOrt);

		// JTextField:
		name.setBounds(x_center, y_north3, x_width, y_height);
		contentpane7.add(name);
		vorname.setBounds(x_center, y_center, x_width, y_height);
		contentpane7.add(vorname);
		username.setBounds(x_center, y_center2, x_width, y_height);
		contentpane7.add(username);
		pw.setBounds(x_center, y_center3, x_width, y_height);
		contentpane7.add(pw);
		matnr.setBounds(x_center, y_south2, x_width, y_height);
		contentpane7.add(matnr);
		sg.setBounds(x_center, y_south3, x_width, y_height);
		contentpane7.add(sg);
		fak.setBounds(x_center, y_south6, x_width, y_height);
		contentpane7.add(fak);

		str.setBounds(x_center, y_opt1, x_width, y_height);
		contentpane7.add(str);
		hausnr.setBounds(x_center, y_opt2, x_width, y_height);
		contentpane7.add(hausnr);
		plz.setBounds(x_center, y_opt3, x_width, y_height);
		contentpane7.add(plz);
		ort.setBounds(x_center, y_opt4, x_width, y_height);
		contentpane7.add(ort);

		// JComboBoxes:
		menueAuswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane7.add(menueAuswahl);
		benutzerAuswahl.setBounds(x_center, y_north, x_width, y_height);
		contentpane7.add(benutzerAuswahl);

		alter.setSize(450, 600);
		alter.setLocation(100, 100);
		alter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		alter.setVisible(true);
		
		if(tableview==null){
			tableview = new JTableview(DB_connection.getAllUsers());
			scrollPane = new JScrollPane(tableview.getSQLTable());
			scrollPane.setBounds(x_centerLibrary, y_Library, x_widthLibrary, y_heightLibrary);
			contentpane7.add(scrollPane);
		}

	}
	
	//TODO Frame-Wechsel & Berechtigungen
	public void Menü(){
		while(menueAuswahl.getSelectedIndex()==0){
			if(menueAuswahl.getSelectedIndex()==1){
				
			}if(menueAuswahl.getSelectedIndex()==2){
				
			}if(menueAuswahl.getSelectedIndex()==3){
				
			}if(menueAuswahl.getSelectedIndex()==4){
				
			}if(menueAuswahl.getSelectedIndex()==5){
				
			}if(menueAuswahl.getSelectedIndex()==6){
				
			}if(menueAuswahl.getSelectedIndex()==7){
				
			}
		}
		//TODO Erst überprüfen wenn ok geklickt wird! Wie umsetzten?
		while(menueAuswahl.getSelectedIndex()==0){
			if(menueAuswahl.getSelectedIndex()==1){
				ok.setActionCommand(ACTION_NEXT_AUSLEIHE);
				ok.addActionListener(control);
			}if(menueAuswahl.getSelectedIndex()==2){
				ok.setActionCommand(ACTION_NEXT_RÜCKGABE);
				ok.addActionListener(control);
			}if(menueAuswahl.getSelectedIndex()==3){
				ok.setActionCommand(ACTION_NEXT_ANZEIGE);
				ok.addActionListener(control);
			}if(menueAuswahl.getSelectedIndex()==4){
				ok.setActionCommand(ACTION_NEXT_INVENTARISIERUNG);
				ok.addActionListener(control);
			}if(menueAuswahl.getSelectedIndex()==5){
				ok.setActionCommand(ACTION_NEXT_ANLEGEN);
				ok.addActionListener(control);
			}if(menueAuswahl.getSelectedIndex()==6){
				ok.setActionCommand(ACTION_NEXT_ÄNDERN);
				ok.addActionListener(control);
			}
		}
	}
	
	public void Benutzer(){
		while(benutzerAuswahl.getSelectedIndex()==0){
			System.out.println("in While loop");
			if(benutzerAuswahl.getSelectedIndex()==1){
				ok.setActionCommand(ACTION_CREATE_STUD);
				ok.addActionListener(control);
				System.out.println(benutzerAuswahl.getSelectedIndex());
			}if(benutzerAuswahl.getSelectedIndex()==2){
				ok.setActionCommand(ACTION_CREATE_PROF);
				ok.addActionListener(control);
				System.out.println(benutzerAuswahl.getSelectedIndex());
			}if(benutzerAuswahl.getSelectedIndex()==3){
				ok.setActionCommand(ACTION_CREATE_PERS);
				ok.addActionListener(control);
				System.out.println(benutzerAuswahl.getSelectedIndex());
			}
		}
	}
	
	public void setLoginJFrame(){
		tableview.updateSQLTable(DB_connection.getAllUsers());
		this.login.setVisible(true);
	}
	
	public void setRentJFrame(){
		//TODO tableview getAllBücher
		//hide vorherige Fenster?
		this.launchRent();
	}
	
	//TODO setJFrame
}
