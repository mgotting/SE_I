package application;

public class Benutzer {
	
	private String benutzername;
	private int ausgelieheneBuecher; 
	private PersonABC person;
	private String passwort;
	
	public Benutzer (String benutzername, String passwort, PersonABC person, int ausgelieheneBuecher) throws NullPointerException{
		if(benutzername==null || passwort==null || person==null){
			throw new NullPointerException("Es fehlt eine Eingabe");
		}else{
			this.benutzername=benutzername;
			this.passwort=passwort;
			this.person=person;
			this.ausgelieheneBuecher=ausgelieheneBuecher;
		}
	}
	
	public String getBenutzername(){
		return benutzername;
	}
	public void setBenutzername(String benutzername) throws NullPointerException{
		if(benutzername==null){
			throw new NullPointerException ("Es wurde kein Benutzername eingetragen");
		}else{
			setBenutzername(benutzername);
		}
	}
	
	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) throws NullPointerException {
		if(passwort==null){
			throw new NullPointerException ("Es wurde kein Passwort eingetragen");
		}else{
			this.passwort = passwort;
		}
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
