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
	JMenuBar bar;
	JMenu menu;
	JMenuItem benutzerAnlegen, benutzerÄndern, buchAusleihen, buchRückgabe;
	JButton zurückgeben;
	public JTableview tableview;
	private JScrollPane scrollPane;
	
	ButtonHandler controlButton;
	
	public final static String ACTION_BUCH_AUSLEIHEN = "BUCH_AUSLEIHEN";
	public final static String ACTION_BUCH_AUSWAHL = "BUCH_AUSWÄHLEN";
	public final static String ACTION_BENUTZER_ANLEGEN = "BENUTZER_ANLEGEN";
	public final static String ACTION_BENUTZER_ÄNDERN = "BENUTZER_ÄNDERN";
	public final static String ACTION_BUCH_RÜCKGABE = "BUCH_RÜCKGABE";
	
	public MenuHandler controlMenu;
	
	int x_right = 330;
	int y_north = 60;
	int x_width = 160;
	int y_height = 20;
	int x_center = 140;
	int y_bottom = 350;
	int x_widthLibrary = 450;
	int y_heightLibrary = 100;
	int x_centerLibrary = 90;
	int y_south2 = 200;
	
	public BuchRueckgabe(){
		rückgabe = new JFrame("Rückgabe");
		panel = new JPanel();
		bar = new JMenuBar();
		menu = new JMenu("Menu");
		buchAusleihen = new JMenuItem("Buch ausleihen");
		benutzerAnlegen = new JMenuItem("Benutzer anlegen");
		benutzerÄndern = new JMenuItem("Benutzer ändern");
		buchRückgabe = new JMenuItem("Buch zurückgeben");
		zurückgeben = new JButton("zurückgeben");
		controlButton = new ButtonHandler(this);
		
		rückgabe.setContentPane(panel);
		panel.setLayout(null);
		
	}
	
	public void launchRückgabe() {
		rückgabe.setBounds(x_right, y_north, x_width, y_height);
		rückgabe.setJMenuBar(bar);
		// Menü wird der Menüleiste hinzugefügt
		bar.add(menu);
		// Wir fügen das JMenuItem unserem JMenu hinzu
		menu.add(buchRückgabe);
		menu.add(benutzerAnlegen);
		menu.add(benutzerÄndern);
		menu.add(buchAusleihen);
		
		benutzerAnlegen.setActionCommand(ACTION_BENUTZER_ANLEGEN);
		benutzerÄndern.setActionCommand(ACTION_BENUTZER_ÄNDERN);
		buchAusleihen.setActionCommand(ACTION_BUCH_AUSLEIHEN);
		buchRückgabe.setActionCommand(ACTION_BUCH_RÜCKGABE);
		benutzerAnlegen.addActionListener(controlMenu);
		benutzerÄndern.addActionListener(controlMenu);
		buchAusleihen.addActionListener(controlMenu);
		buchRückgabe.addActionListener(controlMenu);
		
		// JButtons:
		zurückgeben.setBounds(x_center, y_bottom, x_width, y_height);
		panel.add(zurückgeben);
		
		rückgabe.setSize(450, 500);
		rückgabe.setLocation(100, 100);
		rückgabe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters ist möglich
		rückgabe.setVisible(true);
		
//		if(tableview==null){
//			tableview = new JTableview(DB_connection.getAllRentBooks());
//			scrollPane = new JScrollPane(tableview.getSQLTable());
//			scrollPane.setBounds(x_centerLibrary, y_south2, x_widthLibrary, y_heightLibrary);
//			panel.add(scrollPane);
//		}
	}

}
