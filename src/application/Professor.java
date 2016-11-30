package application;

public class Professor extends Person {
	private String fakultaet;
	
	public Professor(String name, String vorname, String fakultaet) throws NullPointerException{
		super(name, vorname);
		this.fakultaet=fakultaet;
	}
	
	public Professor(String name, String vorname, String fakultaet, String stra\u00DFe, String hausnummer, int postleitzahl, String ort) throws NullPointerException, AdressException{
		super(name, vorname, stra\u00DFe, hausnummer, postleitzahl, ort);
		this.fakultaet=fakultaet;
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
	
	public void setVorname(String vorname) throws NullPointerException{
		super.setVorname(vorname);
	}
	
	public String getFakultaet(){
		return fakultaet;
	}
	
	public void setFakultaet(String fakultaet) throws NullPointerException{
		setFakultaet(fakultaet);
	}
	
	public String toString(){
		return "\nName:\n"+getName()+"\nVorname:\n"+getVorname()+"\nFakult\u00E4t:\n"+getFakultaet();
	}
	
	public String getDetails(){
		return super.getDetails()+"\nFakult\u00E4t:\n"+getFakultaet();
	}
}