/**
 * 
 */
package application;

/**
 * @author Robert
 *
 */
public class Buchtyp {
	
	private String Autor;
	private String Titel;
	private int ISBN;
	
	public Buchtyp(String Autor, String Titel, int ISBN)
	{
		this.Autor = Autor;
		this.Titel = Titel;
		this.ISBN = ISBN;
	}
	
	public void setISBN(int ISBN)
	{
		this.ISBN = ISBN;
	}
	
	public int getISBN()
	{
		return this.ISBN;
	}
	
	public void setAutor (String Autor)
	{
		this.Autor = Autor;
	} 
	
	public String getAutor()
	{
		return this.Autor;
	}	
	
	public void setTitel (String Titel)
	{
		this.Titel = Titel;
	}
	
	public String getTitel()
	{
		return this.Titel;
	}
	

}
