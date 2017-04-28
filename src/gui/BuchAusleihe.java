
package gui;

import javax.swing.*;
import java.sql.*;

import application.ButtonHandler;
import connectionToDatabase.DB_connection;
import connectionToDatabase.JTableview;


public class BuchAusleihe {
	DB_connection cn = null;
	JPanel panel;
	JButton ausleihen, buchAusw�hlen, best�tigen, abbrechen;
	public JTableview tableviewBooks, tableviewRentBooks;
	JScrollPane scrollPane, scrollPane2;
	public Savepoint svpnt = null;
	
	ButtonHandler controlButton;
	
	public final static String ACTION_BUCH_AUSLEIHEN = "AUSLEIHEN";
	public final static String ACTION_BUCH_AUSWAHL = "BUCH_AUSWAHL";
	public final static String ACTION_BUCH_BEST�TIGEN = "BEST�TIGEN";
	public final static String ACTION_BUCH_ABBRECHEN = "ABBRECHEN";
	
	int y_north = 60;
	int y_north2 = 80;
	int y_center = 120;
	int y_center2 = 140;
	int y_south = 180;
	int y_south2 = 200;
	int y_Library = 250;
	int x_left = 10;
	int x_left2 = 370;	
	int x_center = 500;
	int x_right = 700;
	int x_right2= 1040;
	int x_width = 160;
	int x_BUTTON_width = 120;
	int y_height = 20;
	int x_widthLibrary = 1000;
	int y_heightLibrary = 100;
	
	public JTextField tfTitel, tfAutor, tfIsbn;
	public JLabel labelTitel, labelAutor, labelISBN;
	//private String benutzerArt;
	
	public BuchAusleihe(){
		panel = new JPanel();
		// Erzeugung eines Objektes der Klasse JButton
		ausleihen = new JButton("ausleihen");
		buchAusw�hlen = new JButton("ausw�hlen");
		best�tigen = new JButton ("best�tigen");
		abbrechen = new JButton ("abbrechen");
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
	}
		
	public void launchBuchAusleihen(JFrame auswahl){
		auswahl.getContentPane().setVisible(false);
		// Wir f�gen den JButton unserem Panel hinzu:
		ausleihen.setBounds(x_right, y_south2, x_BUTTON_width, y_height);
		panel.add(ausleihen);
		ausleihen.setActionCommand(ACTION_BUCH_AUSLEIHEN);
		ausleihen.addActionListener(controlButton);
		
		buchAusw�hlen.setBounds(x_right2, y_Library, x_BUTTON_width, y_height);
		panel.add(buchAusw�hlen);
		buchAusw�hlen.setActionCommand(ACTION_BUCH_AUSWAHL);
		buchAusw�hlen.addActionListener(controlButton);
		
		best�tigen.setBounds(x_right2 , y_Library + 150, x_BUTTON_width, y_height );
		panel.add(best�tigen);
		best�tigen.setActionCommand(ACTION_BUCH_BEST�TIGEN);
		best�tigen.addActionListener(controlButton);
		
		abbrechen.setBounds(x_right2, y_Library +220, x_BUTTON_width, y_height);
		panel.add(abbrechen);
		abbrechen.setActionCommand(ACTION_BUCH_ABBRECHEN);
		abbrechen.addActionListener(controlButton);
		

		// Wir f�gen die JLabel unserem Panel hinzu:
		labelTitel.setBounds(x_left2, y_north2, x_width, y_height);
		panel.add(labelTitel);
		labelAutor.setBounds(x_left2, y_center2, x_width, y_height);
		panel.add(labelAutor);
		labelISBN.setBounds(x_left2, y_south2, x_width, y_height);
		panel.add(labelISBN);

		// JTextField:
		tfTitel.setBounds(x_center, y_north2, x_width, y_height);
		panel.add(tfTitel);
		tfAutor.setBounds(x_center, y_center2, x_width, y_height);
		panel.add(tfAutor);
		tfIsbn.setBounds(x_center, y_south2, x_width, y_height);
		panel.add(tfIsbn);
		
		
		if(tableviewBooks==null){
			tableviewBooks = new JTableview(DB_connection.getAllAvailableBooks());
			scrollPane = new JScrollPane(tableviewBooks.getSQLTable());
			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
			panel.add(scrollPane);
		}
		
		if(tableviewRentBooks ==null){
			tableviewRentBooks = new JTableview(DB_connection.getAllRentBooks(ButtonHandler.getAngemeldeterUser()));
			scrollPane2 = new JScrollPane(tableviewRentBooks.getSQLTable());
			scrollPane2.setBounds(x_left, y_Library + 150, x_widthLibrary, y_heightLibrary+150);
			panel.add(scrollPane2);
		}
		auswahl.setTitle("Buch ausleihen");
		auswahl.setContentPane(panel);
		
		cn = DB_connection.getDbConnection();
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
