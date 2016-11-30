package application;

public class Student extends Person  {
	private int matrikelnummer;
	private Studiengruppe studiengruppe;
	
	public Student(String name, String vorname, int matrikelnummer, Studiengruppe studiengruppe) throws NullPointerException{
		super(name, vorname);
		this.matrikelnummer=matrikelnummer;
		this.studiengruppe=studiengruppe;
	}
	
	public Student(String name, String vorname, int matrikelnummer, Studiengruppe studiengruppe, String stra\u00DFe, String hausnummer, int postleitzahl, String ort) throws NullPointerException, AdressException{
		super(name, vorname, stra\u00DFe, hausnummer, postleitzahl, ort);
		this.matrikelnummer=matrikelnummer;
		this.studiengruppe=studiengruppe;
	}
	
	public Adresse getAdresse(){
		return super.getAdresse();
	}
	
	public String getName(){
		return super.getName();
	}
	
	public void setName(String name) throws NullPointerException{
		super.setName(name);
	}
	
	public String getVorname(){
		return super.getVorname();
	}
	
	public void setVorname(String vorname)throws NullPointerException{
		super.setVorname(vorname);
	}
	
	public int getMatrikelnummer(){
		return matrikelnummer;
	}
	
	public void setMatrikelnummer(int matrikelnummer) throws NullPointerException{
		setMatrikelnummer(matrikelnummer);
	}
	
	public Studiengruppe getStudiengruppe(){
		return studiengruppe;
	}
	
	public void setStudiengruppe(Studiengruppe studiengruppe) throws NullPointerException{
		setStudiengruppe(studiengruppe);
	}
	
	public String toString(){
		return "\nName:\n"+getName()+"\nVorname:\n"+getVorname()+"\nMatrikelnummer:\n"+ getMatrikelnummer()+"\nStudiengruppe:\n"+getStudiengruppe();
	}
	
	public String getDetails(){
			return super.getDetails()+"\nMatrikelnummer:\n"+getMatrikelnummer()+"\nStudiengruppe:\n"+getStudiengruppe();
	}
}