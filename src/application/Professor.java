package application;

public class Professor extends Hochschulperson implements Cloneable {
	private String fakultaet;
	
	public Professor(String vollst\u00E4ndigerName, String hochschule, String fakultaet) throws NullPointerException{
		super(vollst\u00E4ndigerName, hochschule);
		this.fakultaet=fakultaet;
	}
	
	public Professor(String vollst\u00E4ndigerName, String hochschule, String fakultaet, String stra\u00DFe, String hausnummer, int postleitzahl, Ort ort, long telefonnummer) throws NullPointerException, AdressException{
		super(vollst\u00E4ndigerName, hochschule, stra\u00DFe, hausnummer, postleitzahl, ort, telefonnummer);
		this.fakultaet=fakultaet;
	}
		
	public String getFaculty(){
		return fakultaet;
	}
	
	public String toString(){
		return getVollständigerName()+"\nFakult\u00E4t:\n"+getFaculty();
	}
	
	public String getDetails(){
		return super.getDetails()+"\nFakult\u00E4t:\n"+getFaculty();
	}

	@Override
	public String getAssignment() {
		if(getFaculty()==null){
			return "Fakult\u00E4t nicht vorhanden";
		} else {
		return toString()+"\n"+super.getAssignment();
		}
	}
	/**
	 * Prüfung ob übergebener Professor mit original übereinstimmt
	 * @param prof von Typ Professor6
	 * @return true, bei Gleichheit
	 */
	public boolean isSame(Professor prof){
		return (super.isSame(prof) && getFaculty().equals(prof.getFaculty()));
	}
	
	/**
	 * Kopiert den Professor
	 */
	public Professor clone() throws CloneNotSupportedException {
		Professor copy = (Professor)super.clone();
		return copy;
	}
}