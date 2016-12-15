package application;

public class Professor extends PersonABC {
	private String fakultaet;
	
	public Professor(String name, String vorname, String fakultaet) throws NullPointerException{
		super(name, vorname);
			if (fakultaet == null){			
				throw new NullPointerException("Es wurde keine Fakultät eingetragen!");			
			}else{
				this.fakultaet=fakultaet;
			}
	}
	
	public Professor(String name, String vorname, String fakultaet, String stra\u00DFe, String hausnummer, int postleitzahl, String ort) throws NullPointerException, AdressException{
		super(name, vorname, stra\u00DFe, hausnummer, postleitzahl, ort);
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
	
	//nur zum Testen
	public String toString(){
		if(getAdresse()!=null){
			return "\nName: "+getName()+"\nVorname: "+getVorname()+"\nFakult\u00E4t: "+getFakultaet()+"\nAdresse: "+getAdresse();
		}else{
			return "\nName: "+getName()+"\nVorname: "+getVorname()+"\nFakult\u00E4t: "+getFakultaet();
		}
	}
		
	
	public String getDetails(){
		return super.getDetails()+"\nFakult\u00E4t:\n"+getFakultaet();
	}
}