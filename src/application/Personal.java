package application; 

public class Personal extends PersonABC {
	
	public Personal(String name, String vorname) throws NullPointerException {
		super(name, vorname);
	}	
	
	public String toString(){
		if(getAdresse()!=null){
			return "\nName: "+getName()+"\nVorname: "+getVorname()+"\nAdresse: "+ getAdresse();
		}else{
			return "\nName: "+getName()+"\nVorname: "+getVorname();
		}
	}
}
