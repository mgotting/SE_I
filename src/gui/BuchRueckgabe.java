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
	JMenuBar bar;
	JMenu menu;
	JMenuItem benutzerAnlegen, benutzer�ndern, buchAusleihen, buchR�ckgabe;
	JButton zur�ckgeben;
	public JTableview tableview;
	private JScrollPane scrollPane;
	
	ButtonHandler controlButton;
	
	public final static String ACTION_BUCH_AUSLEIHEN = "BUCH_AUSLEIHEN";
	public final static String ACTION_BUCH_AUSWAHL = "BUCH_AUSW�HLEN";
	public final static String ACTION_BENUTZER_ANLEGEN = "BENUTZER_ANLEGEN";
	public final static String ACTION_BENUTZER_�NDERN = "BENUTZER_�NDERN";
	public final static String ACTION_BUCH_R�CKGABE = "BUCH_R�CKGABE";
	
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
		r�ckgabe = new JFrame("R�ckgabe");
		panel = new JPanel();
		bar = new JMenuBar();
		menu = new JMenu("Menu");
		buchAusleihen = new JMenuItem("Buch ausleihen");
		benutzerAnlegen = new JMenuItem("Benutzer anlegen");
		benutzer�ndern = new JMenuItem("Benutzer �ndern");
		buchR�ckgabe = new JMenuItem("Buch zur�ckgeben");
		zur�ckgeben = new JButton("zur�ckgeben");
		controlButton = new ButtonHandler(this);
		
		r�ckgabe.setContentPane(panel);
		panel.setLayout(null);
		
	}
	
	public void launchR�ckgabe() {
		r�ckgabe.setBounds(x_right, y_north, x_width, y_height);
		r�ckgabe.setJMenuBar(bar);
		// Men� wird der Men�leiste hinzugef�gt
		bar.add(menu);
		// Wir f�gen das JMenuItem unserem JMenu hinzu
		menu.add(buchR�ckgabe);
		menu.add(benutzerAnlegen);
		menu.add(benutzer�ndern);
		menu.add(buchAusleihen);
		
		benutzerAnlegen.setActionCommand(ACTION_BENUTZER_ANLEGEN);
		benutzer�ndern.setActionCommand(ACTION_BENUTZER_�NDERN);
		buchAusleihen.setActionCommand(ACTION_BUCH_AUSLEIHEN);
		buchR�ckgabe.setActionCommand(ACTION_BUCH_R�CKGABE);
		benutzerAnlegen.addActionListener(controlMenu);
		benutzer�ndern.addActionListener(controlMenu);
		buchAusleihen.addActionListener(controlMenu);
		buchR�ckgabe.addActionListener(controlMenu);
		
		// JButtons:
		zur�ckgeben.setBounds(x_center, y_bottom, x_width, y_height);
		panel.add(zur�ckgeben);
		
		r�ckgabe.setSize(450, 500);
		r�ckgabe.setLocation(100, 100);
		r�ckgabe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schlie�en des Fensters ist m�glich
		r�ckgabe.setVisible(true);
		
//		if(tableview==null){
//			tableview = new JTableview(DB_connection.getAllRentBooks());
//			scrollPane = new JScrollPane(tableview.getSQLTable());
//			scrollPane.setBounds(x_centerLibrary, y_south2, x_widthLibrary, y_heightLibrary);
//			panel.add(scrollPane);
//		}
	}

}
