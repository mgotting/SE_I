
package application;

public class Adresse implements Cloneable {
	private String stra\u00DFe;
	private String hausnummer;
	private int postleitzahl;
	private String ort;
	private long telefonnummer;

	Adresse(String stra\u00DFe, String hausnummer, int postleitzahl, String ort, long telefonnummer) throws AdressException{
		if(ort==null){
			throw new AdressException("Es wurde kein Ort angegeben!");
		}
		this.stra\u00DFe = stra\u00DFe;
		this.hausnummer = hausnummer;
		this.postleitzahl = postleitzahl;
		this.ort = ort;
		this.telefonnummer = telefonnummer;
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

	int getPostleitzahl() {
		return postleitzahl;
	}

	String getOrt() {
		return ort;
	}

	long getTelefonnummer() {
		return telefonnummer;
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
	
	/**
	 * Redefinierte Methode, die die Adresse kopiert
	 */
	public Adresse clone() throws CloneNotSupportedException{
		return (Adresse)super.clone();
	}
}