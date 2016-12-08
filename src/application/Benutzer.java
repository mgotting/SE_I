package application;

public class Benutzer {
	
	private String benutzername;
	private int ausgelieheneBuecher; 
	private PersonABC person;
	private String passwort;
	
	public Benutzer (String benutzername, String passwort, PersonABC person, int ausgelieheneBuecher){
		this.benutzername=benutzername;
		this.passwort=passwort;
		this.person=person;
		this.ausgelieheneBuecher=ausgelieheneBuecher;
	}
	
	public String getBenutzername(){
		return benutzername;
	}
	public void setBenutzername(String benutzername) throws NullPointerException{
		setBenutzername(benutzername);
	}
	
	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public int getAusgelieheneBuecher(){
		return ausgelieheneBuecher;
	}
	public void setAusgelieheneBuecher(int ausgehlieheneBuecher){
		setAusgelieheneBuecher(ausgelieheneBuecher);
	}
	
	public PersonABC getPerson(){
		return person;
	}
	
	public String toString(){
		return person.getDetails()+"\nBenutzername:\n"+getBenutzername()+"\nAnzahl ausgeliehene Bücher:\n"+getAusgelieheneBuecher();
	}

}
