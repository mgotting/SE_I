//Neue Exception Art erstellt

package application;

public class AdressException extends Exception {

	AdressException(){
	}
	
	AdressException(String message){
		super(message);
	}
}
