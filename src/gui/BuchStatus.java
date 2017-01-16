/**
 * 
 */
package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import application.ButtonHandler;
import connectionToDatabase.JTableview;

/**
 * @author Sandra
 *
 */
public class BuchStatus {
	
	JPanel panel;
//	JButton ;
	public JTableview tableviewBooks;
	JScrollPane scrollPane;
	
//	TODO: Entscheiden, ob du Button benötigst!
//	ButtonHandler controlButton;
	
//	TODO: Wird evtl. benötigt
//	public final static String ACTION_BUCH_STATUS = "BUCH_STATUS";
	
	int y_north = 60;
	int y_north2 = 80;
	int y_center = 120;
	int y_center2 = 140;
	int y_south = 180;
	int y_south2 = 200;
	int y_Library = 250;
	int x_left = 10;
	int x_center = 140;
	int x_right = 330;
	int x_width = 160;
	int x_BUTTON_width = 120;
	int y_height = 20;
	int x_widthLibrary = 450;
	int y_heightLibrary = 100;
	

	
	public BuchStatus(){
		panel = new JPanel();
		// Erzeugung eines Objektes der Klasse JButton
//		inventarisieren = new JButton("inventarisieren");
		
		// Erzeugung eines Objektes um ActionEvents zu handeln
//		controlButton = new ButtonHandler(this);

		panel.setLayout(null);
		
	}
		
	public void launchBuchStatus(JFrame auswahl){
		auswahl.getContentPane().setVisible(false);
		// Wir fügen den JButton unserem Panel hinzu:
//		inventarisieren.setBounds(x_right, y_south2, x_BUTTON_width, y_height);
//		panel.add(inventarisieren);
//		inventarisieren.setActionCommand(ACTION_BUCH_INVENTARISIEREN);
//		inventarisieren.addActionListener(controlButton);
		
		
		//TODO JTableView
//		if(tableviewBooks==null){
//			tableviewBooks = new JTableview(DB_connection.getAllBooks());
//			scrollPane = new JScrollPane(tableviewBooks.getSQLTable());
//			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
//			panel.add(scrollPane);
//		}	
		
		auswahl.setTitle("Buchstatus anzeigen");
		auswahl.setContentPane(panel);
	
	
	}

}
