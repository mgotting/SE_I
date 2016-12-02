package gui;

import javax.swing.*;
import java.awt.*;


// Erstellt die Oberfläche der GUI

public class surface {
	public JFrame login;	//warum public?
	private Container contentpane;
	private JButton ok;
	public JTextField username, pw;	//warum public?
	public JLabel benutzername, passwort;
	public JComboBox user, menü;
	
	int y_north = 20;
	int y_center = 40;
	int y_south = 300;
	int y_bottom = 350;
	int x_left = 10;
	int x_center = 150;
	int x_right = 140;
	int x_width = 140;
	int y_height = 20;
	
	public surface (){
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
		//TODO Combobox
	}
	
	public void launchLogin(){
		login.setBounds(x_right, y_north, x_width, y_height);
		contentpane.add(ok);
		benutzername.setBounds(x_center, y_north, x_width, y_height);
		contentpane.add(benutzername);
		//passwort.setBounds(x_center, y_center, x_width, y_height);
		//contentpane.add(passwort);
		username.setBounds(x_center, y_center, x_width, y_height);
		contentpane.add(username);
		//pw.setBounds(x_center, y_center, x_width, y_height);
		//contentpane.add(pw);
		//TODO Combobox
		
		login.setSize(450, 500);
		login.setLocation(100,100);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließen des Fensters ist möglich
		login.setVisible(true);
	}

}
