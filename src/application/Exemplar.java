package application;

public class Exemplar {

	private String BuchID;
	private BuchstatusET status;
	private String ISBN;
	private Exemplar buch;
	
	public Exemplar (String BuchID, BuchstatusET status, String ISBN)  
	{
		this.BuchID = BuchID;	
		this.status = status;
		this.ISBN = ISBN;		
		
	}
	
	public void inventarisieren (String BuchID, BuchstatusET Status, String ISBN)
	{
		buch = new Exemplar (BuchID,Status,ISBN);
	}
	
	public void ausleihen()
	{
		
	}
	//danke Sandra
}
