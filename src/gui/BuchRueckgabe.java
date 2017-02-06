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
	JButton zur�ckgeben;
	public JTableview tableview;
	private JScrollPane scrollPane;
	
	ButtonHandler controlButton;
	
	public final static String ACTION_BUCH_ZUR�CKGEBEN = "BUCH_ZUR�CKGEBEN";
	
	int y_south = 200;
	int y_bottom = 350;
	int x_width = 160;
	int y_height = 20;		
	int x_widthLibrary = 1000;
	int y_heightLibrary = 100;
	int x_centerLibrary = 100;
	
	
	public BuchRueckgabe(){
		panel = new JPanel();
		zur�ckgeben = new JButton("zur�ckgeben");
		controlButton = new ButtonHandler(this);
		panel.setLayout(null);
		zur�ckgeben.setActionCommand(ACTION_BUCH_ZUR�CKGEBEN);
		zur�ckgeben.addActionListener(controlButton);
	}
	
	public void launchR�ckgabe(JFrame auswahl) {
		auswahl.getContentPane().setVisible(false);
		// JButtons:
		zur�ckgeben.setBounds(x_centerLibrary, y_bottom, x_width, y_height);
		panel.add(zur�ckgeben);
		
		if(tableview==null){
			tableview = new JTableview(DB_connection.getAllRentBooks(ButtonHandler.getAngemeldeterUser()));
			scrollPane = new JScrollPane(tableview.getSQLTable());
			scrollPane.setBounds(x_centerLibrary, y_south, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}
		auswahl.setTitle("Buch zur�ckgeben");
		auswahl.setContentPane(panel);
	}

}
