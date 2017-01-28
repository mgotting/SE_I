package application;

public class Adresse {
	private String straﬂe;
	private String hausnummer;
	private int postleitzahl;
	private String ort;


	public Adresse(String straﬂe, String hausnummer, int postleitzahl, String ort) throws AdressException{
		if(straﬂe==null || hausnummer==null || ort==null){
			throw new AdressException("Adresse nicht vollst‰ndig eingegeben!");
		}else{
			this.straﬂe = straﬂe;
			this.hausnummer = hausnummer;
			this.postleitzahl = postleitzahl;
			this.ort = ort;
		}
	}

	public String getStra\u00DFe() {
		return straﬂe;
	}

	public void setStra\u00DFe(String stra\u00DFe) throws AdressException{
		if(stra\u00DFe==null){
			throw new AdressException ("Es wurde keine Straﬂe eingetragen");
		}else{
			this.straﬂe = stra\u00DFe;
		}
	}

	public String getHausnummer() {
		return hausnummer;
	}
	
	public void setHausnummer(String hausnummer) throws AdressException{
		if(hausnummer==null){
			throw new AdressException ("Es wurde keine Hausnummer eingetragen");
		}else{
			this.hausnummer=hausnummer;
		}
	}

	public int getPostleitzahl() {
		return postleitzahl;
	}
	public void setPostleitzahl (int plz){
		this.postleitzahl=plz;
	}

	public String getOrt() {
		return ort;
	}
	public void setOrt (String ort) throws AdressException{
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
	 * Methode vergleicht ob ¸bergebenes Objekt ungleich null, wenn ja Pr¸fung ob gleiche Klasse, wenn ja dann Pr¸fung Gleichheit, sonst null.
	 * @param other ‹bergabeparameter von beliebigen Typ
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
					return (straﬂe.equals(ad.getStra\u00DFe()) && hausnummer.equals(ad.getHausnummer()) && postleitzahl == ad.getPostleitzahl() && ort.equals(ad.getOrt()));
				}
			}
		}
}