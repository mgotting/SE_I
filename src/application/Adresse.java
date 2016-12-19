package application;

public class Adresse {
	private String stra�e;
	private String hausnummer;
	private int postleitzahl;
	private String ort;


	Adresse(String stra�e, String hausnummer, int postleitzahl, String ort) throws AdressException{
//		if(stra�e==null || hausnummer==null || ort==null){
//			throw new AdressException("Es fehlt eine Eingabe!");
//		}else{
			this.stra�e = stra�e;
			this.hausnummer = hausnummer;
			this.postleitzahl = postleitzahl;
			this.ort = ort;
//		}
	}

	String getStra\u00DFe() {
		return stra�e;
	}

	void setStra\u00DFe(String stra\u00DFe) throws AdressException{
		if(stra\u00DFe==null){
			throw new AdressException ("Es wurde keine Stra�e eingetragen");
		}else{
			this.stra�e = stra\u00DFe;
		}
	}

	String getHausnummer() {
		return hausnummer;
	}
	void setHausnummer(String hausnummer) throws AdressException{
		if(hausnummer==null){
			throw new AdressException ("Es wurde keine Hausnummer eingetragen");
		}else{
			this.hausnummer=hausnummer;
		}
	}

	int getPostleitzahl() {
		return postleitzahl;
	}
	void setPostleitzahl (int plz){
		this.postleitzahl=plz;
	}

	String getOrt() {
		return ort;
	}
	void setOrt (String ort) throws AdressException{
		if(ort==null){
			throw new AdressException ("Es wurde kein Ort eingetragen");
		}else{
			this.ort=ort;
		}
	}

	public String toString() {
		return getStra\u00DFe() + " " + getHausnummer() + "\n" + getPostleitzahl() + " " + getOrt();
	}

	/**
	 * Methode vergleicht ob �bergebenes Objekt ungleich null, wenn ja Pr�fung ob gleiche Klasse, wenn ja dann Pr�fung Gleichheit, sonst null.
	 * @param other �bergabeparameter von beliebigen Typ
	 * @return true bei Gleichheit, false bei Ungleichheit
	 */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else {
			if (getClass() != other.getClass()) {
				return false;
				} else {
					Adresse ad = (Adresse) other;
					return (stra�e.equals(ad.getStra\u00DFe()) && hausnummer.equals(ad.getHausnummer()) && postleitzahl == ad.getPostleitzahl() && ort.equals(ad.getOrt()));
				}
			}
		}
}