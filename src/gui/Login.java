package gui;

import javax.swing.*;

import connectionToDatabase.*;
import application.ButtonHandler;

/**
 * Klasse zur Erstellung der GUI zum Einloggen
 * 
 * @author Michael Gottinger
 */
 
public class Login {
	public JFrame login;
	public JPanel panel;
	private JButton anmelden;
	private ButtonHandler controlButton;
	private JLabel labelBenutzername, labelPasswort;
	private JTextField benutzername;
	private JPasswordField passwort;
	
	public final static String ACTION_LOGIN = "ANMELDEN";
	
	int x_left = 370;
	int x_right = 700;
	int x_center = 500;
	int y_north = 60;
	int y_north2 = 80;
	int y_center = 120;
	int y_center2 = 140;
	int y_south = 200;
	
	int x_width = 160;
	int y_height = 20;
	
	
	int x_anmelden_width = 120;
	int x_widthLibrary = 450;
	int y_heightLibrary = 100;
	int y_Library = 420;
	
	
	public Login(){
		controlButton = new ButtonHandler(this);
		// Erzeugung eines Objektes um ActionEvents zu handeln
		controlButton = new ButtonHandler(this);
		login = new JFrame("Login");
		panel = new JPanel();
		login.setContentPane(panel);
		panel.setLayout(null);
		anmelden = new JButton("anmelden");
		labelBenutzername = new JLabel("Benutzername:", JLabel.LEFT);
		labelPasswort = new JLabel("Passwort:", JLabel.LEFT);
		benutzername = new JTextField(45);
		passwort = new JPasswordField(10);
	}
	
	public String getBenutzername(){
			String benutzername = this.benutzername.getText();
			return benutzername;
		}
	
	public void setBenutzername(String benutzername){
		this.benutzername.setText(benutzername);
	}
	
	public String getPasswort(){
		@SuppressWarnings("deprecation")
		String passwort = this.passwort.getText();
		return passwort;
	}
	
	public void setPasswort(String passwort){
		this.passwort.setText(passwort);
	}
	
	public void launchLogin(){
		login.setBounds(x_right, y_north, x_width, y_height);

		// JButtons:
		panel.getRootPane().setDefaultButton(anmelden);
		anmelden.setBounds(x_right, y_center2, x_anmelden_width, y_height);
		panel.add(anmelden);
		anmelden.setActionCommand(ACTION_LOGIN);
		anmelden.addActionListener(controlButton);

		// JLables:
		labelBenutzername.setBounds(x_left, y_north2, x_width, y_height);
		panel.add(labelBenutzername);
		labelPasswort.setBounds(x_left, y_center2, x_width, y_height);
		panel.add(labelPasswort);

		// JTextFields:
		benutzername.setBounds(x_center, y_north2, x_width, y_height);
		panel.add(benutzername);
		passwort.setBounds(x_center, y_center2, x_width, y_height);
		panel.add(passwort);

		login.setSize(1200, 600);
		login.setLocation(100, 100);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schlie�en des Fensters ist m�glich
		login.setVisible(true);
	}
}
