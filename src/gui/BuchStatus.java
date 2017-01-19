/**
 * 
 */
package gui;

import javax.swing.*;

import application.*;
import connectionToDatabase.*;

/**
 * @author Sandra
 *
 */
public class BuchStatus {
	
	JPanel panel;
	JButton buchstatus;
	public JTableview tableviewBooks;
	JScrollPane scrollPane;
	
//	TODO: Entscheiden, ob du Button benötigst!
//	ButtonHandler controlButton;
	
//	TODO: Wird evtl. benötigt
//	public final static String ACTION_BUCH_STATUS = "BUCH_STATUS";
	
	
	int y_Library = 250;
	int x_left = 10;
	int x_widthLibrary = 450;
	int y_heightLibrary = 100;
	

	
	public BuchStatus(){
		panel = new JPanel();
		buchstatus = new JButton("buchstatus");	

//		controlButton = new ButtonHandler(this);

		panel.setLayout(null);
		
	}
		
	public void launchBuchStatus(JFrame auswahl){
		auswahl.getContentPane().setVisible(false);		
	
		if(tableviewBooks==null){
			tableviewBooks = new JTableview(DB_connection.getAllBooks());
			scrollPane = new JScrollPane(tableviewBooks.getSQLTable());
			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}	
		
		auswahl.setTitle("Buchstatus anzeigen");
		auswahl.setContentPane(panel);
	
	
	}

}
