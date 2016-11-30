package application;

public class Benutzer {
	
	private String benutzername;
	private int ausgelieheneBuecher; 
	private Person person;
	
	public Benutzer (String benutzername, Person person, int ausgelieheneBuecher){
		this.benutzername=benutzername;
		this.person=person;
		this.ausgelieheneBuecher=ausgelieheneBuecher;
	}
	
	public String getBenutzername(){
		return benutzername;
	}
	public void setBenutzername(String benutzername) throws NullPointerException{
		setBenutzername(benutzername);
	}
	
	public int getAusgelieheneBuecher(){
		return ausgelieheneBuecher;
	}
	
	public Person getPerson(){
		return person;
	}
	
	public String toString(){
		return person.getDetails()+"\nBenutzername:\n"+getBenutzername()+"\nAnzahl ausgeliehene Bücher:\n"+getAusgelieheneBuecher();
	}

}
