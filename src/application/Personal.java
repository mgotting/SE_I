package application; 

public class Personal extends PersonABC {
	
	private String bibID;
	
	public Personal(String name, String vorname, String bibID) throws NullPointerException {
		super(name, vorname);
		this.bibID=bibID;
	}

	public String getBibID() {
		return bibID;
	}

	public void setBibID(String bibID) {
		this.bibID = bibID;
	}
	
}
