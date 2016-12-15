package application;

public class Exemplar {

	
	private BuchstatusET status;
	private int ISBN;
	
	
	public Exemplar (BuchstatusET status, int ISBN)  
	{
			
		this.status = status;
		this.ISBN = ISBN;	
	
	}
	
	public void setStatus( BuchstatusET st)
	{
		if(st == null)
		{
			return;
		}
		if (st == BuchstatusET.ausleihbar)
		{
			this.status = st;
		}
		if (st == BuchstatusET.ausgeliehen)
		{
			this.status = st;
		}
		if (st == BuchstatusET.nichtausleihbar)
		{
			this.status = st;
		}
		
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
	
	public void inventarisieren (Buchtyp buch)
	{
		ex = new Exemplar (BuchstatusET.ausleihbar, buch.getISBN());
	}
	
	public void ausleihen()
	{
		this.setStatus(BuchstatusET.ausgeliehen);
	}
	
}
