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
	JFrame r�ckgabe;
	JPanel panel;
	JButton zur�ckgeben;
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
		r�ckgabe = new JFrame("R�ckgabe");
		panel = new JPanel();
		r�ckgabe.setContentPane(panel);
		panel.setLayout(null);
		zur�ckgeben = new JButton("zur�ckgeben");
	}
	
	public void launchR�ckgabe() {
		r�ckgabe.setBounds(x_right, y_north, x_width, y_height);

		// JButtons:
		zur�ckgeben.setBounds(x_center, y_bottom, x_width, y_height);
		panel.add(zur�ckgeben);

		zur�ckgeben.setSize(450, 500);
		zur�ckgeben.setLocation(100, 100);
		r�ckgabe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schlie�en des Fensters ist m�glich
		zur�ckgeben.setVisible(true);
		
		if(tableview==null){
			tableview = new JTableview(DB_connection.getAllRentBooks(ButtonHandler.getBenutzername()));
			scrollPane = new JScrollPane(tableview.getSQLTable());
			scrollPane.setBounds(x_centerLibrary, y_south2, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}
	}

}
