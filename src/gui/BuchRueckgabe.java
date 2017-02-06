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
	
	ButtonHandler controlButton;
	
	public final static String ACTION_BUCH_ZURÜCKGEBEN = "BUCH_ZURÜCKGEBEN";
	
	int y_south = 200;
	int y_bottom = 350;
	int x_width = 160;
	int y_height = 20;		
	int x_widthLibrary = 1000;
	int y_heightLibrary = 100;
	int x_centerLibrary = 100;
	
	
	public BuchRueckgabe(){
		panel = new JPanel();
		zurückgeben = new JButton("zurückgeben");
		controlButton = new ButtonHandler(this);
		panel.setLayout(null);
		zurückgeben.setActionCommand(ACTION_BUCH_ZURÜCKGEBEN);
		zurückgeben.addActionListener(controlButton);
	}
	
	public void launchRückgabe(JFrame auswahl) {
		auswahl.getContentPane().setVisible(false);
		// JButtons:
		zurückgeben.setBounds(x_centerLibrary, y_bottom, x_width, y_height);
		panel.add(zurückgeben);
		
		if(tableview==null){
			tableview = new JTableview(DB_connection.getAllRentBooks(ButtonHandler.getAngemeldeterUser()));
			scrollPane = new JScrollPane(tableview.getSQLTable());
			scrollPane.setBounds(x_centerLibrary, y_south, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}
		auswahl.setTitle("Buch zurückgeben");
		auswahl.setContentPane(panel);
	}

}
