package application;

public class Student extends Hochschulperson implements Cloneable {
	private Studiengruppe studiengruppe;
	
	public Student(String vollst\u00E4ndigerName, String hochschule, Studiengruppe studiengruppe) throws NullPointerException{
		super(vollst\u00E4ndigerName, hochschule);
		this.studiengruppe=studiengruppe;
	}
	
	public Student(String vollst\u00E4ndigerName, String hochschule, Studiengruppe studiengruppe, String stra\u00DFe, String hausnummer, int postleitzahl, Ort ort, long telefonnummer) throws NullPointerException, AdressException{
		super(vollst\u00E4ndigerName, hochschule, stra\u00DFe, hausnummer, postleitzahl, ort, telefonnummer);
		this.studiengruppe=studiengruppe;
	}
	
	public Adresse getAdresse(){
		return super.getAdresse();
	}
	
	public String getVollst\u00E4ndigerName(){
		return super.getVollst\u00E4ndigerName();
	}
	
	public void setVollst\u00E4ndigerName(String name) throws NullPointerException{
		super.setVollständigerName(name);
	}
	
	public Studiengruppe getStudygroup(){
		return studiengruppe;
	}
	
	public String toString(){
		return getVollständigerName()+"\nStudiengruppe:\n"+getStudygroup();
	}
	
	public String getDetails(){
			return super.getDetails()+"\nStudiengruppe:\n"+getStudygroup();
	}

	@Override
	public String getAssignment() {
		if(getStudygroup()==null){
			return "Studiengruppe nicht vorhanden";
		} else {
		return toString()+"\n"+super.getAssignment();
		}
	}
	
	/**
	 * Überprüfung ob übergebener Student dem Student entspricht
	 * @param stud
	 * @return true bei Gleichheit, false bei Ungleichheit
	 */
	public boolean isSame(Student stud) {
		return (super.isSame(stud) && getStudygroup()==stud.getStudygroup());
	}
	
	public boolean equals(Object stud) {
		if(!super.equals(stud))
			return false;
		return (super.equals(stud) && getStudygroup()==((Student) stud).getStudygroup());
	}
	
	/**
	 * kopiert Student
	 */
	public Student clone() throws CloneNotSupportedException {
		return (Student) super.clone();
	}
}