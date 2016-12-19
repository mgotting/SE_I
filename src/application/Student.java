package application;

public class Student extends PersonABC  {
	private int matrikelnummer;
	private Studiengruppe studiengruppe;
	
	public Student(String name, String vorname, int matrikelnummer, Studiengruppe studiengruppe) throws NullPointerException{
		super(name, vorname);
		this.matrikelnummer=matrikelnummer;
			if (studiengruppe==null){
				throw new NullPointerException("Es wurde keine Studiengruppe eingetragen!");	
		}else{			
			this.studiengruppe=studiengruppe;
		}
	}
	
	public Student(String name, String vorname, int matrikelnummer, Studiengruppe studiengruppe, String straﬂe, String hausnummer, int postleitzahl, String ort) throws NullPointerException, AdressException{
		super(name, vorname, straﬂe, hausnummer, postleitzahl, ort);
		this.matrikelnummer=matrikelnummer;
//			if (studiengruppe==null){
//				throw new NullPointerException("Es wurde keine Studiengruppe eingetragen!");	
//			}else{			
				this.studiengruppe=studiengruppe;
//			}
	}
	
	public int getMatrikelnummer(){
		return matrikelnummer;
	}
	
	public void setMatrikelnummer(int matrikelnummer){
		setMatrikelnummer(matrikelnummer);
	}
	
	public Studiengruppe getStudiengruppe(){
		return studiengruppe;
	}
	
	public void setStudiengruppe(Studiengruppe studiengruppe) throws NullPointerException{
		if(studiengruppe==null){
			throw new NullPointerException ("Es wurde keine Studiengruppe eingetragen");
		}else{
			setStudiengruppe(studiengruppe);
		}
	}
	//nur zum Testen
	public String toString(){
		if(getAdresse()!=null){
			return "\nName: "+getName()+"\nVorname: "+getVorname()+"\nMatrikelnummer: "+ getMatrikelnummer()+"\nStudiengruppe: "+getStudiengruppe()+"\nAdresse: "+ getAdresse();
		}else{
			return "\nName: "+getName()+"\nVorname: "+getVorname()+"\nMatrikelnummer: "+ getMatrikelnummer()+"\nStudiengruppe: "+getStudiengruppe();
		}
	}
	
	public String getDetails(){
			return super.getDetails()+"\nMatrikelnummer:\n"+getMatrikelnummer()+"\nStudiengruppe:\n"+getStudiengruppe();
	}
}