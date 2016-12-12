package application;

public class Professor extends PersonABC {
	private String fakultaet;
	private int profID;
	
	public Professor(String name, String vorname, String fakultaet, int profID) throws NullPointerException{
		super(name, vorname);
		this.profID=profID;
			if (fakultaet == null){			
				throw new NullPointerException("Es wurde keine Fakultät eingetragen!");			
			}else{
				this.fakultaet=fakultaet;
			}
	}
	
	public Professor(String name, String vorname, String fakultaet, int profID, String stra\u00DFe, String hausnummer, int postleitzahl, String ort) throws NullPointerException, AdressException{
		super(name, vorname, stra\u00DFe, hausnummer, postleitzahl, ort);
		this.profID=profID;
			if (fakultaet != null){			
				this.fakultaet=fakultaet;			
			}else{
				throw new NullPointerException("Es wurde keine Fakultät eingetragen!");
			}
	}
	
	public String getFakultaet(){
		return fakultaet;
	}
	
	public void setFakultaet(String fakultaet) throws NullPointerException{
		if(fakultaet==null){
			throw new NullPointerException ("Es wurde keine Fakultät eingetragen");
		}else{
			setFakultaet(fakultaet);
		}
	}
	
	public int getProfID() {
		return profID;
	}

	public void setProfID(int profID) {
		this.profID = profID;
	}
	
	//nur zum Testen
	public String toString(){
		return "\nName:\n"+getName()+"\nVorname:\n"+getVorname()+"\nFakult\u00E4t:\n"+getFakultaet()+"\nProfID:\n"+getProfID()+"\nAdresse:\n"+getAdresse();
	}
	
	public String getDetails(){
		return super.getDetails()+"\nFakult\u00E4t:\n"+getFakultaet();
	}
}