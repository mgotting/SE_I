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
	JFrame rückgabe;
	JPanel panel;
	JButton zurückgeben;
	public JTableview tableview;
	private JScrollPane scrollPane;
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	int x_center = 140;
	int y_bottom = 350;
	int x_widthLibrary = 260;
	int y_heightLibrary = 100;
	int x_centerLibrary = 90;
	int y_south2 = 200;
	
	public BuchRueckgabe(){
		rückgabe = new JFrame("Rückgabe");
		panel = new JPanel();
		rückgabe.setContentPane(panel);
		panel.setLayout(null);
		zurückgeben = new JButton("zurückgeben");
	}
	
	public void launchRückgabe() {
		rückgabe.setBounds(x_right, y_north, x_width, y_height);

		// JButtons:
		zurückgeben.setBounds(x_center, y_bottom, x_width, y_height);
		panel.add(zurückgeben);

		zurückgeben.setSize(450, 500);
		zurückgeben.setLocation(100, 100);
		rückgabe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		zurückgeben.setVisible(true);
		
		if(tableview==null){
			tableview = new JTableview(DB_connection.getAllRentBooks(ButtonHandler.getBenutzername()));
			scrollPane = new JScrollPane(tableview.getSQLTable());
			scrollPane.setBounds(x_centerLibrary, y_south2, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}
	}

}
