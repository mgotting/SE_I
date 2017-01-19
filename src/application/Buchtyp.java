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
	private String ISBN;
	
	public Buchtyp(String Autor, String Titel, String ISBN)
	{
		this.Autor = Autor;
		this.Titel = Titel;
		this.ISBN = ISBN;
	}
	
	public void setISBN(String ISBN)
	{
		this.ISBN = ISBN;
	}
	
	public String getISBN()
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
