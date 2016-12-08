package gui;

import javax.swing.*;
import java.awt.*;


// Erstellt die Oberfläche der GUI

public class surface {
	public JFrame login, rent, back, status, inventory, create;	//warum public?
	private Container contentpane, contentpane2, contentpane3, contentpane4, contentpane5, contentpane6;
	private JButton ok, ausleihen, zurückgeben ;
	public JTextField username, pw, titel, autor, isbn, name, vorname, matnr, sg, fak, str, hausnr, plz, ort;	//warum public?
	public JLabel benutzername, passwort, bTitel, bAutor, bISBN, pName, pVorname, matrikelnummer, studiengruppe, fakultaet, strasse, hausnummer, postleitzahl, pOrt;
	
	private static String[] benutzerliste = {"Benutzer wählen", "Student", "Professor", "Personal"};
	JComboBox benutzerAuswahl = new JComboBox(benutzerliste);
	private static String[] menueliste = {"Menü:", "Ausleihe", "Rückgabe", "Bücher anzeigen", "Bücher inventarisieren", "Benutzer anlegen/anzeigen", "Person ändern"};
	JComboBox menueAuswahl = new JComboBox (menueliste);
	private static String[] menuelisteAllg = {"Menü:", "Ausleihe", "Rückgabe", "Bücher anzeigen", "Bücher inventarisieren", "Benutzer anlegen/anzeigen", "Person ändern", "Logout"};
	JComboBox menue2Auswahl = new JComboBox (menuelisteAllg);
	private static String[] statusliste = {"Status wählen", "ausleihbar", "ausgeliehen", "nicht ausleihbar"};
	JComboBox statusAuswahl = new JComboBox (statusliste);
	
	//Layout LOGIN-GUI:
	int ylogin_north = 20;
	int ylogin_center = 70;
	int yloginUser_center = 120;
	int yloginUser2_center = 140;
	int yloginPW_center = 180;
	int yloginPW2_center = 200;
	
	//Layout allgemein:
	int xOK_width = 70;
	int xRENT_width = 90;
	int yMENU_north = 10;
	
	int y_north = 60;
	int y_north2 = 80;
	int y_center = 120;
	int y_center2 = 140;
	int y_south = 180;
	int y_south2 = 200;
	int y_south3 = 250;
	//int y_south4 = 260;
	int y_bottom = 350;
	int x_left = 10;
	int x_center = 140;
	int x_right = 330;
	int x_width = 160;
	int y_height = 20;
	
	
	public surface (){
		//LOGIN-GUI
		login = new JFrame ("Login");
		contentpane = new Container();
		login.setContentPane(contentpane);
		contentpane.setLayout(null);
		ok = new JButton("OK");
		username = new JTextField(45);
		pw = new JTextField(10);
		benutzername = new JLabel("Benutzername:", JLabel.LEFT);
		benutzername.setBackground(Color.white);
		passwort = new JLabel("Passwort:", JLabel.LEFT);
		passwort.setBackground(Color.white);
		benutzerAuswahl.setBackground(Color.white);
		menueAuswahl.setBackground(Color.white);
		
		//AUSLEIH-GUI
		rent = new JFrame ("Ausleihe");
		contentpane2 = new Container();
		rent.setContentPane(contentpane2);
		contentpane2.setLayout(null);
		ausleihen = new JButton ("ausleihen");
		titel = new JTextField(50);
		autor = new JTextField(45);
		isbn = new JTextField(20);
		bTitel = new JLabel("Buchtitel:", JLabel.LEFT);
		bAutor = new JLabel("Autor:", JLabel.LEFT);
		bISBN = new JLabel("ISBN-Nummer:", JLabel.LEFT);
		menue2Auswahl.setBackground(Color.white);
		
		//RÜCKGABE-GUI
		back = new JFrame ("Rückgabe");
		contentpane3 = new Container();
		back.setContentPane(contentpane3);
		contentpane3.setLayout(null);
		zurückgeben = new JButton ("zurückgeben");
		
		//STATUS-GUI
		status = new JFrame ("Buchstatus");
		contentpane4 = new Container();
		status.setContentPane(contentpane4);
		contentpane4.setLayout(null);
		statusAuswahl.setBackground(Color.white);
		
		//INVENT-GUI
		inventory = new JFrame ("Buch inventarisieren");
		contentpane5 = new Container();
		inventory.setContentPane(contentpane5);
		contentpane5.setLayout(null);
		
		//CREATE-GUI
		create = new JFrame ("Benutzer anlegen");
		contentpane6 = new Container();
		create.setContentPane(contentpane6);
		contentpane6.setLayout(null);
		//TODO
	}
	
	public void launchLogin(){
		login.setBounds(x_right, y_north, x_width, y_height);
		
		//JButtons:
		ok.setBounds(x_right, yloginPW2_center, xOK_width, y_height);
		contentpane.add(ok);	
		
		//JComboBoxes:
		benutzerAuswahl.setBounds(x_center, ylogin_north, x_width, y_height);
		contentpane.add(benutzerAuswahl);	
		menueAuswahl.setBounds(x_center, ylogin_center, x_width, y_height);
		contentpane.add(menueAuswahl);
		
		//JLables:
		benutzername.setBounds(x_center, yloginUser_center, x_width, y_height);
		contentpane.add(benutzername);
		passwort.setBounds(x_center, yloginPW_center, x_width, y_height);
		contentpane.add(passwort);
		
		//JTextFields:
		username.setBounds(x_center, yloginUser2_center, x_width, y_height);
		contentpane.add(username);
		pw.setBounds(x_center, yloginPW2_center, x_width, y_height);
		contentpane.add(pw);
		
		login.setSize(450, 500);
		login.setLocation(100,100);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließen des Fensters ist möglich
		login.setVisible(true);

	}
	
	public void launchRent(){
		rent.setBounds(x_right, y_north, x_width, y_height);
		
		//JButton:
		ok.setBounds(x_right, yloginPW2_center, xOK_width, y_height);
		contentpane2.add(ok);
		ausleihen.setBounds(x_right, y_bottom, xRENT_width, y_height);
		contentpane2.add(ausleihen);
		
		//JLabels:
		bTitel.setBounds(x_center, y_north, x_width, y_height);
		contentpane2.add(bTitel);
		bAutor.setBounds(x_center, y_center, x_width, y_height);
		contentpane2.add(bAutor);
		bISBN.setBounds(x_center, y_south, x_width, y_height);
		contentpane2.add(bISBN);
		
		//JTextField:
		titel.setBounds(x_center, y_north2, x_width, y_height);
		contentpane2.add(titel);
		autor.setBounds(x_center, y_center2, x_width, y_height);
		contentpane2.add(autor);
		isbn.setBounds(x_center, y_south2, x_width, y_height);
		contentpane2.add(isbn);
		
		//JComboBox
		menue2Auswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane2.add(menue2Auswahl);
		
		rent.setSize(450, 500);
		rent.setLocation(100,100);
		rent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließen des Fensters ist möglich
		rent.setVisible(true);
	}

	public void launchReturn(){
		back.setBounds(x_right, y_north, x_width, y_height);
		
		//JButtons:
		zurückgeben.setBounds(x_center, y_bottom, x_width, y_height);
		contentpane3.add(zurückgeben);
		
		//JComboBox
		menue2Auswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane3.add(menue2Auswahl);
		
		back.setSize(450, 500);
		back.setLocation(100,100);
		back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließen des Fensters ist möglich
		back.setVisible(true);		
	}
	
	public void launchStatus(){
		status.setBounds(x_right, y_north, x_width, y_height);
		
		//JComboBox
		menue2Auswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane4.add(menue2Auswahl);
		statusAuswahl.setBounds(x_center, y_north2, x_width, y_height);
		contentpane4.add(statusAuswahl);
		
		status.setSize(450, 500);
		status.setLocation(100,100);
		status.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließen des Fensters ist möglich
		status.setVisible(true);	
	}

	public void launchInventar(){
		inventory.setBounds(x_right, y_north, x_width, y_height);
		
		//JButton:
		ok.setBounds(x_right, y_south3, xOK_width, y_height);
		contentpane5.add(ok);
		
		//JLabels:
		bTitel.setBounds(x_center, y_north, x_width, y_height);
		contentpane5.add(bTitel);
		bAutor.setBounds(x_center, y_center, x_width, y_height);
		contentpane5.add(bAutor);
		bISBN.setBounds(x_center, y_south, x_width, y_height);
		contentpane5.add(bISBN);
				
		//JTextField:
		titel.setBounds(x_center, y_north2, x_width, y_height);
		contentpane5.add(titel);
		autor.setBounds(x_center, y_center2, x_width, y_height);
		contentpane5.add(autor);
		isbn.setBounds(x_center, y_south2, x_width, y_height);
		contentpane5.add(isbn);
		
		//JComboBox
		menue2Auswahl.setBounds(x_left, yMENU_north, x_width, y_height);
		contentpane5.add(menue2Auswahl);
		statusAuswahl.setBounds(x_center, y_south3, x_width, y_height);
		contentpane5.add(statusAuswahl);
		
		inventory.setSize(450, 500);
		inventory.setLocation(100,100);
		inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließen des Fensters ist möglich
		inventory.setVisible(true);
	}

	public void launchCreate(){
		//TODO
	}
}
