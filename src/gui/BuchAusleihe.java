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

	JFrame ausleihe;
	JPanel panel;
	JMenuBar bar;
	JMenu menu;
	JMenuItem benutzerAnlegen, benutzer�ndern, buchAusleihen;
	JButton ausleihen, buchAusw�hlen;
	public JTableview tableviewBooks;
	JScrollPane scrollPane;
	
	ButtonHandler controlButton;
//	ComboBoxHandler controlComboBox;
	
	public final static String ACTION_BUCH_AUSLEIHEN = "BUCH_AUSLEIHEN";
	public final static String ACTION_BUCH_AUSWAHL = "BUCH_AUSW�HLEN";
	public final static String ACTION_BENUTZER_ANLEGEN = "BENUTZER_ANLEGEN";
	public final static String ACTION_BENUTZER_�NDERN = "BENUTZER_�NDERN";
	
	public MenuHandler controlMenu;
	
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
	
	public JTextField titel, autor, isbn;
	public JLabel labelTitel, labelAutor, labelISBN;
	//private String benutzerArt;
	
	public BuchAusleihe(){
		// Erzeugung eines neuen Frames mit dem Titel "BenutzerAnlegen"
		ausleihe = new JFrame("BenutzerAnlegen");
		panel = new JPanel();
		// Erstellen einer Men�leiste
		bar = new JMenuBar();
		// Erzeugung eines Objektes der Klasse JMenu
		menu = new JMenu("Menu");
		// Erzeugung eines Objektes der Klasse JMenuItem
		buchAusleihen = new JMenuItem("Buch ausleihen");
		benutzerAnlegen = new JMenuItem("Benutzer anlegen");
		benutzer�ndern = new JMenuItem("Benutzer �ndern");
		// Erzeugung eines Objektes der Klasse JButton
		ausleihen = new JButton("ausleihen");
		buchAusw�hlen = new JButton("ausw�hlen");
		// Erzeugung eines Objektes um ActionEvents zu handeln
		controlButton = new ButtonHandler(this);
		
		ausleihe.setContentPane(panel);
		panel.setLayout(null);
		
		//Erzeugung der JLabels
		labelTitel = new JLabel("Buchtitel:", JLabel.LEFT);
		labelAutor = new JLabel("Autor:", JLabel.LEFT);
		labelISBN = new JLabel("ISBN-Nummer:", JLabel.LEFT);
				
		//Erzeugung der JTextFields
		titel = new JTextField(50);
		autor = new JTextField(45);
		isbn = new JTextField(20);
	}
		
	public void launchBuchAusleihen(){
		ausleihe.setBounds(x_right, y_north, x_width, y_height);
		ausleihe.setJMenuBar(bar);
		// Men� wird der Men�leiste hinzugef�gt
		bar.add(menu);
		// Wir f�gen das JMenuItem unserem JMenu hinzu
		menu.add(benutzerAnlegen);
		menu.add(benutzer�ndern);
		menu.add(buchAusleihen);
		benutzerAnlegen.setActionCommand(ACTION_BENUTZER_ANLEGEN);
		benutzer�ndern.setActionCommand(ACTION_BENUTZER_�NDERN);
		buchAusleihen.setActionCommand(ACTION_BUCH_AUSLEIHEN);
		benutzerAnlegen.addActionListener(controlMenu);
		benutzer�ndern.addActionListener(controlMenu);
		buchAusleihen.addActionListener(controlMenu);
		
		// Wir f�gen den JButton unserem Panel hinzu:
		ausleihen.setBounds(x_right, y_south2, x_BUTTON_width, y_height);
		panel.add(ausleihen);
		ausleihen.setActionCommand(ACTION_BUCH_AUSLEIHEN);
		ausleihen.addActionListener(controlButton);
		buchAusw�hlen.setBounds(x_right, y_Library, x_BUTTON_width, y_height);
		panel.add(buchAusw�hlen);
		buchAusw�hlen.setActionCommand(ACTION_BUCH_AUSWAHL);
		buchAusw�hlen.addActionListener(controlButton);

		// Wir f�gen die JLabel unserem Panel hinzu:
		labelTitel.setBounds(x_center, y_north, x_width, y_height);
		panel.add(labelTitel);
		labelAutor.setBounds(x_center, y_center, x_width, y_height);
		panel.add(labelAutor);
		labelISBN.setBounds(x_center, y_south, x_width, y_height);
		panel.add(labelISBN);

		// JTextField:
		titel.setBounds(x_center, y_north2, x_width, y_height);
		panel.add(titel);
		autor.setBounds(x_center, y_center2, x_width, y_height);
		panel.add(autor);
		isbn.setBounds(x_center, y_south2, x_width, y_height);
		panel.add(isbn);
	
        // Wir setzen die Breite und die H�he unseres Fensters auf 500 Pixel */ 
		ausleihe.setSize(520, 600);
		ausleihe.setLocation(100, 100);
		// Beim schlie�en des GUI-Fensters wird JFrame geschlossen
		ausleihe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Wir lassen unseren Frame anzeigen
		ausleihe.setVisible(true);
		
//		if(tableviewBooks==null){
//			tableviewBooks = new JTableview(DB_connection.getAllBooks());
//			scrollPane = new JScrollPane(tableviewBooks.getSQLTable());
//			scrollPane.setBounds(x_left, y_Library, x_widthLibrary, y_heightLibrary);
//			panel.add(scrollPane);
//		}	
	}
}
