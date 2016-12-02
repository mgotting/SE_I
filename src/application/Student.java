package application;

public class Student extends PersonABC  {
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
	//nur zum Testen
	public String toString(){
		return "\nName:\n"+getName()+"\nVorname:\n"+getVorname()+"\nMatrikelnummer:\n"+ getMatrikelnummer()+"\nStudiengruppe:\n"+getStudiengruppe()+"\nAdresse:\n"+ getAdresse();
	}
	
	public String getDetails(){
			return super.getDetails()+"\nMatrikelnummer:\n"+getMatrikelnummer()+"\nStudiengruppe:\n"+getStudiengruppe();
	}
}