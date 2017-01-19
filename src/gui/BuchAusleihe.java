/**
 * 
 */
package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import application.ButtonHandler;
import connectionToDatabase.DB_connection;
import connectionToDatabase.JTableview;

/**
 * @author Sandra
 *
 */
public class BuchAusleihe {
	JPanel panel;
	JButton ausleihen, buchAuswählen;
	public JTableview tableviewBooks;
	JScrollPane scrollPane;
	String angemeldeterUser;
	
	ButtonHandler controlButton;
	
	public final static String ACTION_BUCH_AUSLEIHEN = "AUSLEIHEN";
	public final static String ACTION_BUCH_AUSWAHL = "BUCH_AUSWAHL";
	
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
	
	public JTextField tfTitel, tfAutor, tfIsbn;
	public JLabel labelTitel, labelAutor, labelISBN;
	//private String benutzerArt;
	
	public BuchAusleihe(String angemeldeterUser){
		this.angemeldeterUser = angemeldeterUser;
		panel = new JPanel();
		// Erzeugung eines Objektes der Klasse JButton
		ausleihen = new JButton("ausleihen");
		buchAuswählen = new JButton("auswählen");
		// Erzeugung eines Objektes um ActionEvents zu handeln
		controlButton = new ButtonHandler(this);

		panel.setLayout(null);
		
		//Erzeugung der JLabels
		labelTitel = new JLabel("Buchtitel:", JLabel.LEFT);
		labelAutor = new JLabel("Autor:", JLabel.LEFT);
		labelISBN = new JLabel("ISBN-Nummer:", JLabel.LEFT);
				
		//Erzeugung der JTextFields
		tfTitel = new JTextField(50);
		tfAutor = new JTextField(45);
		tfIsbn = new JTextField(20);
		System.out.println("User: "+angemeldeterUser);
	}
		
	public void launchBuchAusleihen(JFrame auswahl){
		auswahl.getContentPane().setVisible(false);
		// Wir fügen den JButton unserem Panel hinzu:
		ausleihen.setBounds(x_right, y_south2, x_BUTTON_width, y_height);
		panel.add(ausleihen);
		ausleihen.setActionCommand(ACTION_BUCH_AUSLEIHEN);
		ausleihen.addActionListener(controlButton);
		buchAuswählen.setBounds(x_right, y_Library, x_BUTTON_width, y_height);
		panel.add(buchAuswählen);
		buchAuswählen.setActionCommand(ACTION_BUCH_AUSWAHL);
		buchAuswählen.addActionListener(controlButton);

		// Wir fügen die JLabel unserem Panel hinzu:
		labelTitel.setBounds(x_left, y_north2, x_width, y_height);
		panel.add(labelTitel);
		labelAutor.setBounds(x_left, y_center2, x_width, y_height);
		panel.add(labelAutor);
		labelISBN.setBounds(x_left, y_south2, x_width, y_height);
		panel.add(labelISBN);

		// JTextField:
		tfTitel.setBounds(x_center, y_north2, x_width, y_height);
		panel.add(tfTitel);
		tfAutor.setBounds(x_center, y_center2, x_width, y_height);
		panel.add(tfAutor);
		tfIsbn.setBounds(x_center, y_south2, x_width, y_height);
		panel.add(tfIsbn);
		
		//TODO JTableView
		if(tableviewBooks==null){
			tableviewBooks = new JTableview(DB_connection.getAllAvailableBooks());
			scrollPane = new JScrollPane(tableviewBooks.getSQLTable());
			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}	
		auswahl.setTitle("Buch ausleihen");
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
	
	public String getISBN()
	{
		String ISBN = this.tfIsbn.getText();
		return ISBN;
	}
	
	public void setBuchtitel(String titel)
	{
		this.tfTitel.setText(titel);
	}
	
	public void setAutor(String autor)
	{
		this.tfAutor.setText(autor);
	}
	
	public void setIsbn(String Isbn)
	{
		this.tfIsbn.setText(Isbn);
	}
	
	
}
