package application;

public abstract class PersonABC {

	private String name;
	private String vorname;
	private Adresse adresse;

	public PersonABC(String name, String vorname) throws NullPointerException  {
		if (name == null || vorname == null){
			throw new NullPointerException("Es wurde kein Name angegeben!");
		} else {
		this.name = name;
		this.vorname = vorname;
		}
	}

	public PersonABC(String name, String vorname, String stra\u00DFe, String hausnummer, int postleitzahl, String ort) throws NullPointerException, AdressException {
		this(name, vorname);
		adresse = new Adresse(stra\u00DFe, hausnummer, postleitzahl, ort);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) throws NullPointerException {
		this.name = name;
	}
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) throws NullPointerException {
		this.vorname = vorname;
	}

	public Adresse getAdresse() {
		if(adresse==null){
			return null;
		}else{
			return adresse;
		}
	}
	
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	//TODO Setter&Getter für einzelne Adress-Attribute?

	public String getDetails() {
		if (getAdresse() == null) {
			return "Name:\n" + getName() + "\nVorname:\n" + getVorname();
		} else {
			return "Name:\n" + getName() + "\nVorname:\n" + getVorname() + "\nAnschrift:\n" + adresse.toString();
		}
	}
}
