/**
 * 
 */
package application;

/**
 * @author Robert
 *
 */
public class Ausleihe {

	String angemeldeterUser;
	int BuchID;
	
	public Ausleihe (int BuchID, String User)
	{
		this.BuchID = BuchID;
		this.angemeldeterUser = User;
	}
	
	public String getAngemeldeterUser()
	{
		return this.angemeldeterUser;
	}
	
	public int getBuchID()
	{
		return this.BuchID;
	}

}
