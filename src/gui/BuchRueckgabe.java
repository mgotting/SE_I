/**
 * 
 */
package gui;

import javax.swing.*;

import application.ButtonHandler;
import connectionToDatabase.*;

/**
 * @author Gotti
 *
 */
public class BuchRueckgabe {
	JPanel panel;
	JButton zurückgeben;
	public JTableview tableview;
	private JScrollPane scrollPane;
	String angemeldeterUser;
	
	ButtonHandler controlButton;
	
	public final static String ACTION_BUCH_ZURÜCKGEBEN = "BUCH_ZURÜCKGEBEN";
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	int x_center = 140;
	int y_bottom = 350;
	int x_widthLibrary = 450;
	int y_heightLibrary = 100;
	int x_centerLibrary = 90;
	int y_south2 = 200;
	
	public BuchRueckgabe(String angemeldeterUser){
		this.angemeldeterUser = angemeldeterUser;
		panel = new JPanel();
		zurückgeben = new JButton("zurückgeben");
		controlButton = new ButtonHandler(this);
		panel.setLayout(null);
		zurückgeben.setActionCommand(ACTION_BUCH_ZURÜCKGEBEN);
		zurückgeben.addActionListener(controlButton);
		System.out.println("User: "+angemeldeterUser);
	}
	
	public void launchRückgabe(JFrame auswahl) {
		auswahl.getContentPane().setVisible(false);
		// JButtons:
		zurückgeben.setBounds(x_centerLibrary, y_bottom, x_width, y_height);
		panel.add(zurückgeben);
		
		if(tableview==null){
			tableview = new JTableview(DB_connection.getAllRentBooks(angemeldeterUser));
			scrollPane = new JScrollPane(tableview.getSQLTable());
			scrollPane.setBounds(x_centerLibrary, y_south2, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}
		auswahl.setTitle("Buch zurückgeben");
		auswahl.setContentPane(panel);
	}

}
