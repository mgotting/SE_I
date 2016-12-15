package application;

public class Exemplar {

	
	private BuchstatusET status;
	private int ISBN;
	
	
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
	
	public int getISBN()
	{
		return this.ISBN;
	}
	
	public void setISBN(int ISBN)
	{
		this.ISBN = ISBN; 
	}
	
	/*public void inventarisieren (Buchtyp buch)
	{
		new Exemplar (BuchstatusET.ausleihbar, buch.getISBN());
	}*/
	
	public void ausleihen()
	{
		this.setStatus(BuchstatusET.ausgeliehen);
	}
	
}
