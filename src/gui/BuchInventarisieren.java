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
public class BuchInventarisieren {

	JPanel panel;
	JButton inventarisieren;
	public JTableview tableviewBooks;
	JScrollPane scrollPane;
	
	ButtonHandler controlButton;
	
	public final static String ACTION_BUCH_INVENTARISIEREN = "INVENTARISIEREN";
	
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
	
	public JTextField tfTitel, tfAutor, tfIsbn, tfAnzahl;
	public JLabel labelTitel, labelAutor, labelISBN, labelAnzahl;
	
	public BuchInventarisieren(){
		panel = new JPanel();
		// Erzeugung eines Objektes der Klasse JButton
		inventarisieren = new JButton("inventarisieren");
		
		// Erzeugung eines Objektes um ActionEvents zu handeln
		controlButton = new ButtonHandler(this);

		panel.setLayout(null);
		
		//Erzeugung der JLabels
		labelTitel = new JLabel("Buchtitel:", JLabel.LEFT);
		labelAutor = new JLabel("Autor:", JLabel.LEFT);
		labelISBN = new JLabel("ISBN-Nummer:", JLabel.LEFT);
		labelAnzahl = new JLabel ("Anzahl an Exemplaren:", JLabel.LEFT);
				
		//Erzeugung der JTextFields
		tfTitel = new JTextField(50);
		tfAutor = new JTextField(45);
		tfIsbn = new JTextField(20);
		tfAnzahl = new JTextField(10);
	}
		
	public void launchBuchInventarisieren(JFrame auswahl){
		auswahl.getContentPane().setVisible(false);
		// Wir f�gen den JButton unserem Panel hinzu:
		inventarisieren.setBounds(x_right, y_south2, x_BUTTON_width, y_height);
		panel.add(inventarisieren);
		inventarisieren.setActionCommand(ACTION_BUCH_INVENTARISIEREN);
		inventarisieren.addActionListener(controlButton);
		
		// Wir f�gen die JLabel unserem Panel hinzu:
		labelTitel.setBounds(x_center, y_north, x_width, y_height);
		panel.add(labelTitel);
		labelAutor.setBounds(x_center, y_center, x_width, y_height);
		panel.add(labelAutor);
		labelISBN.setBounds(x_center, y_south, x_width, y_height);
		panel.add(labelISBN);
	

		// JTextField:
		tfTitel.setBounds(x_center, y_north2, x_width, y_height);
		panel.add(tfTitel);
		tfAutor.setBounds(x_center, y_center2, x_width, y_height);
		panel.add(tfAutor);
		tfIsbn.setBounds(x_center, y_south2, x_width, y_height);
		panel.add(tfIsbn);
		
		
		//TODO JTableView
//		if(tableviewBooks==null){
//			tableviewBooks = new JTableview(DB_connection.getAllBooks());
//			scrollPane = new JScrollPane(tableviewBooks.getSQLTable());
//			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
//			panel.add(scrollPane);
//		}	
		
		auswahl.setTitle("Buch inventarisieren");
		auswahl.setContentPane(panel);
	
	
	}
	
	public String getBuchtitel()
	{
		String buchtitel = this.tfTitel.getText();
		return buchtitel;
	}
	
	public String getAutor()
	{
		String autor = this.tfAutor.getText();
		return autor;
	}
	
	public int getISBN()
	{
		int ISBN = Integer.parseInt(this.tfIsbn.getText());
		return ISBN;
	}
}
