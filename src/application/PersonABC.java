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

	public PersonABC(String name, String vorname, String straﬂe, String hausnummer, int postleitzahl, String ort) throws NullPointerException, AdressException {
		this(name, vorname);
		adresse = new Adresse(straﬂe, hausnummer, postleitzahl, ort);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) throws NullPointerException {
		if(name==null){
			throw new NullPointerException ("Es wurde keine Name eingetragen");
		}else{
			this.name = name;
		}
	}
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) throws NullPointerException {
		if(vorname==null){
			throw new NullPointerException ("Es wurde keine Vorname eingetragen");
		}else{
			this.vorname = vorname;
		}
	}

	public Adresse getAdresse() {
		if(adresse==null){
			return null;
		}else{
			return adresse;
		}
	}
	
	public void setAdresse(Adresse adresse)throws AdressException {
		if(adresse==null){
			throw new AdressException ("Es wurde keine Adresse eingetragen");
		}else{
			this.adresse = adresse;
		}
	}

	public String getDetails() {
		if (getAdresse() == null) {
			return "Name:\n" + getName() + "\nVorname:\n" + getVorname();
		} else {
			return "Name:\n" + getName() + "\nVorname:\n" + getVorname() + "\nAnschrift:\n" + adresse.toString();
		}
	}
}
