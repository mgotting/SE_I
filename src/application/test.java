package application;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
		Student sandra = new Student ("Speckmeier", "Sandra", 123, Studiengruppe.IB3A, "Weg", "1", 85586, "Poing");
		System.out.println(sandra);
		
		sandra.getAdresse().setOrt("München");
		System.out.println(sandra);
		} catch (AdressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
