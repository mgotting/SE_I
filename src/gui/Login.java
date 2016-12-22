/**
 * 
 */
package gui;

import javax.swing.*;

import connectionToDatabase.*;
import application.ButtonHandler;

/**
 * @author Gotti
 *
 */

public class Login {
	public JFrame login;
	private JPanel panel;
	private JButton anmelden;
	private ButtonHandler controlButton;
	private JLabel labelBenutzername, labelPasswort;
	private JTextField benutzername, passwort;
	public JTableview tableview;
	private JScrollPane scrollPane;
	
	public final static String ACTION_LOGIN = "ANMELDEN";
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	int x_center = 140;
	int y_center = 120;
	int y_center2 = 140;
	int y_north2 = 80;
	int x_anmelden_width = 70;
	int x_widthLibrary = 260;
	int y_heightLibrary = 100;
	int x_centerLibrary = 90;
	int y_Library = 420;
	int y_south2 = 200;
	
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
		passwort = new JTextField(10);
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
	
	public void launchLogin(){
		login.setBounds(x_right, y_north, x_width, y_height);

		// JButtons:
		anmelden.setBounds(x_right, y_center2, x_anmelden_width, y_height);
		panel.add(anmelden);
		anmelden.setActionCommand(ACTION_LOGIN);
		anmelden.addActionListener(controlButton);

		// JLables:
		labelBenutzername.setBounds(x_center, y_north, x_width, y_height);
		panel.add(labelBenutzername);
		labelPasswort.setBounds(x_center, y_center, x_width, y_height);
		panel.add(labelPasswort);

		// JTextFields:
		benutzername.setBounds(x_center, y_north2, x_width, y_height);
		panel.add(benutzername);
		passwort.setBounds(x_center, y_center2, x_width, y_height);
		panel.add(passwort);

		login.setSize(450, 500);
		login.setLocation(100, 100);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		login.setVisible(true);
		
		if(tableview==null){
			tableview = new JTableview(DB_connection.getAllUsers());
			scrollPane = new JScrollPane(tableview.getSQLTable());
			scrollPane.setBounds(x_centerLibrary, y_south2, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}
	}
}
