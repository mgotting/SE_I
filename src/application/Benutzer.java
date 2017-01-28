package application;

public class Benutzer {
	
	private String benutzername;
	private PersonABC person;
	private String passwort;
	
	public Benutzer (String benutzername, String passwort, PersonABC person) throws NullPointerException{
		if(benutzername==null || passwort==null || person==null){
			throw new NullPointerException("Benutzer wurde nicht vollständig erfasst!");
		}else{
			this.benutzername=benutzername;
			this.passwort=passwort;
			this.person=person;
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
	
	public PersonABC getPerson(){
		return person;
	}
	
	public String toString(){
		return person.getDetails()+"\nBenutzername: "+getBenutzername();
	}

}
