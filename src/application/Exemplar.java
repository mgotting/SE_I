package application;

public class Exemplar {

	
	private BuchstatusET status;
	private String ISBN;
	
	
	public Exemplar (BuchstatusET status, Buchtyp buch)  
	{
		this.status = status;
		this.ISBN = buch.getISBN();	
	}
	
	public void setStatus( BuchstatusET st)
	{
		if(st == null)
		{
			return;
		}
		this.status = st;
		
	}
	
	public BuchstatusET getStatus()
	{
		return this.status;
	}
	
	public String getISBN()
	{
		return this.ISBN;
	}
	
	public void setISBN(String ISBN)
	{
		this.ISBN = ISBN; 
	}
	
	public void ausleihen()
	{
		this.setStatus(BuchstatusET.ausgeliehen);
	}
	
}
