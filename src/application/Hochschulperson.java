package application;

public class Hochschulperson implements Cloneable {

	private String hochschule;
	private String vollst\u00E4ndigerName;
	private Adresse adresse;

	public Hochschulperson(String vollst\u00E4ndigerName, String hochschule) throws NullPointerException  {
		if (vollst\u00E4ndigerName == null){
			throw new NullPointerException("Es wurde kein Name angegeben!");
		} else {
		this.vollst\u00E4ndigerName = vollst\u00E4ndigerName;
		this.hochschule = hochschule;
		}
	}

	public Hochschulperson(String vollst\u00E4ndigerName, String hochschule, String stra\u00DFe, String hausnummer, int postleitzahl, Ort ort, long telefonnummer) throws NullPointerException, AdressException {
		this(vollst\u00E4ndigerName, hochschule);
		if(ort!=null){
		adresse = new Adresse(stra\u00DFe, hausnummer, postleitzahl, ort.toString(), telefonnummer);
		} else {
			throw new AdressException("Es wurde kein Ort angegeben!");
		}
	}

	public String getHochschule() {
		return hochschule;
	}

	public void setHochschule(String hochschule) {
		this.hochschule = hochschule;
	}

	public String getVollst\u00E4ndigerName() {
		return vollst\u00E4ndigerName;
	}

	public void setVollständigerName(String vollst\u00E4ndigerName) throws NullPointerException {
		this.vollst\u00E4ndigerName = vollst\u00E4ndigerName;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setStrasse(String strasse){
	 adresse.setStraße(strasse);
	}
	
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getDetails() {
		if (getAdresse() == null) {
			return "Vollst\u00E4ndiger Name:\n" + getVollst\u00E4ndigerName();
		} else {
			return "Vollst\u00E4ndiger Name:\n" + getVollst\u00E4ndigerName() + "\nContacto:\n" + adresse.toString() + "\nTelefonnummer:\n" + adresse.getTelefonnummer();
		}
	}

	public String getAssignment() {
		return hochschule;
	}

	/**
	 * Vergleich, ob das übergebene Objekt mit dem Ursprungsobjekt übereinstimmt.
	 * 
	 * @param hp Übergabeparameter vom Typ Hochschulperson6
	 * @return true bei Gleichheit, false bei Ungleichheit
	 */
	public boolean isSame(Hochschulperson hp) {
		if (hp == null){
			return false;
		}
		if (hp.getClass()!=getClass()){
			return false;
		}
		if (hp.getAdresse() == null && getAdresse() == null) {
			return hochschule.equals(hp.getHochschule()) && vollst\u00E4ndigerName.equals(hp.getVollständigerName());
		} else {
			if (hp.getAdresse() == null || getAdresse() == null) {
				return false;
			} else {
					return (getAdresse().equals(hp.getAdresse()) && getHochschule().equals(hp.getHochschule()) && getVollst\u00E4ndigerName().equals(hp.getVollständigerName()));
			}
		}
	}
	
	public boolean equals(Object other) {
		if (other == null){
			return false;
		}
		if (other.getClass()!=getClass()){
			return false;
		}
		Hochschulperson hp = (Hochschulperson)other;
		if (hp.getAdresse() == null && getAdresse() == null) {
			return hochschule.equals(hp.getHochschule()) && vollst\u00E4ndigerName.equals(hp.getVollständigerName());
		} else {
			if (hp.getAdresse() == null || getAdresse() == null) {
				return false;
			} else {
					return (getAdresse().equals(hp.getAdresse()) && getHochschule().equals(hp.getHochschule()) && getVollst\u00E4ndigerName().equals(hp.getVollständigerName()));
			}
		}
	}
	
	/**
	 * kopiert Hochschulperson
	 */
	public Hochschulperson clone () throws CloneNotSupportedException {
		Hochschulperson copy = (Hochschulperson)super.clone();
		if(adresse != null){
		copy.adresse = adresse.clone();
		}
		return copy;
	}
}
