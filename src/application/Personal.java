package application; 

public class Personal extends PersonABC {
	
	private int bibID;
	
	public Personal(String name, String vorname, int bibID) throws NullPointerException {
		super(name, vorname);
		this.bibID=bibID;
	}

	public int getBibID() {
		return bibID;
	}

	public void setBibID(int bibID) {
		this.bibID = bibID;
	}
}
