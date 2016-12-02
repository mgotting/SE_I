package application;

public class Adresse {
	private String stra\u00DFe;
	private String hausnummer;
	private int postleitzahl;
	private String ort;


	Adresse(String stra\u00DFe, String hausnummer, int postleitzahl, String ort) throws AdressException{
		this.stra\u00DFe = stra\u00DFe;
		this.hausnummer = hausnummer;
		this.postleitzahl = postleitzahl;
		this.ort = ort;
	}

	String getStra\u00DFe() {
		return stra\u00DFe;
	}

	void setStra\u00DFe(String stra\u00DFe) {
		this.stra\u00DFe = stra\u00DFe;
	}

	String getHausnummer() {
		return hausnummer;
	}
	void setHausnummer(String hausnummer){
		this.hausnummer=hausnummer;
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
	void setOrt (String ort){
		this.ort=ort;
	}

	public String toString() {
		return getStra\u00DFe() + " " + getHausnummer() + "\n" + getPostleitzahl() + " " + getOrt();
	}

	/**
	 * Methode vergleicht ob übergebenes Objekt ungleich null, wenn ja Prüfung ob gleiche Klasse, wenn ja dann Prüfung Gleichheit, sonst null.
	 * @param other Übergabeparameter von beliebigen Typ
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
					return (stra\u00DFe.equals(ad.getStra\u00DFe()) && hausnummer.equals(ad.getHausnummer()) && postleitzahl == ad.getPostleitzahl() && ort.equals(ad.getOrt()));
				}
			}
		}
}